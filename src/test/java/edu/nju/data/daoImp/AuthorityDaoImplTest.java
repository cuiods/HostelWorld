package edu.nju.data.daoImp;

import edu.nju.BaseTest;
import edu.nju.data.dao.AuthorityDao;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Authority dao test
 */
public class AuthorityDaoImplTest extends BaseTest {

    @Resource
    private AuthorityDao authorityDao;

    @Test
    public void findById() throws Exception {
        System.out.println(authorityDao.findById(1));
    }

    @Test
    public void findAll() throws Exception {
        System.out.println(authorityDao.findAll());
    }

    @Test
    public void findByName() throws Exception {
        System.out.println(authorityDao.findByName("USER_BASE"));
    }

}