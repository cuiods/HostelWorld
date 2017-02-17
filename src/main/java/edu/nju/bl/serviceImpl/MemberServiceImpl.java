package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.AccountService;
import edu.nju.bl.service.AuthService;
import edu.nju.bl.service.MemberService;
import edu.nju.bl.strategy.ExchangeScoreStrategy;
import edu.nju.bl.vo.*;
import edu.nju.data.dao.AuthorityDao;
import edu.nju.data.dao.ConsumeDao;
import edu.nju.data.dao.MemberDao;
import edu.nju.data.entity.AuthorityEntity;
import edu.nju.data.entity.MemberEntity;
import edu.nju.exception.HostelException;
import edu.nju.util.constant.AuthorityConstant;
import edu.nju.util.constant.ErrorCode;
import edu.nju.util.constant.MemberConstant;
import edu.nju.util.constant.MessageConstant;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.MemberState;
import edu.nju.util.enums.UserType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * member service impl
 * @author cuihao
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberDao memberDao;

    @Resource
    private AuthorityDao authorityDao;

    @Resource
    private ConsumeDao consumeDao;

    @Resource
    private AuthService authService;

    @Resource
    private AccountService accountService;

    @Resource
    private ExchangeScoreStrategy exchangeScoreStrategy;

    /**
     * Get member basic person info, not including extra info.
     *
     * @param memberId user id of member
     * @return {@link MemberVo}
     */
    @Override
    public MemberVo getMemberInfo(int memberId) {
        return new MemberVo(memberDao.findById(memberId));
    }

    /**
     * Create member
     *
     * @return {@link MemberVo}
     */
    @Override
    public MemberVo createMember(String name, String password, String phone,
                                 String avatar, Gender gender, String description) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName(name);
        memberEntity.setPassword(password);
        memberEntity.setPhone(phone);
        memberEntity.setAvatar(avatar);
        memberEntity.setType(UserType.member);
        memberEntity.setValid((byte) 0);
        memberEntity.setGender(gender);
        memberEntity.setDescription(description);
        memberEntity.setState(MemberState.newly);
        memberEntity.setLevel(0);
        memberEntity.setScore(0);
        memberEntity.setRemain(0);
        memberEntity.setAuthorityEntities(authorityDao.findMemberPause());
        MemberEntity memberEntitySaved = memberDao.save(memberEntity);
        accountService.createAccount(memberEntitySaved);
        return new MemberVo(memberEntitySaved);
    }

    /**
     * Edit member
     *
     * @param memberId member id
     * @param password password
     * @param avatar avatar
     * @param gender gender
     * @param description description
     * @return {@link MemberVo}
     */
    @Override
    public MemberVo editMember(int memberId, String password, String avatar, Gender gender, String description) {
        MemberEntity memberEntity = memberDao.findById(memberId);
        memberEntity.setPassword(password);
        memberEntity.setAvatar(avatar);
        memberEntity.setGender(gender);
        memberEntity.setDescription(description);
        return new MemberVo(memberDao.save(memberEntity));
    }

    /**
     * Activate member authority.
     *
     * @param memberId  member id
     * @param accountId account id
     * @param money     activate money
     * @return {@link ResultVo < MemberVo >}
     */
    @Override
    @Transactional
    public ResultVo<MemberVo> transferToRemain(int memberId, int accountId, int money) throws HostelException {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity==null) throw new HostelException(ErrorCode.MEMBER_NOT_FOUND, MessageConstant.MEMBER_NOT_FOUND);
        long count = memberEntity.getAccountEntities().stream()
                .filter(accountEntity -> accountEntity.getId() == accountId).count();
        if (count==0) throw new HostelException(ErrorCode.ACCOUNT_CONFLICT, MessageConstant.ACCOUNT_CONFLICT);
        if (!authService.isSelf(memberId))
            throw new HostelException(ErrorCode.MEMBER_CONFLICT, MessageConstant.MEMBER_CONFLICT);
        accountService.accountPay(accountId,money);
        memberEntity.setRemain(memberEntity.getRemain()+money);
        consumeDao.save(memberEntity,money,"Transfer from Account:"+accountId);
        if (memberEntity.getRemain()>=MemberConstant.ACTIVE_REMAIN) {
            memberEntity.setState(MemberState.active);
            memberEntity.setActiveDate(new Date(System.currentTimeMillis()));
            memberEntity.setAuthorityEntities(authorityDao.findMemberActive());
        }
        return new ResultVo<>(ErrorCode.SUCCESS,MessageConstant.SUCCESS,new MemberVo(memberDao.save(memberEntity)));
    }

    /**
     * Stop member authority
     *
     * @param memberId member id
     * @return {@link ResultVo < MemberVo >}
     */
    @Override
    public ResultVo<MemberVo> stopMember(int memberId) throws HostelException {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity==null) throw new HostelException(ErrorCode.MEMBER_NOT_FOUND, MessageConstant.MEMBER_NOT_FOUND);
        memberEntity.setState(MemberState.stop);
        List<AuthorityEntity> authorityEntities = new ArrayList<>();
        authorityEntities.add(authorityDao.findByName(AuthorityConstant.USER_BASE));
        memberEntity.setAuthorityEntities(authorityEntities);
        return new ResultVo<>(ErrorCode.SUCCESS,MessageConstant.SUCCESS,new MemberVo(memberDao.save(memberEntity)));
    }

    /**
     * Pause member authority
     *
     * @param memberId member id
     * @return {@link ResultVo < MemberVo >}
     */
    @Override
    public ResultVo<MemberVo> pauseMember(int memberId) {
        MemberEntity memberEntity = memberDao.findById(memberId);
        memberEntity.setState(MemberState.pause);
        memberEntity.setAuthorityEntities(authorityDao.findMemberPause());
        return new ResultVo<>(ErrorCode.SUCCESS,MessageConstant.SUCCESS,new MemberVo(memberDao.save(memberEntity)));
    }

    /**
     * Exchange score to remain money
     *
     * @param score score to exchange
     * @return {@link ResultVo < MemberVo >}
     */
    @Override
    @Transactional
    public ResultVo<MemberVo> exchangeScore(int memberId, int score) throws HostelException {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity == null) throw new HostelException(ErrorCode.MEMBER_NOT_FOUND, MessageConstant.MEMBER_NOT_FOUND);
        if (!authService.isSelf(memberId)) throw new HostelException(ErrorCode.MEMBER_CONFLICT, MessageConstant.MEMBER_CONFLICT);
        int remainScore = memberEntity.getScore() - score;
        if (remainScore < 0) throw new HostelException(ErrorCode.SCORE_NOT_ENOUGH, MessageConstant.SCORE_NOT_ENOUGH);
        memberEntity.setScore(remainScore);
        memberEntity.setRemain(exchangeScoreStrategy.exchange(score)+memberEntity.getRemain());
        consumeDao.save(memberEntity,exchangeScoreStrategy.exchange(score),"Exchange from score");
        return new ResultVo<>(ErrorCode.SUCCESS,MessageConstant.SUCCESS,new MemberVo(memberDao.save(memberEntity)));
    }

    /**
     * Use member remain to pay
     *
     * @param memberId member user id
     * @param payNum   pay number
     * @return {@link ResultVo < MemberVo >}
     */
    @Override
    @Transactional
    public ResultVo<MemberVo> memberPay(int memberId, int payNum) throws HostelException {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity == null) throw new HostelException(ErrorCode.MEMBER_NOT_FOUND, MessageConstant.MEMBER_NOT_FOUND);
        if (!authService.isSelf(memberId)) throw new HostelException(ErrorCode.MEMBER_CONFLICT, MessageConstant.MEMBER_CONFLICT);
        int remain = memberEntity.getRemain() - payNum;
        if (remain < 0) {
            throw new HostelException(ErrorCode.REMAIN_NOT_ENOUGH, MessageConstant.REMAIN_NOT_ENOUGH);
        }
        memberEntity.setRemain(remain);
        memberEntity.setScore(memberEntity.getScore()+payNum);
        memberEntity.setLevel(memberEntity.getLevel()+payNum);
        consumeDao.save(memberEntity,-payNum,"Member card payment");
        return new ResultVo<>(ErrorCode.SUCCESS,MessageConstant.SUCCESS,new MemberVo(memberDao.save(memberEntity)));
    }

    /**
     * Get reservations of a member
     *
     * @param memberId member id
     * @return list of {@link ReserveVo}
     */
    @Override
    @Transactional
    public List<ReserveVo> getMemberReserve(int memberId) {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity == null) return new ArrayList<>();
        return memberEntity.getReserveEntities().stream()
                .map(ReserveVo::new).collect(Collectors.toList());
    }

    /**
     * Get member check info
     *
     * @param memberId member id
     * @return list of {@link CheckVo}
     */
    @Override
    @Transactional
    public List<CheckVo> getMemberCheck(int memberId) {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity == null) return new ArrayList<>();
        return memberEntity.getCheckEntities().stream()
                .map(CheckVo::new).collect(Collectors.toList());
    }

    /**
     * Get member consume records
     *
     * @param memberId member id
     * @return list of {@link ConsumeVo}
     */
    @Override
    @Transactional
    public List<ConsumeVo> getConsumeRecords(int memberId) {
        return memberDao.findById(memberId).getConsumeRecordEntities().stream()
                .map(ConsumeVo::new).collect(Collectors.toList());
    }

    /**
     * Check member active date
     */
    @Override
    @Scheduled(cron = "0 0 2 * * ?")
    public void checkMemberState() {
        Date oneYearBefore = new Date(System.currentTimeMillis());
        oneYearBefore.setYear(oneYearBefore.getYear()-MemberConstant.ACTIVE_YEAR);
        Date twoYearBefore = new Date(System.currentTimeMillis());
        twoYearBefore.setYear(twoYearBefore.getYear()-MemberConstant.PAUSE_YEAR);
        List<MemberEntity> memberEntities = memberDao.findAll();
        memberEntities.stream()
                .filter(memberEntity -> memberEntity.getActiveDate()!=null)
                .filter(memberEntity -> memberEntity.getActiveDate().before(oneYearBefore)
                        && memberEntity.getState() == MemberState.active && memberEntity.getRemain()< MemberConstant.ACTIVE_REMAIN)
                .collect(Collectors.toList())
                .forEach(memberEntity -> pauseMember(memberEntity.getId()));
        memberEntities.stream()
                .filter(memberEntity -> memberEntity.getActiveDate()!=null)
                .filter(memberEntity -> memberEntity.getActiveDate().before(twoYearBefore)
                        && memberEntity.getState() == MemberState.pause)
                .collect(Collectors.toList())
                .forEach(memberEntity -> {
                    try {
                        stopMember(memberEntity.getId());
                    } catch (HostelException e) {
                        e.printStackTrace();
                    }
                });
    }

}
