package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.RoomService;
import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.ReserveVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.RoomVo;
import edu.nju.util.enums.BedType;
import edu.nju.util.enums.PayWay;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Room service impl
 * @author cuihao
 */
@Service
public class RoomServiceImpl implements RoomService {
    /**
     * room reservation service
     *
     * @param roomId room id
     * @param memberId member id
     * @param start start date
     * @param end end date
     * @param nameOne name one of person
     * @param nameTwo name two of person
     * @param contact contact info
     * @param email email of person
     * @param extra extra info
     * @return {@link ResultVo < ReserveVo >}
     */
    @Override
    public ResultVo<ReserveVo> reserve(int roomId, int memberId, Date start, Date end,
                                       String nameOne, String nameTwo, String contact, String email, String extra) {
        return null;
    }

    /**
     * Cancel reserve
     *
     * @param reserveId reserve id
     * @return {@link ResultVo}
     */
    @Override
    public ResultVo<Boolean> cancelReserve(int reserveId) {
        return null;
    }

    /**
     * room checkin service
     *
     * @param roomId room id
     * @param memberId member id
     * @param start start date
     * @param end end date
     * @param nameOne name one of person
     * @param nameTwo name two of person
     * @return {@link ResultVo <CheckVo>}
     */
    @Override
    public ResultVo<CheckVo> checkIn(int roomId, int memberId, Date start, Date end,
                                     String nameOne, String nameTwo) {
        return null;
    }

    /**
     * Room check out service
     *
     * @param checkId check id
     * @param payWay  pay way
     * @return {@link ResultVo < CheckVo >}
     */
    @Override
    public ResultVo<CheckVo> checkOut(int checkId, PayWay payWay) {
        return null;
    }

    /**
     * Get room info including left room number
     *
     * @param roomId room id
     * @param start  start date
     * @param end    end date
     * @return {@link RoomVo} with room number
     */
    @Override
    public RoomVo getRoomDetail(int roomId, Date start, Date end) {
        return null;
    }

    /**
     * Get room left number
     *
     * @param roomId room id
     * @param start start date
     * @param end end date
     * @return room number
     */
    @Override
    public int getRoomNum(int roomId, Date start, Date end) {
        return 0;
    }

    /**
     * Create room plan
     *
     * @param hotelId     hotel id
     * @param roomType    room type
     * @param size        room size
     * @param people      people number can live in
     * @param bedType     bed type
     * @param description room description
     * @param number      room number
     * @param price       room price
     * @param start       start of the room plan
     * @param end         end of the room plan
     * @return {@link RoomVo}
     */
    @Override
    public RoomVo createRoom(int hotelId, String roomType, int size, int people, BedType bedType,
                             String description, int number, BigDecimal price, Date start, Date end) {
        return null;
    }

    /**
     * Get reservations of a member
     *
     * @param hotelId member id
     * @return list of {@link ReserveVo}
     */
    @Override
    public List<ReserveVo> getHotelReserve(int hotelId) {
        return null;
    }

    /**
     * Get member check info
     *
     * @param hotelId member id
     * @return list of {@link CheckVo}
     */
    @Override
    public List<CheckVo> getHotelCheck(int hotelId) {
        return null;
    }
}
