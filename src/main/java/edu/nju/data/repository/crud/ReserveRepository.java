package edu.nju.data.repository.crud;

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

    List<ReserveEntity> findByMemberEntity_IdAndRoomEntity_IdAndStartAndEnd(int memberEntity_Id, int roomEntity_Id, Date start, Date end);
}
