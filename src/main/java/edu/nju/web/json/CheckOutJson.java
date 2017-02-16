package edu.nju.web.json;

import lombok.Data;

/**
 * Check out data(optional)
 */
@Data
public class CheckOutJson {
    private int memberId = -1;
    private String payway;
}
