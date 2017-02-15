package edu.nju.bl.serviceImpl;

import edu.nju.BaseTest;
import edu.nju.bl.service.HotelService;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.HotelStar;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Hotel service test
 */
public class HotelServiceImplTest extends BaseTest {

    @Resource
    private HotelService hotelService;

    @Test
    public void getHotelList() throws Exception {
        System.out.println(hotelService.getHotelList(0,10));
    }

    @Test
    public void getHotelDetail() throws Exception {
        System.out.println(hotelService.getHotelDetail(17));
    }

    @Test
    public void createHotel() throws Exception {
        System.out.println(hotelService.createHotel("testHotel","123456","12345678","",
                Gender.female,"fullname hotel","here",1,1,"description",
                "summary", HotelStar.one,""));
    }

    @Test
    public void editHotel() throws Exception {
        System.out.println(hotelService.editHotel(21,"editFullname","here",1,1,
                "description","summary",HotelStar.three,""));
    }

}