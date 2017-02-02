package edu.nju.bl.service;

import edu.nju.bl.vo.HotelVo;
import edu.nju.util.enums.HotelStar;

import java.util.List;

/**
 * hotel service
 * @author cuihao
 */
public interface HotelService {

    /**
     * Get list of hotel
     * @param offset offset
     * @param maxSize maxSize
     * @return List of {@link HotelVo}
     */
    List<HotelVo> getHotelList(int offset, int maxSize);

    /**
     * Get detail info of hotel: including rooms
     * @param hotelId hotel user id
     * @return detailed {@link HotelVo} including rooms
     */
    HotelVo getHotelDetail(int hotelId);

    /**
     * Create hotel
     * @param userId userId
     * @param fullName full name of hotel
     * @param location hotel location description
     * @param x hotel location x latitude
     * @param y hotel location y longitude
     * @param description hotel description
     * @param summary description summary
     * @param hotelStar star level of hotel
     * @param picture hotel picture
     * @return {@link HotelVo}
     */
    HotelVo createHotel(int userId, String fullName, String location, double x, double y,
                        String description, String summary, HotelStar hotelStar, String picture);

    /**
     * Edit hotel info
     * @param userId userId
     * @param fullName full name of hotel
     * @param location hotel location description
     * @param x hotel location x latitude
     * @param y hotel location y longitude
     * @param description hotel description
     * @param summary description summary
     * @param hotelStar star level of hotel
     * @param picture hotel picture
     * @return {@link HotelVo}
     */
    HotelVo editHotel(int userId, String fullName, String location, double x, double y,
                      String description, String summary, HotelStar hotelStar, String picture);

}
