package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.AccountService;
import edu.nju.bl.service.AuthService;
import edu.nju.bl.service.HotelService;
import edu.nju.bl.service.RoomService;
import edu.nju.bl.strategy.DiscountStrategy;
import edu.nju.bl.vo.*;
import edu.nju.data.dao.AuthorityDao;
import edu.nju.data.dao.HotelDao;
import edu.nju.data.dao.HotelTempDao;
import edu.nju.data.entity.HotelEntity;
import edu.nju.data.entity.HotelTempEntity;
import edu.nju.data.entity.MemberEntity;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.HotelStar;
import edu.nju.util.enums.HotelState;
import edu.nju.util.enums.UserType;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hotel service impl
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Resource
    private HotelDao hotelDao;

    @Resource
    private AuthorityDao authorityDao;

    @Resource
    private HotelTempDao hotelTempDao;

    @Resource
    private RoomService roomService;

    @Resource
    private AuthService authService;

    @Resource
    private AccountService accountService;

    @Resource
    private DiscountStrategy discountStrategy;

    /**
     * Get list of hotel
     *
     * @param page  page number
     * @param pageSize maxSize
     * @return List of {@link HotelVo}
     */
    @Override
    public Page<HotelVo> getHotelList(int page, int pageSize) {
        Page<HotelEntity> hotelEntities = hotelDao.findAll(page,pageSize,"id", Sort.Direction.ASC);
        return new PageImpl<>(hotelEntities.getContent().stream().map(HotelVo::new).collect(Collectors.toList()),
                hotelEntities.nextPageable(),hotelEntities.getTotalElements());
    }

    /**
     * Get detail info of hotel: including rooms
     *
     * @param hotelId hotel user id
     * @return detailed {@link HotelVo} including rooms
     */
    @Override
    @Transactional
    public HotelVo getHotelDetail(int hotelId) {
        HotelEntity hotelEntity = hotelDao.findById(hotelId);
        Date today = new Date(System.currentTimeMillis());
        MemberEntity memberEntity = authService.getCurrentUser();
        List<RoomVo> roomVos = hotelEntity.getRoomEntities().stream()
                .filter(roomEntity -> roomEntity.getStart().before(today) && roomEntity.getEnd().after(today))
                .map(roomEntity -> new RoomVo(roomEntity,
                        roomService.getRoomNum(roomEntity.getId(),roomEntity.getStart(),roomEntity.getEnd()),
                        memberEntity==null?-1:(int)discountStrategy.getDiscount(memberEntity.getLevel(),roomEntity.getPrice().intValue())))
                .collect(Collectors.toList());
        return new HotelVo(hotelEntity,roomVos);
    }

    /**
     * Create hotel
     *
     * @param fullName    full name of hotel
     * @param location    hotel location description
     * @param x           hotel location x latitude
     * @param y           hotel location y longitude
     * @param description hotel description
     * @param summary     description summary
     * @param hotelStar   star level of hotel
     * @param picture     hotel picture         @return {@link HotelVo}
     */
    @Override
    public HotelVo createHotel(String name, String password, String phone, String avatar, Gender gender,
                               String fullName, String location, double x, double y,
                               String description, String summary, HotelStar hotelStar, String picture) {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setName(name);
        hotelEntity.setPassword(password);
        hotelEntity.setPhone(phone);
        hotelEntity.setAvatar(avatar);
        hotelEntity.setGender(gender);
        hotelEntity.setType(UserType.hotel);
        hotelEntity.setValid((byte) 0);
        hotelEntity.setFullname(fullName);
        hotelEntity.setLocation(location);
        hotelEntity.setLocationX(x);
        hotelEntity.setLocationY(y);
        hotelEntity.setDescription(description);
        hotelEntity.setSummary(summary);
        hotelEntity.setStar(hotelStar);
        hotelEntity.setPicture(picture);
        hotelEntity.setState(HotelState.newly);
        hotelEntity.setAuthorityEntities(authorityDao.findHotelPause());
        HotelEntity hotelEntitySaved = hotelDao.save(hotelEntity);
        accountService.createAccount(hotelEntitySaved);
        return new HotelVo(hotelDao.findById(hotelEntitySaved.getId()));
    }

    /**
     * Edit hotel info
     *
     * @param userId      userId
     * @param fullName    full name of hotel
     * @param location    hotel location description
     * @param x           hotel location x latitude
     * @param y           hotel location y longitude
     * @param description hotel description
     * @param summary     description summary
     * @param hotelStar   star level of hotel
     * @param picture     hotel picture
     * @return {@link HotelVo}
     */
    @Override
    public HotelTempVo editHotel(int userId, String fullName, String location, double x, double y,
                                 String description, String summary, HotelStar hotelStar, String picture) {
        HotelEntity hotelEntity = hotelDao.findById(userId);
        if (hotelEntity == null) return null;
        HotelTempEntity hotelTempEntity = hotelTempDao.findById(userId);
        if (hotelTempEntity == null) hotelTempEntity = new HotelTempEntity();
        hotelTempEntity.setId(hotelEntity.getId());
        hotelTempEntity.setFullname(fullName);
        hotelTempEntity.setLocation(location);
        hotelTempEntity.setLocationX(x);
        hotelTempEntity.setLocationY(y);
        hotelTempEntity.setDescription(description);
        hotelTempEntity.setSummary(summary);
        hotelTempEntity.setStar(hotelStar);
        hotelTempEntity.setPicture(picture);
        hotelTempEntity.setState(HotelState.edit);
        return new HotelTempVo(hotelTempDao.findById(hotelTempDao.save(hotelTempEntity).getId()));
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

    /**
     * Get available rooms of a hotel
     *
     * @param hotelId hotel id
     * @return available rooms
     */
    @Override
    public List<RoomVo> getAvailableRooms(int hotelId) {
        HotelEntity hotelEntity = hotelDao.findById(hotelId);
        Date today = new Date(System.currentTimeMillis());
        MemberEntity memberEntity = authService.getCurrentUser();
        return hotelEntity.getRoomEntities().stream()
                .filter(roomEntity -> roomEntity.getStart().before(today) && roomEntity.getEnd().after(today))
                .map(roomEntity -> new RoomVo(roomEntity,
                        roomService.getRoomNum(roomEntity.getId(),roomEntity.getStart(),roomEntity.getEnd()),
                        memberEntity==null?-1:(int)discountStrategy.getDiscount(memberEntity.getLevel(),roomEntity.getPrice().intValue())))
                .collect(Collectors.toList());
    }
}
