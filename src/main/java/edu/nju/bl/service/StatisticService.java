package edu.nju.bl.service;

import edu.nju.bl.vo.StatisticVo;

/**
 * generate statistic data
 * @author cuihao
 */
public interface StatisticService {

    StatisticVo getHotelStatistic(int hotelId);

    StatisticVo getAllStatistic();
}
