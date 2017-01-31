package edu.nju.data.dao;

import edu.nju.data.entity.AccountEntity;

/**
 * Account data access object
 * @author cuihao
 */
public interface AccountDao {

    /**
     * Update or create an account, depending on whether id is null
     * @param entity account entity
     * @return saved {@link AccountEntity}
     */
    AccountEntity save(AccountEntity entity);

    /**
     * Find account by id
     * @param id id
     * @return {@link AccountEntity}
     */
    AccountEntity findById(int id);

}
