package edu.nju.data.daoImp;

import edu.nju.data.dao.CheckDao;
import edu.nju.data.entity.CheckRecordEntity;
import edu.nju.data.repository.crud.CheckRepository;
import edu.nju.util.enums.CheckState;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Check entity dao impl
 * @author cuihao
 */
@Repository
public class CheckDaoImpl implements CheckDao {

    @Resource
    private CheckRepository checkRepository;

    /**
     * Find check entity by check id
     *
     * @param id id
     * @return {@link CheckRecordEntity}
     */
    @Override
    public CheckRecordEntity findById(int id) {
        return checkRepository.findOne(id);
    }

    /**
     * find check records by state
     *
     * @param checkState checkState
     * @return list of {@link CheckRecordEntity}
     */
    @Override
    public List<CheckRecordEntity> findByState(CheckState checkState) {
        return checkRepository.findByState(checkState);
    }

    /**
     * create or update check entity
     *
     * @param checkRecordEntity check entity to save
     * @return saved {@link CheckRecordEntity}
     */
    @Override
    public CheckRecordEntity save(CheckRecordEntity checkRecordEntity) {
        return checkRepository.save(checkRecordEntity);
    }

    @Override
    public List<CheckRecordEntity> findByRoomIdAndState(int roomId, CheckState state) {
        return checkRepository.findByRoomEntity_IdAndState(roomId, state);
    }
}
