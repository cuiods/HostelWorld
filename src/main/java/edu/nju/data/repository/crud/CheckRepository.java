package edu.nju.data.repository.crud;

import edu.nju.data.entity.CheckRecordEntity;
import edu.nju.util.enums.CheckState;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * check in and check out record repository
 * @author cuihao
 */
public interface CheckRepository extends CrudRepository<CheckRecordEntity,Integer>{

    List<CheckRecordEntity> findByState(CheckState checkState);

    List<CheckRecordEntity> findByCreatedAtLessThan(Timestamp createdAt);
}
