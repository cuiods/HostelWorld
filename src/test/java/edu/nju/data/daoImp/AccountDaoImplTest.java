package edu.nju.data.daoImp;

import edu.nju.BaseTest;
import edu.nju.data.dao.AccountDao;
import edu.nju.data.dao.UserDao;
import edu.nju.data.entity.AccountEntity;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Account dao test
 */
public class AccountDaoImplTest extends BaseTest {

    @Resource
    private AccountDao accountDao;

    @Resource
    private UserDao userDao;

    @Test
    @Transactional
    @Rollback
    public void save() throws Exception {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(1500);
        accountEntity.setUserEntity(userDao.findByUserName("cuiods"));
        accountDao.save(accountEntity);
    }

    @Test
    public void findById() throws Exception {
        System.out.println(accountDao.findById(1));
    }

}