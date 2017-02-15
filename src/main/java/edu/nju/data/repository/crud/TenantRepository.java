package edu.nju.data.repository.crud;

import edu.nju.data.entity.TenantEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * tenant repository
 */
public interface TenantRepository extends CrudRepository<TenantEntity,Integer>{
}
