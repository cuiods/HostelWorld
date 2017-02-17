package edu.nju.bl.service;

import edu.nju.bl.vo.AccountVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.data.entity.UserEntity;
import edu.nju.exception.HostelException;

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
     * Get available accounts
     * @param userId user id
     * @param money money needed
     * @return list of available {@link AccountVo}
     */
    List<AccountVo> getAvailableAccounts(int userId, int money);

    /**
     * Use account to pay
     * @param accountId accountId
     * @param number pay number
     * @return {@link ResultVo<AccountVo>}
     */
    ResultVo<AccountVo> accountPay(int accountId, int number) throws HostelException;

    /**
     * Create default account for user
     * @param userEntity user Entity
     */
    void createAccount(UserEntity userEntity);

}
