package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.AuthService;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.UserVo;
import edu.nju.data.dao.MemberDao;
import edu.nju.data.dao.UserDao;
import edu.nju.data.entity.MemberEntity;
import edu.nju.data.entity.UserEntity;
import edu.nju.util.constant.ErrorCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Auth service impl
 * @author cuihao
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserDao userDao;

    @Resource
    private MemberDao memberDao;
    /**
     * Find user by username
     *
     * @param userName user name
     * @return {@link UserVo}
     */
    @Override
    public UserVo findByUserName(String userName) {
        UserEntity userEntity = userDao.findByUserName(userName);
        if (userEntity!=null) {
            return new UserVo(userEntity);
        }
        return null;
    }

    /**
     * Find user by userId
     *
     * @param userId user id
     * @return {@link UserVo}
     */
    @Override
    public UserVo findByUserId(int userId) {
        UserEntity userEntity = userDao.findById(userId);
        if (userEntity!=null) {
            return new UserVo(userEntity);
        }
        return null;
    }

    /**
     * login to system
     *
     * @param userName user name
     * @param password password
     * @return {@link ResultVo < UserVo >}
     */
    @Override
    public ResultVo<UserVo> login(String userName, String password) {
        UserEntity userEntity = userDao.findByUserName(userName);
        ResultVo<UserVo> userVoResultVo = new ResultVo<>(null);
        if (userEntity == null) {
            userVoResultVo.setMessage("Cannot find user.");
        } else {
            if (!userEntity.getPassword().equals(password)) {
                userVoResultVo.setMessage("Error password.");
            } else {
                userVoResultVo.setCode(ErrorCode.SUCCESS);
                userVoResultVo.setMessage("Login succeed.");
                userVoResultVo.setData(new UserVo(userEntity));
            }
        }
        return userVoResultVo;
    }

    /**
     * Verify the operation changes the user's own profile
     *
     * @param userId user id to be changed
     * @return is self
     */
    @Override
    public boolean isSelf(int userId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userDao.findByUserName(userDetails.getUsername());
        return userEntity!=null && userEntity.getId() == userId;
    }

    /**
     * Get current user.
     *
     * @return current user
     */
    @Override
    public MemberEntity getCurrentUser() {
        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userDetails!=null) return memberDao.findByName(userDetails.getUsername());
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
