package edu.nju.data.dao;

import edu.nju.data.entity.ReserveEntity;

import java.sql.Date;

/**
 * Reserve entity dao
 * @author cuihao
 */
public interface ReserveDao {

    /**
     * Find reserve entity by id
     * @param id id
     * @return {@link ReserveEntity}
     */
    ReserveEntity findById(int id);

    /**
     * Save reserve entity
     * @param reserveEntity reserve entity to create or update
     * @return saved {@link ReserveEntity}
     */
    ReserveEntity save(ReserveEntity reserveEntity);

    /**
     * find member reserve
     * @return memberReserve
     */
    ReserveEntity findMemberReserve(int memberId, int roomId, Date start, Date end);

    /**
     * Delete reserve(set deleted_at)
     * @param id reserve id
     */
    void delete(int id);

}
