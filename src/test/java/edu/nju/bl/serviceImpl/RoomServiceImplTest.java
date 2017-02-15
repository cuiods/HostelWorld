package edu.nju.bl.serviceImpl;

import edu.nju.BaseTest;
import edu.nju.bl.service.RoomService;
import edu.nju.util.enums.BedType;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Room service test
 * @author cuihao
 */
@WithMockUser(username = "member")
public class RoomServiceImplTest extends BaseTest {

    @Resource
    private RoomService roomService;

    @Test
    public void reserve() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,1,14);
        Date start = new Date(calendar.getTimeInMillis());
        calendar.set(2017,1,15);
        Date end = new Date(calendar.getTimeInMillis());
        System.out.println(roomService.reserve(1,20,start,
                end,"member","","110",
                "c@163.com",""));
    }

    @Test
    public void cancelReserve() throws Exception {
        System.out.println(roomService.cancelReserve(5));
    }

    @Test
    public void checkIn() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,1,15);
        Date start = new Date(calendar.getTimeInMillis());
        calendar.set(2017,1,16);
        Date end = new Date(calendar.getTimeInMillis());
        List<Integer> tenants = new ArrayList<>();
        tenants.add(1);
        System.out.println(roomService.checkIn(1,6,start,end,tenants));
    }

    @Test
    public void checkOut() throws Exception {
        System.out.println(roomService.checkOut(4));
    }

    @Test
    public void getRoomDetail() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,1,15);
        Date start = new Date(calendar.getTimeInMillis());
        calendar.set(2017,1,16);
        Date end = new Date(calendar.getTimeInMillis());
        System.out.println(roomService.getRoomDetail(1,start,end));
    }

    @Test
    public void getRoomNum() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,1,15);
        Date start = new Date(calendar.getTimeInMillis());
        calendar.set(2017,1,16);
        Date end = new Date(calendar.getTimeInMillis());
        System.out.println(roomService.getRoomNum(2,start,end));
    }

    @Test
    public void createRoom() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,0,15);
        Date start = new Date(calendar.getTimeInMillis());
        calendar.set(2017,11,16);
        Date end = new Date(calendar.getTimeInMillis());
        System.out.println(roomService.createRoom(17,"big room",24,3, BedType.big,
                "",10,new BigDecimal(200),start,end));
    }


}