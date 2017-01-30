package edu.nju.data.daoImp;

import edu.nju.data.dao.UserDao;
import edu.nju.data.entity.UserEntity;
import edu.nju.data.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * {@link UserDao} implement
 * @author cuihao
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private UserRepository userRepository;

    /**
     * Find user by id
     *
     * @param id userId
     * @return {@link UserEntity}
     */
    @Override
    public UserEntity findById(int id) {
        return userRepository.findOne(id);
    }

    /**
     * Find user by userName.
     * user name is unique.
     *
     * @param name user name
     * @return {@link UserEntity}
     */
    @Override
    public UserEntity findByUserName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * Find all users.
     *
     * @return list of {@link UserEntity}
     */
    @Override
    public List<UserEntity> findAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    /**
     * Save user entity.<br/>
     * Use spring data {@code save} method, similar to merge and persist.<br/>
     * CREATE or UPDATE object.
     *
     * @param userEntity user entity to be saved
     * @return saved entity
     */
    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    /**
     * Delete user: set deleted_at column not null
     *
     * @param id user id
     */
    @Override
    public void delete(int id) {
        UserEntity userEntity = userRepository.findOne(id);
        if (userEntity!=null) {
            userEntity.setDeletedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(userEntity);
        }
    }
}
