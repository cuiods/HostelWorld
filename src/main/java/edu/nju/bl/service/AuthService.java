package edu.nju.bl.service;

import edu.nju.bl.vo.UserVo;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.UserType;

/**
 * System auth service
 * @author cuihao
 */
public interface AuthService {

    /**
     * Register system user
     * @param name user name
     * @param password user password
     * @param phone phone number
     * @param avatar user avatar
     * @param gender gender
     * @param userType {@link UserType}
     * @return {@link UserVo}
     */
    UserVo createUser(String name, String password, String phone, String avatar,
                      Gender gender, UserType userType);

}
