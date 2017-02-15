package edu.nju.data.dao;

import edu.nju.data.entity.CheckRecordEntity;
import edu.nju.util.enums.CheckState;

import java.util.List;

/**
 * Check entity dao
 */
public interface CheckDao {

    /**
     * Find check entity by check id
     * @param id id
     * @return {@link CheckRecordEntity}
     */
    CheckRecordEntity findById(int id);

    /**
     * create or update check entity
     * @param checkRecordEntity check entity to save
     * @return saved {@link CheckRecordEntity}
     */
    CheckRecordEntity save(CheckRecordEntity checkRecordEntity);

    List<CheckRecordEntity> findByRoomIdAndState(int roomId, CheckState state);

}
