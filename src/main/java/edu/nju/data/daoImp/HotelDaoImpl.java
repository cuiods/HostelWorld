package edu.nju.data.daoImp;

import edu.nju.data.dao.HotelDao;
import edu.nju.data.entity.HotelEntity;
import edu.nju.data.repository.crud.HotelRepository;
import edu.nju.data.repository.page.HotelPageRepository;
import edu.nju.util.enums.HotelStar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Hotel entity data access object
 * @author cuihao
 */
@Repository
public class HotelDaoImpl implements HotelDao {

    @Resource
    private HotelPageRepository hotelPageRepository;

    @Resource
    private HotelRepository hotelRepository;

    @Override
    public HotelEntity findById(int id) {
        return hotelRepository.findOne(id);
    }

    @Override
    public HotelEntity findByFullName(String name) {
        return hotelRepository.findByFullname(name);
    }

    @Override
    public List<HotelEntity> findByKeyword(String keyword) {
        return hotelRepository.findByFullnameLike("%"+keyword+"%");
    }

    @Override
    public List<HotelEntity> findByLocation(double x, double y) {
        return hotelRepository.findByLocationXAndLocationY(x,y);
    }

    @Override
    public List<HotelEntity> findByStar(HotelStar star) {
        return hotelRepository.findByStar(star);
    }

    @Override
    public Page<HotelEntity> findAll(int page, int pageSize, String sortColumn, Sort.Direction sortDirection) {
        Sort sort = new Sort(sortDirection, sortColumn);
        Pageable pageable = new PageRequest(page,pageSize,sort);
        return hotelPageRepository.findAll(pageable);
    }

    @Override
    public HotelEntity save(HotelEntity hotelEntity) {
        return hotelRepository.save(hotelEntity);
    }

    @Override
    public void delete(int id) {
        HotelEntity hotelEntity = hotelRepository.findOne(id);
        if (hotelEntity!=null) {
            hotelEntity.setDeletedAt(new Timestamp(System.currentTimeMillis()));
            hotelRepository.save(hotelEntity);
        }
    }
}
