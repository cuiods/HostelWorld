package edu.nju.data.daoImp;

import edu.nju.data.dao.MemberDao;
import edu.nju.data.entity.MemberEntity;
import edu.nju.data.repository.crud.MemberRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Member dao impl
 * @author cuihao
 */
@Repository
public class MemberDaoImpl implements MemberDao {

    @Resource
    private MemberRepository memberRepository;

    /**
     * Find member by id
     *
     * @param id member id
     * @return {@link MemberEntity}
     */
    @Override
    public MemberEntity findById(int id) {
        return memberRepository.findOne(id);
    }

    /**
     * Find member by name
     *
     * @param name member name
     * @return {@link MemberEntity}
     */
    @Override
    public MemberEntity findByName(String name) {
        return memberRepository.findByName(name);
    }

    /**
     * Update or create member entity
     *
     * @param memberEntity member entity
     * @return {@link MemberEntity}
     */
    @Override
    public MemberEntity save(MemberEntity memberEntity) {
        return memberRepository.save(memberEntity);
    }

    /**
     * Soft delete
     *
     * @param id member id
     */
    @Override
    public void delete(int id) {

    }

    /**
     * Find all members
     *
     * @return list of {@link MemberEntity}
     */
    @Override
    public List<MemberEntity> findAll() {
        return (List<MemberEntity>) memberRepository.findAll();
    }
}
