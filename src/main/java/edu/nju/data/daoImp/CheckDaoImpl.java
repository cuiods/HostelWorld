package edu.nju.data.daoImp;

import edu.nju.data.dao.CheckDao;
import edu.nju.data.entity.CheckRecordEntity;
import edu.nju.data.repository.crud.CheckRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

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
     * create or update check entity
     *
     * @param checkRecordEntity check entity to save
     * @return saved {@link CheckRecordEntity}
     */
    @Override
    public CheckRecordEntity save(CheckRecordEntity checkRecordEntity) {
        return checkRepository.save(checkRecordEntity);
    }
}
