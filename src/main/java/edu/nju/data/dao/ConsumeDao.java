package edu.nju.data.dao;

import edu.nju.data.entity.ConsumeRecordEntity;
import edu.nju.data.entity.MemberEntity;

/**
 * Consume record dao
 * @author cuihao
 */
public interface ConsumeDao {

    ConsumeRecordEntity findById(int id);

    ConsumeRecordEntity save(ConsumeRecordEntity consumeRecordEntity);

    ConsumeRecordEntity save(MemberEntity entity, int number, String description);

}
