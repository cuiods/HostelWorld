package edu.nju.web.json;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Exchange score for remain json
 */
@Data
public class ExchangeJson {
    @Min(value = 100,message = "You should at least exchange 100 score.")
    private int score;
}
