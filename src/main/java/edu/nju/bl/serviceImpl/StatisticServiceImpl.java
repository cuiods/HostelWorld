package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.StatisticService;
import edu.nju.bl.vo.StatisticVo;
import edu.nju.data.dao.CheckDao;
import edu.nju.data.dao.ReserveDao;
import edu.nju.data.dao.RoomDao;
import edu.nju.data.entity.CheckRecordEntity;
import edu.nju.data.entity.ReserveEntity;
import edu.nju.data.entity.RoomEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * statistic service impl
 * @author cuihao
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Resource
    private RoomDao roomDao;

    @Resource
    private ReserveDao reserveDao;

    @Resource
    private CheckDao checkDao;

    @Override
    @Transactional
    public StatisticVo getHotelStatistic(int hotelId) {
        List<RoomEntity> roomEntities = roomDao.findByHotelId(hotelId);
        return doStatistic(roomEntities);
    }

    @Override
    @Transactional
    public StatisticVo getAllStatistic() {
        List<RoomEntity> roomEntities = roomDao.findAll();
        return doStatistic(roomEntities);
    }

    private StatisticVo doStatistic(List<RoomEntity> roomEntities) {
        StatisticVo statisticVo = new StatisticVo();
        Timestamp yesterday = new Timestamp(System.currentTimeMillis()-86400000);
        Timestamp aWeekAgo = new Timestamp(System.currentTimeMillis()-604800000);
        List<CheckRecordEntity> weekChecks = roomEntities.stream()
                .flatMap(roomEntity -> checkDao.findByRoomAndCreateAfter(roomEntity.getId(),aWeekAgo).stream())
                .collect(Collectors.toList());
        List<ReserveEntity> weekReserves = roomEntities.stream()
                .flatMap(roomEntity -> reserveDao.findByRoomAndCreateAfter(roomEntity.getId(),aWeekAgo).stream())
                .collect(Collectors.toList());
        //setReserve
        statisticVo.setReserve(roomEntities.stream()
                .mapToLong(roomEntity -> reserveDao.findByRoomAndCreateAfter(roomEntity.getId(),yesterday).size()).sum());
        List<CheckRecordEntity> todayChecks = weekChecks.stream()
                .filter(checkRecordEntity -> checkRecordEntity.getCreatedAt().after(yesterday)).collect(Collectors.toList());
        //set checks
        statisticVo.setCheck(todayChecks.size());
        long sumToday = todayChecks.stream()
                .mapToLong(checkRecordEntity -> checkRecordEntity.getRoomEntity().getPrice().intValue()).sum();
        long sumWeek = weekChecks.stream()
                .mapToLong(checkRecordEntity -> checkRecordEntity.getRoomEntity().getPrice().intValue()).sum();
        //set sum
        statisticVo.setMoney(sumToday);
        statisticVo.setWeekMoney(sumWeek);
        weekChecks.stream()
                .collect(groupingBy(check -> (check.getCreatedAt().getTime()-aWeekAgo.getTime())/86400000))
                .forEach((aLong, checkRecordEntities) -> statisticVo.setChecks(aLong, checkRecordEntities.size()));
        weekReserves.stream()
                .collect(groupingBy(reserve-> (reserve.getCreatedAt().getTime()-aWeekAgo.getTime())/86400000))
                .forEach((aLong, reserveEntities) -> statisticVo.setReserves(aLong, reserveEntities.size()));
        return statisticVo;
    }
}
