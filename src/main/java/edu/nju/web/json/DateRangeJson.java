package edu.nju.web.json;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * date range parameter
 */
@Data
public class DateRangeJson {
    private String start;
    private String end;
}
