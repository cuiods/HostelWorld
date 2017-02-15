package edu.nju.web.controller;

import edu.nju.bl.service.MemberService;
import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.MemberVo;
import edu.nju.bl.vo.ReserveVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.util.enums.Gender;
import edu.nju.web.json.ExchangeJson;
import edu.nju.web.json.MemberJson;
import edu.nju.web.json.TransferJson;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * REST controller of member module
 * @author cuihao
 */
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public MemberVo info(@PathVariable int id) {
        return memberService.getMemberInfo(id);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultVo<MemberVo> createMember(@Valid @RequestBody MemberJson memberJson, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        return new ResultVo<>(true,null,memberService.createMember(memberJson.getName(),
                memberJson.getPassword(),memberJson.getPhone(),memberJson.getAvatar(),
                Gender.valueOf(memberJson.getGender()),memberJson.getDescription()));
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public ResultVo<MemberVo> recharge(@Valid @RequestParam TransferJson transferJson, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        return memberService.transferToRemain(transferJson.getMemberId(),transferJson.getAccountId(),transferJson.getMoney());
    }

    @RequestMapping(value = "/{id}/stop", method = RequestMethod.POST)
    public ResultVo<MemberVo> stop(@PathVariable int id) {
        return memberService.stopMember(id);
    }

    @RequestMapping(value = "/{id}/exchange", method = RequestMethod.POST)
    public ResultVo<MemberVo> exchangeScore(@PathVariable int id, @Valid @RequestBody ExchangeJson exchangeJson,
                                            BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        return memberService.exchangeScore(id,exchangeJson.getScore());
    }

    @RequestMapping(value = "/{id}/reserve", method = RequestMethod.GET)
    public List<ReserveVo> reserveVos(@PathVariable int id) {
        return memberService.getMemberReserve(id);
    }

    @RequestMapping(value = "/{id}/check",method = RequestMethod.GET)
    public List<CheckVo> checkVos(@PathVariable int id) {
        return memberService.getMemberCheck(id);
    }

}
