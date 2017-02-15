package edu.nju.data.dao;

import edu.nju.data.entity.TenantEntity;

/**
 * Check in tenant dao
 * @author cuihao
 */
public interface TenantDao {

    /**
     * Find tenant by id
     * @param id tenant id
     * @return {@link TenantEntity}
     */
    TenantEntity findById(int id);

    /**
     * Save tenant
     * @param tenantEntity tenant to save
     * @return {@link TenantEntity}
     */
    TenantEntity save(TenantEntity tenantEntity);

    /**
     * Delete tenant
     * @param id id
     */
    void delete(int id);
}
