package edu.nju.data.dao;

import edu.nju.data.entity.RoomEntity;

import java.util.List;

/**
 * Room entity dao
 * @author cuihao
 */
public interface RoomDao {

    /**
     * Find room entity by id
     * @param id room id
     * @return {@link RoomEntity}
     */
    RoomEntity findById(int id);

    RoomEntity save(RoomEntity roomEntity);

    List<RoomEntity> findByHotelId(int hotelId);

    List<RoomEntity> findAll();

}
