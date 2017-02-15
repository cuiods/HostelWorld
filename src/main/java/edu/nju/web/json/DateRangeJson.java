package edu.nju.web.json;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * date range parameter
 */
@Data
public class DateRangeJson {
    @Pattern(regexp = "/^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])$/",message = "Unsupported date format.")
    private String start;
    @Pattern(regexp = "/^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])$/",message = "Unsupported date format.")
    private String end;
}
