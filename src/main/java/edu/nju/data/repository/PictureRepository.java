package edu.nju.data.repository;

import edu.nju.data.entity.PictureEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Picture entity repository
 * @author cuihao
 */
public interface PictureRepository extends CrudRepository<PictureEntity,Integer>{
}
