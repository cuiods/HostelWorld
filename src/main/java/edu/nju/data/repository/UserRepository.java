package edu.nju.data.repository;

import edu.nju.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * user CRUD repository
 * @author cuihao
 */
public interface UserRepository extends CrudRepository<UserEntity,Integer>{

    UserEntity findByName(String name);

}
