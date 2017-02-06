package edu.nju.data.daoImp;

import edu.nju.data.dao.HotelTempDao;
import edu.nju.data.entity.HotelTempEntity;
import edu.nju.data.repository.crud.HotelTempRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Hotel temp dao impl
 */
@Repository
public class HotelTempDaoImpl implements HotelTempDao {

    @Resource
    private HotelTempRepository hotelTempRepository;

    @Override
    public HotelTempEntity findById(int id) {
        return hotelTempRepository.findOne(id);
    }

    @Override
    public HotelTempEntity save(HotelTempEntity hotelTempEntity) {
        return hotelTempRepository.save(hotelTempEntity);
    }

    @Override
    public void delete(int id) {
        hotelTempRepository.delete(id);
    }
}
