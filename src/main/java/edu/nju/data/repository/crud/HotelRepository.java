package edu.nju.data.repository.crud;

import edu.nju.data.entity.HotelEntity;
import edu.nju.util.enums.HotelStar;
import edu.nju.util.enums.HotelState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Hotel entity repository
 * @author cuihao
 */
public interface HotelRepository extends CrudRepository<HotelEntity,Integer>{

    HotelEntity findByFullname(String fullname);

    List<HotelEntity> findByFullnameLike(String fullname);

    List<HotelEntity> findByFullnameStartingWith(String fullname);

    List<HotelEntity> findByState(HotelState state);

    List<HotelEntity> findByLocationXAndLocationY(double locationX, double locationY);

    List<HotelEntity> findByStar(HotelStar star);

}
