package edu.nju.data.repository;

import edu.nju.data.entity.MemberEntity;
import edu.nju.util.enums.MemberState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * member repository
 * @author cuihao
 */
public interface MemberRepository extends CrudRepository<MemberEntity,Integer>{

    List<MemberEntity> findByState(MemberState state);

    List<MemberEntity> findByLevelBetween(int level1, int level2);

    List<MemberEntity> findByLevelGreaterThan(int level);

    List<MemberEntity> findByScoreBetween(int score1, int score2);

    List<MemberEntity> findByScoreGreaterThan(int score);

    List<MemberEntity> findByRemainBetween(int remain1, int remain2);

    List<MemberEntity> findByRemainGreaterThan(int remain);

}
