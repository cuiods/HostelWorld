package edu.nju.data.daoImp;

import edu.nju.data.dao.ReserveDao;
import edu.nju.data.entity.ReserveEntity;
import edu.nju.data.repository.crud.ReserveRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Reserve dao impl
 * @author cuihao
 */
@Repository
public class ReserveDaoImpl implements ReserveDao {

    @Resource
    private ReserveRepository reserveRepository;
    /**
     * Find reserve entity by id
     *
     * @param id id
     * @return {@link ReserveEntity}
     */
    @Override
    public ReserveEntity findById(int id) {
        return reserveRepository.findOne(id);
    }

    /**
     * Save reserve entity
     *
     * @param reserveEntity reserve entity to create or update
     * @return saved {@link ReserveEntity}
     */
    @Override
    public ReserveEntity save(ReserveEntity reserveEntity) {
        return reserveRepository.save(reserveEntity);
    }

    /**
     * Delete reserve(set deleted_at)
     *
     * @param id reserve id
     */
    @Override
    public void delete(int id) {
        ReserveEntity reserveEntity = reserveRepository.findOne(id);
        if (reserveEntity!=null) {
            reserveEntity.setDeletedAt(new Timestamp(System.currentTimeMillis()));
            reserveRepository.save(reserveEntity);
        }
    }
}
