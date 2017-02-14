package edu.nju.bl.service;

import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.UserVo;
import edu.nju.data.entity.UserEntity;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.UserType;

/**
 * System auth service
 * @author cuihao
 */
public interface AuthService {

    /**
     * Find user by username
     * @param userName user name
     * @return {@link UserVo}
     */
    UserVo findByUserName(String userName);

    /**
     * Find user by userId
     * @param userId user id
     * @return {@link UserVo}
     */
    UserVo findByUserId(int userId);

    /**
     * login to system
     * @param userName user name
     * @param password password
     * @return {@link ResultVo<UserVo>}
     */
    ResultVo<UserVo> login(String userName, String password);

    /**
     * Verify the operation changes the user's own profile
     * @param userId user id to be changed
     * @return is self
     */
    boolean isSelf(int userId);

    /**
     * Get current user.
     * @return current user
     */
    UserEntity getCurrentUser();
}
