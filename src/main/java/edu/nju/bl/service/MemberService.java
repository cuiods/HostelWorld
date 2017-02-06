package edu.nju.bl.service;

import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.MemberVo;
import edu.nju.bl.vo.ReserveVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.util.enums.Gender;

import java.util.List;

/**
 * Member module business logic service
 * @author cuihao
 */
public interface MemberService {

    /**
     * Get member basic person info, not including extra info.
     * @param memberId user id of member
     * @return {@link MemberVo}
     */
    MemberVo getMemberInfo(int memberId);

    /**
     * Create member
     * @return {@link MemberVo}
     */
    MemberVo createMember(String name, String password, String phone, String avatar,
                          Gender gender, String description);

    /**
     * Activate member authority.
     * @param memberId member id
     * @param accountId account id
     * @param money activate money
     * @return {@link ResultVo<MemberVo>}
     */
    ResultVo<MemberVo> activeMember(int memberId, int accountId, int money);

    /**
     * Stop member authority
     * @param memberId member id
     * @return {@link ResultVo<MemberVo>}
     */
    ResultVo<MemberVo> stopMember(int memberId);

    /**
     * Pause member authority
     * @param memberId member id
     * @return {@link ResultVo<MemberVo>}
     */
    ResultVo<MemberVo> pauseMember(int memberId);

    /**
     * Exchange score to remain money
     * @param score score to exchange
     * @return {@link ResultVo<MemberVo>}
     */
    ResultVo<MemberVo> exchangeScore(int memberId, int score);

    /**
     * Use member remain to pay
     * @param memberId member user id
     * @param payNum pay number
     * @return {@link ResultVo<MemberVo>}
     */
    ResultVo<MemberVo> memberPay(int memberId, int payNum);

    /**
     * Get reservations of a member
     * @param memberId member id
     * @return list of {@link ReserveVo}
     */
    List<ReserveVo> getMemberReserve(int memberId);

    /**
     * Get member check info
     * @param memberId member id
     * @return list of {@link CheckVo}
     */
    List<CheckVo> getMemberCheck(int memberId);

}
