package edu.nju.bl.serviceImpl;

import edu.nju.BaseTest;
import edu.nju.bl.service.StatisticService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017/3/13.
 */
public class StatisticServiceImplTest extends BaseTest {

    @Resource
    private StatisticService statisticService;

    @Test
    public void getHotelStatistic() throws Exception {
        System.out.println(statisticService.getHotelStatistic(17));
    }

    @Test
    public void getAllStatistic() throws Exception {
        System.out.println("haha"+statisticService.getAllStatistic());
    }

}