package edu.nju.bl.serviceImpl;

import edu.nju.BaseTest;
import edu.nju.bl.service.HotelService;
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

    }

    @Test
    public void createHotel() throws Exception {

    }

    @Test
    public void editHotel() throws Exception {

    }

}