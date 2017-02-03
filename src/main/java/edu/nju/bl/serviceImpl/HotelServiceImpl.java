package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.HotelService;
import edu.nju.bl.vo.HotelVo;
import edu.nju.data.dao.HotelDao;
import edu.nju.data.entity.HotelEntity;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.HotelStar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hotel service impl
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Resource
    private HotelDao hotelDao;

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
        return null;
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
    public HotelVo createHotel(String name, String password, String phone, String avatar, Gender gender, String fullName, String location, double x, double y, String description, String summary, HotelStar hotelStar, String picture) {
        return null;
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
    public HotelVo editHotel(int userId, String fullName, String location, double x, double y, String description, String summary, HotelStar hotelStar, String picture) {
        return null;
    }
}
