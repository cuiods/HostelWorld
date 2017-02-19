package edu.nju.web.json;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * date range parameter
 */
@Data
public class DateRangeJson {
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}/",message = "Unsupported date format.")
    private String start;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "Unsupported date format.")
    private String end;
}
