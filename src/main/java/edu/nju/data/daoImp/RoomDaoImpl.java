package edu.nju.data.daoImp;

import edu.nju.data.dao.RoomDao;
import edu.nju.data.entity.RoomEntity;
import edu.nju.data.repository.crud.RoomRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Room dao impl
 * @author cuihao
 */
@Service
public class RoomDaoImpl implements RoomDao {

    @Resource
    private RoomRepository roomRepository;

    /**
     * Find room entity by id
     *
     * @param id room id
     * @return {@link RoomEntity}
     */
    @Override
    public RoomEntity findById(int id) {
        return roomRepository.findOne(id);
    }

    @Override
    public RoomEntity save(RoomEntity roomEntity) {
        return roomRepository.save(roomEntity);
    }
}
