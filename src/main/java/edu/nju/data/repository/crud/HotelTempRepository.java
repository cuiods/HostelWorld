package edu.nju.data.repository.crud;

import edu.nju.data.entity.HotelTempEntity;
import edu.nju.util.enums.HotelState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Hotel temp repository
 * @author cuihao
 */
public interface HotelTempRepository extends CrudRepository<HotelTempEntity,Integer>{

    List<HotelTempEntity> findByState(HotelState state);
}
