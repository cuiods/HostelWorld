package edu.nju.data.daoImp;

import edu.nju.data.dao.AccountDao;
import edu.nju.data.entity.AccountEntity;
import edu.nju.data.repository.crud.AccountRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * {@link AccountDao} impl
 * @author cuihao
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Resource
    private AccountRepository accountRepository;

    @Override
    public AccountEntity save(AccountEntity entity) {
        return accountRepository.save(entity);
    }

    @Override
    public AccountEntity findById(int id) {
        return accountRepository.findOne(id);
    }
}
