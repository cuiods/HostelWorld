package edu.nju.web.json;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * edit hotel parameter
 */
@Data
public class HotelEditJson {
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String location;
    private double x;
    private double y;
    private String description;
    private String summary;
    @Pattern(regexp = "one|two|three|four|five")
    private String hotelStar;
    private String picture;
}
