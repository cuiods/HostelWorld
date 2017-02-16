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
     * find check records by state
     * @param checkState checkState
     * @return list of {@link CheckRecordEntity}
     */
    List<CheckRecordEntity> findByState(CheckState checkState);

    /**
     * create or update check entity
     * @param checkRecordEntity check entity to save
     * @return saved {@link CheckRecordEntity}
     */
    CheckRecordEntity save(CheckRecordEntity checkRecordEntity);

    /**
     * Find by room and state
     * @param roomId room id
     * @param state check state
     * @return list of {@link CheckRecordEntity}
     */
    List<CheckRecordEntity> findByRoomIdAndState(int roomId, CheckState state);

}
