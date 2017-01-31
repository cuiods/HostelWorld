package edu.nju.data.dao;

import edu.nju.data.entity.HotelEntity;
import edu.nju.util.enums.HotelStar;

import java.util.List;

/**
 * Hotel data access object
 * @author cuihao
 */
public interface HotelDao {

    HotelEntity findById(int id);

    HotelEntity findByFullName(String name);

    List<HotelEntity> findByKeyword(String keyword);

    List<HotelEntity> findByLocation(double x, double y);

    List<HotelEntity> findByStar(HotelStar star);

    HotelEntity save(HotelEntity hotelEntity);

    void delete(int id);

}
