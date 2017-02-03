package edu.nju.data.dao;

import edu.nju.data.entity.HotelEntity;
import edu.nju.util.enums.HotelStar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

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

    Page<HotelEntity> findAll(int page, int pageSize, String sortColumn, Sort.Direction sortDirection);

    HotelEntity save(HotelEntity hotelEntity);

    void delete(int id);

}
