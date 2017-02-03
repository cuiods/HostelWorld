package edu.nju.data.repository.crud;

import edu.nju.data.entity.RoomEntity;
import edu.nju.util.enums.BedType;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Room entity repository
 * @author cuihao
 */
public interface RoomRepository extends CrudRepository<RoomEntity, Integer>{

    List<RoomEntity> findByHotelEntity_IdAndBedType(int hotelEntity_Id, BedType bedType);

    List<RoomEntity> findByHotelEntity_IdAndRoomType(int hotelEntity_Id, String roomType);

    List<RoomEntity> findByHotelEntity_IdAndPriceBetween(int hotelEntity_Id, BigDecimal price, BigDecimal price2);

    List<RoomEntity> findByHotelEntity_IdAndPriceGreaterThan(int hotelEntity_Id, BigDecimal price);

}
