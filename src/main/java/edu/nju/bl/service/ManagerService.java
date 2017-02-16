package edu.nju.bl.service;

import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.HotelTempVo;
import edu.nju.bl.vo.HotelVo;
import edu.nju.bl.vo.ResultVo;

import java.util.List;

/**
 * Manager service
 * @author cuihao
 */
public interface ManagerService {

    /**
     * Approve hotel create info
     * @param hotelId hotel id
     */
    ResultVo<Boolean> approveHotelCreate(int hotelId);

    /**
     * Get hotels to be approved.
     * @return list of {@link HotelVo}
     */
    List<HotelVo> getCreatedHotel();

    /**
     * Get edited hotel
     * @return list of {@link HotelTempVo}
     */
    List<HotelTempVo> getEditHotel();

    /**
     * Get check record whose hotels have not get the money.
     * @return list of {@link CheckVo}
     */
    List<CheckVo> getUnCompletedCheck();

    /**
     * Approve hotel edit info
     * @param hotelId hotel id
     */
    ResultVo<Boolean> approveHotelEdit(int hotelId);

    /**
     * Set check out record to complete and give hotel money.
     * @param checkId check id
     */
    ResultVo<Boolean> completeCheckOutRecord(int checkId);

    /**
     * Set check out record to complete and give hotel money.
     * @param checkIds check ids
     */
    ResultVo<Boolean> completeCheckOutRecord(List<Integer> checkIds);

}
