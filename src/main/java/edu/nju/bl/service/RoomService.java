package edu.nju.bl.service;

import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.ReserveVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.RoomVo;
import edu.nju.util.enums.BedType;
import edu.nju.util.enums.PayWay;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Hotel room operation service
 * @author cuihao
 */
public interface RoomService {

    /**
     * room reservation service
     * @return {@link ResultVo<ReserveVo>}
     */
    ResultVo<ReserveVo> reserve(int roomId, int memberId, Date start, Date end, String nameOne, String nameTwo,
                                String contact, String email, String extra);

    /**
     * Cancel reserve
     * @param reserveId reserve id
     * @return {@link ResultVo}
     */
    ResultVo<Boolean> cancelReserve(int reserveId);

    /**
     * room checkin service
     * @return {@link ResultVo<CheckVo>}
     */
    ResultVo<CheckVo> checkIn(int roomId, int reserveId, Date start, Date end, List<Integer> tenants);

    /**
     * Room check out service
     * @param checkId check id
     * @return {@link ResultVo<CheckVo>}
     */
    ResultVo<CheckVo> checkOut(int checkId);

    /**
     * Get room info including left room number
     * @param roomId room id
     * @param start start date
     * @param end end date
     * @return {@link RoomVo} with room number
     */
    RoomVo getRoomDetail(int roomId, Date start, Date end);

    /**
     * Get room left number
     * @return room number
     */
    int getRoomNum(int roomId, Date start, Date end);

    /**
     * Create room plan
     * @param hotelId hotel id
     * @param roomType room type
     * @param size room size
     * @param people people number can live in
     * @param bedType bed type
     * @param description room description
     * @param number room number
     * @param price room price
     * @param start start of the room plan
     * @param end end of the room plan
     * @return {@link RoomVo}
     */
    RoomVo createRoom(int hotelId, String roomType, int size, int people, BedType bedType, String description,
                      int number, BigDecimal price, Date start, Date end);


}
