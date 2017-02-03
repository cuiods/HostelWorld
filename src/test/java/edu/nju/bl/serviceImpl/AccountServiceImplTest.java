package edu.nju.bl.serviceImpl;

import edu.nju.BaseTest;
import edu.nju.bl.service.AccountService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Account service test
 * @author cuihao
 */
public class AccountServiceImplTest extends BaseTest {

    @Resource
    private AccountService accountService;

    @Test
    public void getAccounts() throws Exception {
        System.out.println(accountService.getAccounts(16));
    }

    @Test
    public void getAvailableAccounts() throws Exception {
        System.out.println(accountService.getAvailableAccounts(16,2000));
    }

    @Test
    public void accountPay() throws Exception {
        System.out.println(accountService.accountPay(3,2000));
        System.out.println(accountService.accountPay(2,100));
    }

}