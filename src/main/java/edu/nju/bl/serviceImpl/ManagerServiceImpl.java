package edu.nju.bl.serviceImpl;

import edu.nju.bl.service.ManagerService;
import org.springframework.stereotype.Service;

/**
 * Manager service impl
 * @author cuihao
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    /**
     * Approve hotel create/edit info
     *
     * @param hotelId hotel id
     */
    @Override
    public void approveHotelTemp(int hotelId) {

    }

    /**
     * Set check out record to complete and give hotel money.
     *
     * @param checkId check id
     */
    @Override
    public void completeCheckOutRecord(int checkId) {

    }
}
