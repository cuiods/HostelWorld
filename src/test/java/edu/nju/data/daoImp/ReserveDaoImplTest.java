package edu.nju.data.daoImp;

import edu.nju.BaseTest;
import edu.nju.data.dao.ReserveDao;
import edu.nju.data.entity.ReserveEntity;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Reserve dao test
 */
public class ReserveDaoImplTest extends BaseTest {

    @Resource
    private ReserveDao reserveDao;

    @Test
    public void findById() throws Exception {
        System.out.println(reserveDao.findById(1));
    }

    @Test
    public void save() throws Exception {
        ReserveEntity reserveEntity = reserveDao.findById(1);
        reserveEntity.setContact("110");
        reserveDao.save(reserveEntity);
    }

    @Test
    public void delete() throws Exception {

    }

}