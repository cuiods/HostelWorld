package edu.nju.data.repository;

import edu.nju.data.entity.ReserveEntity;
import edu.nju.data.entity.RoomEntity;
import edu.nju.util.enums.ReserveState;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

/**
 * reserve record entity repository
 */
public interface ReserveRepository extends CrudRepository<ReserveEntity,Integer>{

    List<ReserveEntity> findByRoomEntityAndStartGreaterThanAndEndLessThan(RoomEntity roomEntity, Date start, Date end);

    List<ReserveEntity> findByRoomEntityAndState(RoomEntity roomEntity, ReserveState state);
}
