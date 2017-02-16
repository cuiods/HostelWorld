package edu.nju.data.dao;

import edu.nju.data.entity.HotelTempEntity;
import edu.nju.util.enums.HotelState;

import java.util.List;

/**
 * Hotel temp dao
 * @author cuihao
 */
public interface HotelTempDao {

    HotelTempEntity findById(int id);

    List<HotelTempEntity> findByState(HotelState hotelState);

    HotelTempEntity save(HotelTempEntity hotelTempEntity);

    void delete(int id);

}
