package edu.nju.bl.service;

import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.HotelVo;
import edu.nju.bl.vo.ReserveVo;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.HotelStar;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * hotel service
 * @author cuihao
 */
public interface HotelService {

    /**
     * Get list of hotel
     * @param page page number
     * @param pageSize maxSize
     * @return List of {@link HotelVo}
     */
    Page<HotelVo> getHotelList(int page, int pageSize);

    /**
     * Get detail info of hotel: including rooms
     * @param hotelId hotel user id
     * @return detailed {@link HotelVo} including rooms
     */
    HotelVo getHotelDetail(int hotelId);

    /**
     * Create hotel
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
    HotelVo createHotel(String name, String password, String phone, String avatar,
                        Gender gender, String fullName, String location, double x, double y,
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

    /**
     * Get reservations of a member
     * @param hotelId member id
     * @return list of {@link ReserveVo}
     */
    List<ReserveVo> getHotelReserve(int hotelId);

    /**
     * Get member check info
     * @param hotelId member id
     * @return list of {@link CheckVo}
     */
    List<CheckVo> getHotelCheck(int hotelId);

}
