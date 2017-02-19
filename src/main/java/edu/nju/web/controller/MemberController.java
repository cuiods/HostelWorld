package edu.nju.web.controller;

import edu.nju.bl.service.MemberService;
import edu.nju.bl.vo.*;
import edu.nju.exception.HostelException;
import edu.nju.util.constant.ErrorCode;
import edu.nju.util.enums.Gender;
import edu.nju.web.json.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * REST controller of member module
 * @author cuihao
 */
@Api(value = "/member", description = "Application member API")
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @ApiOperation(value = "Get member list",notes = "Get a page of memberVo",
            response = Page.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "pageJson", value = "page setting", required = true, dataType = "PageJson")
    @PostMapping(value = "/list", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<MemberVo> memberList(@Valid @RequestBody PageJson pageJson) {
        return memberService.getMemberList(pageJson.getPage(),pageJson.getPageSize());
    }

    @ApiOperation(value = "Get member info",notes = "Get detail info of a member.",
            response = MemberVo.class,responseContainer = "ResultVo", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{id}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MemberVo info(@PathVariable int id) {
        return memberService.getMemberInfo(id);
    }

    @ApiOperation(value = "Create member",notes = "Create a new user with member authority.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "memberJson", value = "member data", required = true, dataType = "MemberJson")
    @PostMapping(value = "",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<MemberVo> createMember(@Valid @RequestBody MemberJson memberJson) {
        return new ResultVo<>(ErrorCode.SUCCESS,null,memberService.createMember(memberJson.getName(),
                memberJson.getPassword(),memberJson.getPhone(),memberJson.getAvatar(),
                Gender.valueOf(memberJson.getGender()),memberJson.getDescription()));
    }

    @ApiOperation(value = "Edit member",notes = "Edit member info.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "memberEditJson", value = "member edit data", required = true, dataType = "MemberEditJson")
    @PutMapping(value = "/edit",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<MemberVo> editMember(@Valid @RequestBody MemberEditJson memberEditJson) {
        return new ResultVo<>(ErrorCode.SUCCESS,null,memberService.editMember(memberEditJson.getMemberId(),
                memberEditJson.getPassword(),memberEditJson.getAvatar(),Gender.valueOf(memberEditJson.getGender()),
                memberEditJson.getDescription()));
    }

    @ApiOperation(value = "Recharge member account",notes = "Transfer money from bank account to member account.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "transferJson", value = "transfer data", required = true, dataType = "TransferJson")
    @PostMapping(value = "/recharge", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<MemberVo> recharge(@Valid @RequestBody TransferJson transferJson) throws HostelException {
        return memberService.transferToRemain(transferJson.getMemberId(),transferJson.getAccountId(),transferJson.getMoney());
    }

    @ApiOperation(value = "Stop member",notes = "Stop member.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/{id}/stop", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<MemberVo> stop(@PathVariable int id) throws HostelException {
        return memberService.stopMember(id);
    }

    @ApiOperation(value = "Exchange member score",notes = "Exchange member score for member balance.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "exchangeJson", value = "exchange number", required = true, dataType = "ExchangeJson")
    @PostMapping(value = "/{id}/exchange", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<MemberVo> exchangeScore(@PathVariable int id, @Valid @RequestBody ExchangeJson exchangeJson) throws HostelException {
        return memberService.exchangeScore(id,exchangeJson.getScore());
    }

    @ApiOperation(value = "Get member reservations",notes = "Get member reservations.",
            response = ReserveVo.class, responseContainer = "List",produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{id}/reserve", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReserveVo> reserveVos(@PathVariable int id) {
        return memberService.getMemberReserve(id);
    }

    @ApiOperation(value = "Get member check records",notes = "Get member check in records.",
            response = CheckVo.class, responseContainer = "List",produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{id}/check",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CheckVo> checkVos(@PathVariable int id) {
        return memberService.getMemberCheck(id);
    }

    @ApiOperation(value = "Get member consume records",notes = "Get member consume records.",
            response = ConsumeVo.class, responseContainer = "List",produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{id}/consume",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ConsumeVo> consumeVos(@PathVariable int id) {
        return memberService.getConsumeRecords(id);
    }

}
