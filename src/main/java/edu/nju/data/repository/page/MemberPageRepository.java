package edu.nju.data.repository.page;

import edu.nju.data.entity.MemberEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Get member by page
 * @author cuihao
 */
public interface MemberPageRepository extends PagingAndSortingRepository<MemberEntity,Integer>{
}
