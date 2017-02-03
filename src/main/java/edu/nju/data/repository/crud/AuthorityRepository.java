package edu.nju.data.repository.crud;

import edu.nju.data.entity.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Authority repository
 */
public interface AuthorityRepository extends CrudRepository<AuthorityEntity,Integer>{

    AuthorityEntity findByName(String name);

    List<AuthorityEntity> findByNameLike(String name);

    List<AuthorityEntity> findByNameStartingWith(String name);

}
