package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.AuthService;
import edu.nju.bl.service.MemberService;
import edu.nju.bl.service.RoomService;
import edu.nju.bl.vo.*;
import edu.nju.data.dao.*;
import edu.nju.data.entity.*;
import edu.nju.util.constant.MemberConstant;
import edu.nju.util.constant.MessageConstant;
import edu.nju.util.enums.*;
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

    @Resource
    private CheckDao checkDao;

    @Resource
    private TenantDao tenantDao;

    @Resource
    private AuthorityDao authorityDao;

    @Resource
    private AuthService authService;

    @Resource
    private MemberService memberService;

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
    @Transactional
    public ResultVo<ReserveVo> reserve(int roomId, int memberId, Date start, Date end,
                                       String nameOne, String nameTwo, String contact, String email, String extra) {
        if (getRoomNum(roomId, start, end)<=0)
            return new ResultVo<>(false, MessageConstant.ROOM_NOT_ENOUGH,null);
        RoomEntity roomEntity = roomDao.findById(roomId);
        MemberEntity memberEntity = memberDao.findById(memberId);
        ResultVo<MemberVo> memberVoResultVo = memberService.memberPay(memberId,roomEntity.getPrice().intValue());
        if (!memberVoResultVo.isSuccess())
            return new ResultVo<>(false,memberVoResultVo.getMessage(),null);
        ReserveEntity reserveEntity = new ReserveEntity();
        reserveEntity.setRoomEntity(roomEntity);
        reserveEntity.setMemberEntity(memberEntity);
        reserveEntity.setStart(start);
        reserveEntity.setEnd(end);
        reserveEntity.setNameOne(nameOne);
        reserveEntity.setNameTwo(nameTwo);
        reserveEntity.setContact(contact);
        reserveEntity.setEmail(email);
        reserveEntity.setExtra(extra);
        reserveEntity.setState(ReserveState.reserve);
        return new ResultVo<>(true,MessageConstant.SUCCESS,new ReserveVo(reserveDao.save(reserveEntity)));
    }

    /**
     * Cancel reserve
     *
     * @param reserveId reserve id
     * @return {@link ResultVo}
     */
    @Override
    @Transactional
    public ResultVo<Boolean> cancelReserve(int reserveId) {
        ReserveEntity reserveEntity = reserveDao.findById(reserveId);
        MemberEntity memberEntity = reserveEntity.getMemberEntity();
        if (!authService.isSelf(memberEntity.getId()))
            return new ResultVo<>(false,MessageConstant.AUTHORITY_FORBIDDEN,null);
        memberEntity.setRemain(memberEntity.getRemain()+reserveEntity.getRoomEntity().getPrice().intValue());
        if (memberEntity.getRemain()>= MemberConstant.ACTIVE_REMAIN) {
            memberEntity.setState(MemberState.active);
            memberEntity.setAuthorityEntities(authorityDao.findMemberActive());
        }
        memberDao.save(memberEntity);
        reserveDao.delete(reserveId);
        return new ResultVo<>(true,MessageConstant.SUCCESS,true);
    }

    /**
     * room checkin service
     *
     * @param roomId room id
     * @param start start date
     * @param end end date
     * @param tenants tenant ids
     * @return {@link ResultVo <CheckVo>}
     */
    @Override
    @Transactional
    public ResultVo<CheckVo> checkIn(int roomId, int reserveId, Date start, Date end,
                                     List<Integer> tenants) {
        CheckRecordEntity checkRecordEntity = new CheckRecordEntity();
        RoomEntity roomEntity = roomDao.findById(roomId);
        if (tenants.size()>roomEntity.getPeople())
            return new ResultVo<>(false,MessageConstant.PEOPLE_MAX,null);
        checkRecordEntity.setRoomEntity(roomEntity);
        checkRecordEntity.setStart(start);
        checkRecordEntity.setEnd(end);
        checkRecordEntity.setState(CheckState.checkIn);
        checkRecordEntity.setPayway(PayWay.cash);
        checkRecordEntity.setTenantEntities(tenants.stream()
                .map(integer -> tenantDao.findById(integer)).collect(Collectors.toList()));
        ReserveEntity reserveEntity = reserveDao.findById(reserveId);
        if (reserveEntity!=null) {
            reserveEntity.setState(ReserveState.checkIn);
            checkRecordEntity.setMemberEntity(reserveEntity.getMemberEntity());
            checkRecordEntity.setPayway(PayWay.member);
            reserveDao.save(reserveEntity);
            return new ResultVo<>(true,MessageConstant.SUCCESS,new CheckVo(checkDao.save(checkRecordEntity)));
        }
        if (getRoomNum(roomId, start, end) <= 0) {
            return new ResultVo<>(false,MessageConstant.ROOM_NOT_ENOUGH,null);
        }
        return new ResultVo<>(true,MessageConstant.SUCCESS,new CheckVo(checkDao.save(checkRecordEntity)));
    }

    /**
     * Room check out service
     *
     * @param checkId check id
     * @return {@link ResultVo < CheckVo >}
     */
    @Override
    @Transactional
    public ResultVo<CheckVo> checkOut(int checkId) {
        CheckRecordEntity checkRecordEntity = checkDao.findById(checkId);
        if (checkRecordEntity == null) {
            return new ResultVo<>(false,MessageConstant.CHECK_NOT_FOUND,null);
        }
        checkRecordEntity.setState(CheckState.complete);
        if (checkRecordEntity.getPayway() == PayWay.member) {
            checkRecordEntity.setState(CheckState.checkOut);
        }
        return new ResultVo<>(true,MessageConstant.SUCCESS,new CheckVo(checkDao.save(checkRecordEntity)));
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
    @Transactional
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
        if (start.before(roomEntity.getStart())||end.after(roomEntity.getEnd())) return -1;
        long reserve = 0;
        if (roomEntity.getReserveEntities() != null) {
            reserve = roomEntity.getReserveEntities().stream()
                    .filter(reserveEntity -> reserveEntity.getDeletedAt() == null && reserveEntity.getState()==ReserveState.reserve)
                    .filter(reserveEntity -> reserveEntity.getEnd().after(start) && reserveEntity.getStart().before(end))
                    .count();
        }
        long check = 0;
        if (roomEntity.getCheckEntities()!=null) {
            check = roomEntity.getCheckEntities().stream()
                    .filter(checkRecordEntity -> checkRecordEntity.getState()==CheckState.checkIn)
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
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setHotelEntity(hotelDao.findById(hotelId));
        roomEntity.setRoomType(roomType);
        roomEntity.setSize(size);
        roomEntity.setPeople(people);
        roomEntity.setBedType(bedType);
        roomEntity.setDescription(description);
        roomEntity.setNumber(number);
        roomEntity.setPrice(price);
        roomEntity.setStart(start);
        roomEntity.setEnd(end);
        return new RoomVo(roomDao.save(roomEntity));
    }

    /**
     * Get unfinished checks
     *
     * @param roomId room id
     * @return list of {@link CheckVo}
     */
    @Override
    public List<CheckVo> getUnfinishedChecks(int roomId) {
        return checkDao.findByRoomIdAndState(roomId,CheckState.checkIn).stream()
                .map(CheckVo::new).collect(Collectors.toList());
    }

}
