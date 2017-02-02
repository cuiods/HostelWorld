package edu.nju.bl.service;

import edu.nju.bl.vo.AccountVo;
import edu.nju.bl.vo.ResultVo;

import java.util.List;

/**
 * User account service
 * @author cuihao
 */
public interface AccountService {

    /**
     * Get user accounts.
     * @param userId user id
     * @return list of {@link AccountVo}s
     */
    List<AccountVo> getAccounts(int userId);

    /**
     * Set user default account.
     * @param userId user id
     * @param accountId account id
     * @return {@link AccountVo}
     */
    AccountVo setDefaultAccount(int userId, int accountId);

    /**
     * Use account to pay
     * @param accountId accountId
     * @param number pay number
     * @return {@link ResultVo<AccountVo>}
     */
    ResultVo<AccountVo> accountPay(int accountId, int number);

}
