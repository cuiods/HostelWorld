package edu.nju.data.daoImp;

import edu.nju.BaseTest;
import edu.nju.data.dao.RoomDao;
import org.junit.Test;

import javax.annotation.Resource;


/**
 * Room dao impl test
 * @author cuihao
 */
public class RoomDaoImplTest extends BaseTest {

    @Resource
    private RoomDao roomDao;

    @Test
    public void findById() throws Exception {
        System.out.println(roomDao.findById(1));
    }

}