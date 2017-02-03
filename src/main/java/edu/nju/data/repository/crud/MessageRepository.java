package edu.nju.data.repository.crud;

import edu.nju.data.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * message entity repository
 * @author cuihao
 */
public interface MessageRepository extends CrudRepository<MessageEntity,Integer>{
}
