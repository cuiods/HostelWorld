package edu.nju.web.controller;

import edu.nju.bl.service.AuthService;
import edu.nju.bl.vo.AccountVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.UserVo;
import edu.nju.util.constant.ErrorCode;
import edu.nju.util.constant.MessageConstant;
import edu.nju.web.json.AuthJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(value = "/auth",description = "Auth API")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @ApiOperation(value = "User login auth",notes = "If succeed, return user info.",
            response = UserVo.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "authJson", value = "user login data", required = true, dataType = "AuthJson")
    @PostMapping(value = "",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<UserVo> login(@Valid @RequestBody AuthJson authJson) {
        return authService.login(authJson.getUsername(),authJson.getPassword());
    }

    @ApiOperation(value = "User logout auth",notes = "User logout.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Void> logout(HttpServletRequest request) throws ServletException {
        request.getSession(true).invalidate();
        return new ResultVo<>(ErrorCode.SUCCESS, MessageConstant.SUCCESS,null);
    }

}
