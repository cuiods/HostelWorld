package edu.nju.bl.serviceImpl;

import edu.nju.BaseTest;
import edu.nju.bl.service.AuthService;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.UserVo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Auth service test
 */
public class AuthServiceImplTest extends BaseTest {

    @Resource
    private AuthService authService;

    @Test
    public void findByUserName() throws Exception {
        System.out.println(authService.findByUserName("cuiods"));
    }

    @Test
    public void findByUserId() throws Exception {
        System.out.println(authService.findByUserId(14));
    }

    @Test
    public void login() throws Exception {
        Assert.assertTrue(!authService.login("cuiods","12345").isSuccess());
        Assert.assertTrue(!authService.login("cuiod","123456").isSuccess());
        System.out.println(authService.login("cuiods","123456"));
    }

}