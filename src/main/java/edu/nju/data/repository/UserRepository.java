package edu.nju.data.repository;

import edu.nju.data.entity.UserEntity;
import edu.nju.util.enums.UserType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * user CRUD repository
 * @author cuihao
 */
public interface UserRepository extends CrudRepository<UserEntity,Integer>{

    UserEntity findByName(String name);

    List<UserEntity> findByType(UserType type);

}
