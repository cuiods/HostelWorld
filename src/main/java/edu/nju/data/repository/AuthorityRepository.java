package edu.nju.data.repository;

import edu.nju.data.entity.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Authority repository
 */
public interface AuthorityRepository extends CrudRepository<AuthorityEntity,Integer>{

    AuthorityEntity findByName(String name);

}
