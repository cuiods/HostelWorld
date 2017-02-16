package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.ManagerService;
import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.HotelTempVo;
import edu.nju.bl.vo.HotelVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.data.dao.AccountDao;
import edu.nju.data.dao.CheckDao;
import edu.nju.data.dao.HotelDao;
import edu.nju.data.dao.HotelTempDao;
import edu.nju.data.entity.*;
import edu.nju.util.constant.MessageConstant;
import edu.nju.util.enums.CheckState;
import edu.nju.util.enums.HotelState;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manager service impl
 * @author cuihao
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private HotelDao hotelDao;

    @Resource
    private HotelTempDao hotelTempDao;

    @Resource
    private CheckDao checkDao;

    @Resource
    private AccountDao accountDao;

    /**
     * Approve hotel create info
     *
     * @param hotelId hotel id
     */
    @Override
    public ResultVo<Boolean> approveHotelCreate(int hotelId) {
        HotelEntity hotelEntity = hotelDao.findById(hotelId);
        if (hotelEntity == null) return new ResultVo<>(false, MessageConstant.HOTEL_NOT_FOUND,null);
        hotelEntity.setState(HotelState.normal);
        hotelDao.save(hotelEntity);
        return new ResultVo<>(true,MessageConstant.SUCCESS,true);
    }

    /**
     * Get hotels to be approved.
     *
     * @return list of {@link HotelVo}
     */
    @Override
    public List<HotelVo> getCreatedHotel() {
        return hotelDao.findByState(HotelState.newly).stream().map(HotelVo::new).collect(Collectors.toList());
    }

    /**
     * Get edited hotel
     *
     * @return list of {@link HotelTempVo}
     */
    @Override
    public List<HotelTempVo> getEditHotel() {
        return hotelTempDao.findByState(HotelState.edit).stream().map(HotelTempVo::new).collect(Collectors.toList());
    }

    /**
     * Get check record whose hotels have not get the money.
     *
     * @return list of {@link CheckVo}
     */
    @Override
    public List<CheckVo> getUnCompletedCheck() {
        return checkDao.findByState(CheckState.checkOut).stream().map(CheckVo::new).collect(Collectors.toList());
    }

    /**
     * Approve hotel edit info
     *
     * @param hotelId hotel id
     */
    @Override
    public ResultVo<Boolean> approveHotelEdit(int hotelId) {
        HotelTempEntity hotelTempEntity = hotelTempDao.findById(hotelId);
        if (hotelTempEntity == null) return new ResultVo<>(false,MessageConstant.EDIT_NOT_FOUND,null);
        hotelTempEntity.setState(HotelState.normal);
        HotelEntity hotelEntity = hotelDao.findById(hotelId);
        if (hotelEntity == null) return new ResultVo<>(false,MessageConstant.HOTEL_NOT_FOUND,null);
        BeanUtils.copyProperties(hotelTempEntity,hotelEntity);
        hotelDao.save(hotelEntity);
        hotelTempDao.save(hotelTempEntity);
        return new ResultVo<>(true,MessageConstant.SUCCESS,true);
    }

    /**
     * Set check out record to complete and give hotel money.
     *
     * @param checkId check id
     */
    @Override
    @Transactional
    public ResultVo<Boolean> completeCheckOutRecord(int checkId) {
        CheckRecordEntity checkRecordEntity = checkDao.findById(checkId);
        if (checkRecordEntity == null)
            return new ResultVo<>(false,MessageConstant.CHECK_NOT_FOUND,null);
        RoomEntity roomEntity = checkRecordEntity.getRoomEntity();
        HotelEntity hotelEntity = roomEntity.getHotelEntity();
        AccountEntity defaultAccount = hotelEntity.getAccountEntities().get(0);
        defaultAccount.setBalance(defaultAccount.getBalance()+roomEntity.getPrice().intValue());
        accountDao.save(defaultAccount);
        checkRecordEntity.setState(CheckState.complete);
        checkDao.save(checkRecordEntity);
        return new ResultVo<>(true,MessageConstant.SUCCESS,true);
    }

    /**
     * Set check out record to complete and give hotel money.
     *
     * @param checkIds check ids
     */
    @Override
    public ResultVo<Boolean> completeCheckOutRecord(List<Integer> checkIds) {
        int success = (int) checkIds.stream()
                .map(this::completeCheckOutRecord).filter(ResultVo::isSuccess).count();
        return new ResultVo<>(success == checkIds.size(), "Completed "+success+" check record(s).",null);
    }
}
