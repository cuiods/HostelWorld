package edu.nju.data.daoImp;

import edu.nju.BaseTest;
import edu.nju.data.dao.CheckDao;
import edu.nju.data.entity.CheckRecordEntity;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Check dao impl test
 */
public class CheckDaoImplTest extends BaseTest {

    @Resource
    private CheckDao checkDao;

    @Test
    public void findById() throws Exception {
        System.out.println(checkDao.findById(1));
    }

    @Test
    public void save() throws Exception {
    }

}