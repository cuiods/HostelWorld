package edu.nju.data.daoImp;

import edu.nju.BaseTest;
import edu.nju.data.dao.AuthorityDao;
import edu.nju.data.dao.UserDao;
import edu.nju.data.entity.AuthorityEntity;
import edu.nju.data.entity.UserEntity;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.UserType;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * user dao test
 */
public class UserDaoImplTest extends BaseTest{

    @Resource
    private UserDao userDao;

    @Resource
    private AuthorityDao authorityDao;

    @Test
    public void findById() throws Exception {
        System.out.println(userDao.findById(12));
    }

    @Test
    @Transactional
    public void findByUserName() throws Exception {
        System.out.println(userDao.findByUserName("cuiods"));
    }

    @Test
    public void findAll() throws Exception {
        System.out.println(userDao.findAll());
    }

    @Test
    @Transactional
    @Rollback
    public void save() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("cuiods");
        userEntity.setPassword("123456");
        userEntity.setPhone("18795859216");
        userEntity.setAvatar("avatar");
        userEntity.setGender(Gender.male);
        userEntity.setType(UserType.member);
        userEntity.setValid((byte) 0);
        ArrayList<AuthorityEntity> entities = new ArrayList<>();
        entities.add(authorityDao.findById(1));
        userEntity.setAuthorityEntities(entities);
        userDao.save(userEntity);
    }

    @Test
    public void delete() throws Exception {
        userDao.delete(12);
    }

}