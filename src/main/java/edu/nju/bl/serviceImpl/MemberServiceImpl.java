package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.AccountService;
import edu.nju.bl.service.MemberService;
import edu.nju.bl.strategy.ExchangeScoreStrategy;
import edu.nju.bl.vo.*;
import edu.nju.data.dao.MemberDao;
import edu.nju.data.entity.MemberEntity;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.MemberState;
import edu.nju.util.enums.UserType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
        return new MemberVo(memberEntity);
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
    public ResultVo<MemberVo> activeMember(int memberId, int accountId, int money) {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity==null) return new ResultVo<>(false,"Cannot find member.",null);
        ResultVo<AccountVo> accountVoResultVo = accountService.accountPay(accountId,money);
        if (!accountVoResultVo.isSuccess()) {
            return new ResultVo<>(false,accountVoResultVo.getMessage(),null);
        }
        memberEntity.setRemain(memberEntity.getRemain()+money);
        memberEntity.setState(MemberState.active);
        return new ResultVo<>(true,"Active succeed.",new MemberVo(memberDao.save(memberEntity)));
    }

    /**
     * Stop member authority
     *
     * @param memberId member id
     * @return {@link ResultVo < MemberVo >}
     */
    @Override
    public ResultVo<MemberVo> stopMember(int memberId) {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity==null) return new ResultVo<>(false,"Cannot find member.",null);
        memberEntity.setState(MemberState.stop);
        return new ResultVo<>(true,"Member stopped.",new MemberVo(memberDao.save(memberEntity)));
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
        if (memberEntity==null) return new ResultVo<>(false,"Cannot find member.",null);
        memberEntity.setState(MemberState.pause);
        return new ResultVo<>(true,"Member suspended.",new MemberVo(memberDao.save(memberEntity)));
    }

    /**
     * Exchange score to remain money
     *
     * @param score score to exchange
     * @return {@link ResultVo < MemberVo >}
     */
    @Override
    @Transactional
    public ResultVo<MemberVo> exchangeScore(int memberId, int score) {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity == null) return new ResultVo<>(false,"Cannot find member.",null);
        int remainScore = memberEntity.getScore() - score;
        if (remainScore < 0) return new ResultVo<>(false,"Not enough score",null);
        memberEntity.setScore(remainScore);
        memberEntity.setRemain(exchangeScoreStrategy.exchange(score)+memberEntity.getScore());
        return new ResultVo<>(true,"Success",new MemberVo(memberDao.save(memberEntity)));
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
    public ResultVo<MemberVo> memberPay(int memberId, int payNum) {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity == null) return new ResultVo<>(false,"Cannot find member.",null);
        int remain = memberEntity.getRemain() - payNum;
        if (remain < 0) {
            return new ResultVo<>(false,"Not enough member remain",null);
        }
        memberEntity.setRemain(remain);
        return new ResultVo<>(true,"Success",new MemberVo(memberDao.save(memberEntity)));
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
    public List<CheckVo> getMemberCheck(int memberId) {
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity == null) return new ArrayList<>();
        return memberEntity.getCheckEntities().stream()
                .map(CheckVo::new).collect(Collectors.toList());
    }
}
