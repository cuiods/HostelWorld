package edu.nju.data.repository.page;

import edu.nju.data.entity.HotelEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Hotel page and sort repository
 * @author cuihao
 */
public interface HotelPageRepository extends PagingAndSortingRepository<HotelEntity,Integer> {
}
