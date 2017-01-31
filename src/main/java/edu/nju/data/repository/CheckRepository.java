package edu.nju.data.repository;

import edu.nju.data.entity.CheckEntity;
import edu.nju.util.enums.CheckState;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * check in and check out record repository
 * @author cuihao
 */
public interface CheckRepository extends CrudRepository<CheckEntity,Integer>{

    List<CheckEntity> findByState(CheckState checkState);

    List<CheckEntity> findByDateBetween(Date start, Date end);

    List<CheckEntity> findByCreatedAtLessThan(Timestamp createdAt);
}
