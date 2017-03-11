package edu.nju.data.daoImp;

import edu.nju.data.dao.ConsumeDao;
import edu.nju.data.entity.ConsumeRecordEntity;
import edu.nju.data.entity.MemberEntity;
import edu.nju.data.repository.crud.ConsumeRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Consume dao impl
 */
@Repository
public class ConsumeDaoImpl implements ConsumeDao {

    @Resource
    private ConsumeRepository consumeRepository;

    @Override
    public ConsumeRecordEntity findById(int id) {
        return consumeRepository.findOne(id);
    }

    @Override
    public ConsumeRecordEntity save(ConsumeRecordEntity consumeRecordEntity) {
        return consumeRepository.save(consumeRecordEntity);
    }

    @Override
    public ConsumeRecordEntity save(MemberEntity entity, int number, String description) {
        ConsumeRecordEntity consumeRecordEntity = new ConsumeRecordEntity();
        consumeRecordEntity.setMemberEntity(entity);
        consumeRecordEntity.setNumber(number);
        consumeRecordEntity.setDescription(description);
        return consumeRepository.save(consumeRecordEntity);
    }
}
