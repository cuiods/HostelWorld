package edu.nju.data.dao;

import edu.nju.data.entity.AuthorityEntity;

import java.util.List;

/**
 * AuthorityConstant data access object
 * @author cuihao
 */
public interface AuthorityDao {

    /**
     * Find authorities by id
     * @param id id
     * @return {@link AuthorityEntity}
     */
    AuthorityEntity findById(int id);

    /**
     * Find all authorities
     * @return list of {@link AuthorityEntity}
     */
    List<AuthorityEntity> findAll();

    /**
     * Find authority by name
     * @param name name of auth
     * @return {@link AuthorityEntity}
     */
    AuthorityEntity findByName(String name);

}
