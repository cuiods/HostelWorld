package edu.nju.data.dao;

import edu.nju.data.entity.UserEntity;

import java.util.List;

/**
 * User entity data access object.
 * @author cuihao
 */
public interface UserDao {

    /**
     * Find user by id
     * @param id userId
     * @return {@link UserEntity}
     */
    UserEntity findById(int id);

    /**
     * Find user by userName.
     * user name is unique.
     * @param name user name
     * @return {@link UserEntity}
     */
    UserEntity findByUserName(String name);

    /**
     * Find all users.
     * @return list of {@link UserEntity}
     */
    List<UserEntity> findAll();

    /**
     * Save user entity.<br/>
     * Use spring data {@code save} method, similar to persist and merge.<br/>
     * CREATE or UPDATE object.
     * @param userEntity user entity to be saved
     * @return saved entity
     */
    UserEntity save(UserEntity userEntity);

    /**
     * Delete user: set deleted_at column not null
     * @param id user id
     */
    void delete(int id);
}
