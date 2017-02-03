package edu.nju.data.daoImp;

import edu.nju.BaseTest;
import edu.nju.data.dao.HotelDao;
import edu.nju.data.entity.HotelEntity;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.HotelStar;
import edu.nju.util.enums.UserType;
import org.junit.Test;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Hotel dao impl test
 */
public class HotelDaoImplTest extends BaseTest {

    @Resource
    private HotelDao hotelDao;

    @Test
    public void findById() throws Exception {
        System.out.println(hotelDao.findById(17));
    }

    @Test
    public void findByFullName() throws Exception {
        System.out.println(hotelDao.findByFullName("cuihao de hotel"));
    }

    @Test
    public void findByKeyword() throws Exception {
        System.out.println(hotelDao.findByKeyword("cuihao"));
    }

    @Test
    public void findByLocation() throws Exception {
        System.out.println(hotelDao.findByLocation(1,1));
    }

    @Test
    public void findByStar() throws Exception {
        System.out.println(hotelDao.findByStar(HotelStar.five));
    }

    @Test
    public void findAll() throws Exception {
        System.out.println(hotelDao.findAll(0,10,"id", Sort.Direction.ASC).getTotalPages());
    }

    @Test
    public void save() throws Exception {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setName("testHotel");
        hotelEntity.setPassword("123456");
        hotelEntity.setGender(Gender.male);
        hotelEntity.setType(UserType.hotel);
        hotelEntity.setValid((byte) 0);
        hotelEntity.setFullname("test Hotel");
        hotelEntity.setLocation("a location");
        hotelDao.save(hotelEntity);
    }

    @Test
    public void delete() throws Exception {
        hotelDao.delete(19);
    }

}