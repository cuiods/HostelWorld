package edu.nju.bl.service;

/**
 * Manager service
 * @author cuihao
 */
public interface ManagerService {

    /**
     * Approve hotel create/edit info
     * @param hotelId hotel id
     */
    void approveHotelTemp(int hotelId);

    /**
     * Set check out record to complete and give hotel money.
     * @param checkId check id
     */
    void completeCheckOutRecord(int checkId);

}
