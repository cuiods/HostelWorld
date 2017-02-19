package edu.nju.web.controller;

import edu.nju.bl.service.AccountService;
import edu.nju.bl.vo.AccountVo;
import edu.nju.web.json.ExchangeJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * REST controller of account module
 * @author cuihao
 */
@Api(value = "/account",description = "User Account API")
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @ApiOperation(value = "Get user accounts",notes = "When creating a user, system will create a bank account.",
            response = AccountVo.class,responseContainer = "List", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "{id}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AccountVo> accounts(@PathVariable("id") int userId) {
        return accountService.getAccounts(userId);
    }

    @ApiOperation(value = "Get available accounts",notes = "Get available accounts which has enough money according to money parameter",
            response = AccountVo.class,responseContainer = "List", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "money", value = "money to pay", required = true, dataType = "ExchangeJson")
    @GetMapping(value = "{id}/available",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AccountVo> accountAvailable(@PathVariable("id") int userId, ExchangeJson money) {
        return accountService.getAvailableAccounts(userId,money.getScore());
    }

}
