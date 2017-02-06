package edu.nju.data.dao;

import edu.nju.data.entity.HotelTempEntity;

/**
 * Hotel temp dao
 * @author cuihao
 */
public interface HotelTempDao {

    HotelTempEntity findById(int id);

    HotelTempEntity save(HotelTempEntity hotelTempEntity);

    void delete(int id);

}
