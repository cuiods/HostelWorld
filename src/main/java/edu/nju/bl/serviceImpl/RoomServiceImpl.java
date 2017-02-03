package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.RoomService;
import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.ReserveVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.RoomVo;
import edu.nju.data.dao.HotelDao;
import edu.nju.data.dao.MemberDao;
import edu.nju.data.dao.ReserveDao;
import edu.nju.data.dao.RoomDao;
import edu.nju.data.entity.HotelEntity;
import edu.nju.data.entity.MemberEntity;
import edu.nju.data.entity.ReserveEntity;
import edu.nju.data.entity.RoomEntity;
import edu.nju.util.enums.BedType;
import edu.nju.util.enums.PayWay;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Room service impl
 * @author cuihao
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomDao roomDao;

    @Resource
    private HotelDao hotelDao;

    @Resource
    private MemberDao memberDao;

    @Resource
    private ReserveDao reserveDao;

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
        if (getRoomNum(roomId, start, end)<=0) {
            return new ResultVo<>(false,"Not enough room.",null);
        }
        ReserveEntity reserveEntity = new ReserveEntity();
        reserveEntity.setRoomEntity(roomDao.findById(roomId));
        reserveEntity.setMemberEntity(memberDao.findById(memberId));
        reserveEntity.setStart(start);
        reserveEntity.setEnd(end);
        reserveEntity.setNameOne(nameOne);
        reserveEntity.setNameTwo(nameTwo);
        reserveEntity.setContact(contact);
        reserveEntity.setEmail(email);
        reserveEntity.setExtra(extra);
        return new ResultVo<>(true,"Reserve succeed",new ReserveVo(reserveDao.save(reserveEntity)));
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
        RoomEntity roomEntity = roomDao.findById(roomId);
        return new RoomVo(roomEntity,getRoomNum(roomId, start, end));
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
    @Transactional
    public int getRoomNum(int roomId, Date start, Date end) {
        RoomEntity roomEntity = roomDao.findById(roomId);
        if (roomEntity==null) return -1;
        long reserve = 0;
        if (roomEntity.getReserveEntities() != null) {
            reserve = roomEntity.getReserveEntities().stream()
                    .filter(reserveEntity -> reserveEntity.getEnd().after(start) && reserveEntity.getStart().before(end))
                    .count();
        }
        long check = 0;
        if (roomEntity.getCheckEntities()!=null) {
            check = roomEntity.getCheckEntities().stream()
                    .filter(checkEntity -> checkEntity.getEnd().after(start) && checkEntity.getStart().before(end))
                    .count();
        }
        return roomEntity.getNumber()-Math.toIntExact(reserve)-Math.toIntExact(check);
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
    @Transactional
    public List<ReserveVo> getHotelReserve(int hotelId) {
        HotelEntity hotelEntity = hotelDao.findById(hotelId);
        if (hotelEntity==null) return new ArrayList<>();
        return hotelEntity.getRoomEntities().stream()
                .flatMap(roomEntity -> roomEntity.getReserveEntities().stream())
                .map(ReserveVo::new)
                .collect(Collectors.toList());
    }

    /**
     * Get member check info
     *
     * @param hotelId member id
     * @return list of {@link CheckVo}
     */
    @Override
    @Transactional
    public List<CheckVo> getHotelCheck(int hotelId) {
        HotelEntity hotelEntity = hotelDao.findById(hotelId);
        if (hotelEntity==null) return new ArrayList<>();
        return hotelEntity.getRoomEntities().stream()
                .flatMap(roomEntity -> roomEntity.getCheckEntities().stream())
                .map(CheckVo::new)
                .collect(Collectors.toList());
    }
}
