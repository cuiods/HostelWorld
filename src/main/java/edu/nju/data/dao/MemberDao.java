package edu.nju.data.dao;

import edu.nju.data.entity.MemberEntity;

import java.util.List;

/**
 * HostelWorld member entity
 * @author cuihao
 */
public interface MemberDao {

    /**
     * Find member by id
     * @param id member id
     * @return {@link MemberEntity}
     */
    MemberEntity findById(int id);

    /**
     * Find member by name
     * @param name member name
     * @return {@link MemberEntity}
     */
    MemberEntity findByName(String name);

    /**
     * Update or create member entity
     * @param memberEntity member entity
     * @return {@link MemberEntity}
     */
    MemberEntity save(MemberEntity memberEntity);

    /**
     * Soft delete
     * @param id member id
     */
    void delete(int id);

    /**
     * Find all members
     * @return list of {@link MemberEntity}
     */
    List<MemberEntity> findAll();

}
