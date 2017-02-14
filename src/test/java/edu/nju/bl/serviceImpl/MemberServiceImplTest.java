package edu.nju.bl.serviceImpl;

import edu.nju.BaseTest;
import edu.nju.bl.service.MemberService;
import edu.nju.util.enums.Gender;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Member service test
 */
public class MemberServiceImplTest extends BaseTest {

    @Resource
    private MemberService memberService;

    @Test
    public void getMemberInfo() throws Exception {
        System.out.println(memberService.getMemberInfo(16));
    }

    @Test
    public void createMember() throws Exception {
        System.out.println(memberService.createMember("member","123456","13962729161",
                "hahah", Gender.male,"hhh"));
    }

    @Test
    public void activeMember() throws Exception {
        System.out.println(memberService.transferToRemain(20,5,2000));
    }

    @Test
    public void stopMember() throws Exception {
        System.out.println(memberService.stopMember(20));
    }

    @Test
    public void pauseMember() throws Exception {
        System.out.println(memberService.pauseMember(20));
    }

    @Test
    public void exchangeScore() throws Exception {
        System.out.println(memberService.exchangeScore(20,400));
    }

    @Test
    public void memberPay() throws Exception {
        System.out.println(memberService.memberPay(20,1000));
    }

    @Test
    public void getMemberReserve() throws Exception {
        System.out.println(memberService.getMemberReserve(20));
    }

    @Test
    public void getMemberCheck() throws Exception {
        System.out.println(memberService.getMemberCheck(20));
    }

    @Test
    public void checkMemberState() throws Exception {
        memberService.checkMemberState();
    }

}