package edu.nju.data.dao;

import edu.nju.data.entity.AccountEntity;

/**
 * Account data access object
 * @author cuihao
 */
public interface AccountDao {

    AccountEntity save(AccountEntity entity);

    AccountEntity findById(int id);

}
