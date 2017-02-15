package edu.nju.bl.serviceImpl;

import edu.nju.BaseTest;
import edu.nju.bl.service.ManagerService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Member service test
 * @author cuihao
 */
public class ManagerServiceImplTest extends BaseTest {

    @Resource
    private ManagerService managerService;

    @Test
    public void approveHotelCreate() throws Exception {
        System.out.println(managerService.approveHotelCreate(21));
    }

    @Test
    public void approveHotelEdit() throws Exception {
        System.out.println(managerService.approveHotelEdit(21));
    }

    @Test
    public void completeCheckOutRecord() throws Exception {

    }

}