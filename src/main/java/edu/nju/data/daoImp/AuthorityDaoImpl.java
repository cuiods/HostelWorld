package edu.nju.data.daoImp;

import edu.nju.data.dao.AuthorityDao;
import edu.nju.data.entity.AuthorityEntity;
import edu.nju.data.repository.crud.AuthorityRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Authority dao impl
 * @author cuihao
 */
@Repository
public class AuthorityDaoImpl implements AuthorityDao {

    @Resource
    private AuthorityRepository authorityRepository;

    /**
     * Find authorities by id
     *
     * @param id id
     * @return {@link AuthorityEntity}
     */
    @Override
    public AuthorityEntity findById(int id) {
        return authorityRepository.findOne(id);
    }

    /**
     * Find all authorities
     *
     * @return list of {@link AuthorityEntity}
     */
    @Override
    public List<AuthorityEntity> findAll() {
        return (List<AuthorityEntity>) authorityRepository.findAll();
    }

    /**
     * Find authority by name
     *
     * @param name name of auth
     * @return {@link AuthorityEntity}
     */
    @Override
    public AuthorityEntity findByName(String name) {
        return authorityRepository.findByName(name);
    }
}
