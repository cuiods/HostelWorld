package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.AuthService;
import edu.nju.bl.service.MemberService;
import edu.nju.bl.service.RoomService;
import edu.nju.bl.strategy.DiscountStrategy;
import edu.nju.bl.vo.*;
import edu.nju.data.dao.*;
import edu.nju.data.entity.*;
import edu.nju.exception.HostelException;
import edu.nju.util.constant.ErrorCode;
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
    private ConsumeDao consumeDao;

    @Resource
    private AuthService authService;

    @Resource
    private MemberService memberService;

    @Resource
    private DiscountStrategy discountStrategy;

    /**
     * room reservation service
     *
     * @param roomId room id
     * @param memberId member id
     * @param start start date
     * @param end end date
     * @param nameOne name one of person
     * @param contact contact info
     * @param extra extra info
     * @return {@link ResultVo < ReserveVo >}
     */
    @Override
    @Transactional
    public ResultVo<ReserveVo> reserve(int roomId, int memberId, Date start, Date end,
                                       String nameOne, String contact, String extra) throws HostelException {
        if (getRoomNum(roomId, start, end)<=0)
            throw new HostelException(ErrorCode.ROOM_NOT_ENOUGH, MessageConstant.ROOM_NOT_ENOUGH);
        RoomEntity roomEntity = roomDao.findById(roomId);
        MemberEntity memberEntity = memberDao.findById(memberId);
        int money = (int) discountStrategy.getDiscount(memberEntity.getLevel(),roomEntity.getPrice().intValue());
        memberService.memberPay(memberId,
                (int) discountStrategy.getDiscount(memberEntity.getLevel(),roomEntity.getPrice().intValue()));
        ReserveEntity reserveEntity = new ReserveEntity();
        reserveEntity.setRoomEntity(roomEntity);
        reserveEntity.setMemberEntity(memberEntity);
        reserveEntity.setStart(start);
        reserveEntity.setEnd(end);
        reserveEntity.setName(nameOne);
        reserveEntity.setContact(contact);
        reserveEntity.setExtra(extra);
        reserveEntity.setState(ReserveState.reserve);
        return new ResultVo<>(ErrorCode.SUCCESS,MessageConstant.SUCCESS,
                new ReserveVo(reserveDao.findById(reserveDao.save(reserveEntity).getId()),true));
    }

    /**
     * Cancel reserve
     *
     * @param reserveId reserve id
     * @return {@link ResultVo}
     */
    @Override
    @Transactional
    public ResultVo<Integer> cancelReserve(int reserveId) throws HostelException {
        ReserveEntity reserveEntity = reserveDao.findById(reserveId);
        MemberEntity memberEntity = reserveEntity.getMemberEntity();
        if (!authService.isSelf(memberEntity.getId()))
            throw new HostelException(ErrorCode.AUTHORITY_FORBIDDEN, MessageConstant.AUTHORITY_FORBIDDEN);
        int returnMoney = (int) discountStrategy.getDiscount(memberEntity.getLevel(),reserveEntity.getRoomEntity().getPrice().intValue());
        memberEntity.setRemain(memberEntity.getRemain()+returnMoney);
        consumeDao.save(memberEntity,returnMoney,"Return from reservation");
        if (memberEntity.getRemain()>= MemberConstant.ACTIVE_REMAIN) {
            memberEntity.setState(MemberState.active);
            memberEntity.setAuthorityEntities(authorityDao.findMemberActive());
        }
        memberDao.save(memberEntity);
        reserveDao.delete(reserveId);
        return new ResultVo<>(ErrorCode.SUCCESS,MessageConstant.SUCCESS,memberEntity.getId());
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
    public ResultVo<CheckVo> checkIn(int roomId, int memberId, Date start, Date end,
                                     List<Integer> tenants) throws HostelException {
        CheckRecordEntity checkRecordEntity = new CheckRecordEntity();
        RoomEntity roomEntity = roomDao.findById(roomId);
        if (tenants.size()>roomEntity.getPeople())
            throw new HostelException(ErrorCode.PEOPLE_MAX, MessageConstant.PEOPLE_MAX);
        checkRecordEntity.setRoomEntity(roomEntity);
        checkRecordEntity.setStart(start);
        checkRecordEntity.setEnd(end);
        checkRecordEntity.setState(CheckState.checkIn);
        checkRecordEntity.setPayway(PayWay.cash);
        checkRecordEntity.setTenantEntities(tenants.stream()
                .map(integer -> tenantDao.findById(integer)).collect(Collectors.toList()));
        MemberEntity memberEntity = memberDao.findById(memberId);
        if (memberEntity!=null) {
            checkRecordEntity.setMemberEntity(memberEntity);
            ReserveEntity reserveEntity = reserveDao.findMemberReserve(memberId, roomId, start, end);
            if (reserveEntity!=null) {
                reserveEntity.setState(ReserveState.checkIn);
                checkRecordEntity.setPayway(PayWay.member);
                checkRecordEntity.setPay(1);
                reserveDao.save(reserveEntity);
            } else throw new HostelException(ErrorCode.RESERVATION_NOT_FOUND, MessageConstant.RESERVATION_NOT_FOUND);
        } else if (getRoomNum(roomId, start, end) <= 0) {
            throw new HostelException(ErrorCode.ROOM_NOT_ENOUGH, MessageConstant.ROOM_NOT_ENOUGH);
        }
        return new ResultVo<>(ErrorCode.SUCCESS,MessageConstant.SUCCESS,
                new CheckVo(checkDao.findById(checkDao.save(checkRecordEntity).getId()),true));
    }

    /**
     * Room check out service
     *
     * @param checkId check id
     * @return {@link ResultVo < CheckVo >}
     */
    @Override
    @Transactional
    public ResultVo<CheckVo> checkOut(int checkId, int memberId, PayWay payWay) throws HostelException {
        CheckRecordEntity checkRecordEntity = checkDao.findById(checkId);
        if (payWay!=null) checkRecordEntity.setPayway(payWay);
        if (checkRecordEntity == null) {
            throw new HostelException(ErrorCode.CHECK_NOT_FOUND, MessageConstant.CHECK_NOT_FOUND);
        }
        if (memberId > 0 && payWay == PayWay.member && checkRecordEntity.getPay()==0) {
            memberService.memberPay(memberId,checkRecordEntity.getRoomEntity().getPrice().intValue());
            checkRecordEntity.setPay(1);
        }
        checkRecordEntity.setState(CheckState.complete);
        if (checkRecordEntity.getPayway() == PayWay.member) {
            checkRecordEntity.setState(CheckState.checkOut);
        }
        return new ResultVo<>(ErrorCode.SUCCESS,MessageConstant.SUCCESS,new CheckVo(checkDao.save(checkRecordEntity)));
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
        MemberEntity memberEntity = authService.getCurrentUser();
        if (memberEntity!=null)
            return new RoomVo(roomEntity,getRoomNum(roomId, start, end),
                    (int) discountStrategy.getDiscount(memberEntity.getLevel(),roomEntity.getPrice().intValue()));
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
        return new RoomVo(roomDao.findById(roomDao.save(roomEntity).getId()));
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
