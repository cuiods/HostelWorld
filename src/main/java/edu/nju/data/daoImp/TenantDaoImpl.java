package edu.nju.data.daoImp;

import edu.nju.data.dao.TenantDao;
import edu.nju.data.entity.TenantEntity;
import edu.nju.data.repository.crud.TenantRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Tenant dao impl
 * @author cuihao
 */
@Repository
public class TenantDaoImpl implements TenantDao {

    @Resource
    private TenantRepository tenantRepository;

    /**
     * Find tenant by id
     *
     * @param id tenant id
     * @return {@link TenantEntity}
     */
    @Override
    public TenantEntity findById(int id) {
        return tenantRepository.findOne(id);
    }

    /**
     * Save tenant
     *
     * @param tenantEntity tenant to save
     * @return {@link TenantEntity}
     */
    @Override
    public TenantEntity save(TenantEntity tenantEntity) {
        return tenantRepository.save(tenantEntity);
    }

    /**
     * Delete tenant
     *
     * @param id id
     */
    @Override
    public void delete(int id) {
        tenantRepository.delete(id);
    }
}
