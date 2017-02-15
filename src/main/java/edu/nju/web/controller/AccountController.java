package edu.nju.web.controller;

import edu.nju.bl.service.AccountService;
import edu.nju.bl.vo.AccountVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * REST controller of account module
 * @author cuihao
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public List<AccountVo> accounts(@PathVariable("id") int userId) {
        return accountService.getAccounts(userId);
    }

    @RequestMapping(value = "{id}/available", method = RequestMethod.GET)
    public List<AccountVo> accountAvailable(@PathVariable("id") int userId, int money) {
        return accountService.getAvailableAccounts(userId,money);
    }

}
