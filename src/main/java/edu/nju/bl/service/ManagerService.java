package edu.nju.bl.service;

import edu.nju.bl.vo.ResultVo;

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
     * Approve hotel edit info
     * @param hotelId hotel id
     */
    ResultVo<Boolean> approveHotelEdit(int hotelId);

    /**
     * Set check out record to complete and give hotel money.
     * @param checkId check id
     */
    ResultVo<Boolean> completeCheckOutRecord(int checkId);

}
