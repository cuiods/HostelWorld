package edu.nju.data.repository.crud;

import edu.nju.data.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * User Account Repository.
 * CANNOT UNDERSTAND!!!!!
 * I will create an account with 2000 yuan when creating a user.
 */
public interface AccountRepository extends CrudRepository<AccountEntity,Integer>{

    List<AccountEntity> findByBalanceLessThan(int balance);

}
