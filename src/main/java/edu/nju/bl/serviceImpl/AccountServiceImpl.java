package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.AccountService;
import edu.nju.bl.vo.AccountVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.data.dao.AccountDao;
import edu.nju.data.dao.UserDao;
import edu.nju.data.entity.AccountEntity;
import edu.nju.data.entity.UserEntity;
import edu.nju.exception.HostelException;
import edu.nju.util.constant.ErrorCode;
import edu.nju.util.constant.MessageConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Account service impl
 * @author cuihao
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private UserDao userDao;

    @Resource
    private AccountDao accountDao;

    /**
     * Get user accounts.
     *
     * @param userId user id
     * @return list of {@link AccountVo}s
     */
    @Override
    @Transactional
    public List<AccountVo> getAccounts(int userId) {
        UserEntity entity = userDao.findById(userId);
        if (entity == null) return new ArrayList<>();
        return entity.getAccountEntities().stream().map(AccountVo::new).collect(Collectors.toList());
    }

    /**
     * Get available accounts
     *
     * @param userId user id
     * @param money  money needed
     * @return list of available {@link AccountVo}
     */
    @Override
    @Transactional
    public List<AccountVo> getAvailableAccounts(int userId, int money) {
        UserEntity entity = userDao.findById(userId);
        if (entity == null) return new ArrayList<>();
        return entity.getAccountEntities().stream()
                .filter(accountEntity -> accountEntity.getBalance() >= money)
                .map(AccountVo::new)
                .collect(Collectors.toList());
    }

    /**
     * Use account to pay
     *
     * @param accountId accountId
     * @param number    pay number
     * @return {@link ResultVo < AccountVo >}
     */
    @Override
    @Transactional
    public ResultVo<AccountVo> accountPay(int accountId, int number) throws HostelException {
        AccountEntity accountEntity = accountDao.findById(accountId);
        if (accountEntity == null)
            throw new HostelException(ErrorCode.ACCOUNT_NOT_FOUND, MessageConstant.ACCOUNT_NOT_FOUND);
        ResultVo<AccountVo> resultVo = new ResultVo<>();
        int balance = accountEntity.getBalance()-number;
        if (balance>=0) {
            accountEntity.setBalance(balance);
            accountDao.save(accountEntity);
            resultVo.setCode(ErrorCode.SUCCESS);
            resultVo.setMessage(MessageConstant.SUCCESS);
            resultVo.setData(new AccountVo(accountEntity));
        } else throw new HostelException(ErrorCode.BALANCE_NOT_ENOUGH, MessageConstant.BALANCE_NOT_ENOUGH);
        return resultVo;
    }

    /**
     * Create default account for user
     *
     * @param userEntity user Entity
     */
    @Override
    public void createAccount(UserEntity userEntity) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserEntity(userEntity);
        accountEntity.setBalance(5000);
        accountDao.save(accountEntity);
    }
}
