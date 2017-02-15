package edu.nju.web.json;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Create hotel parameter
 */
@Data
public class HotelCreateJson {
    @Length(max = 255,message = "max length of name is 255")
    private String name;
    @Length(max = 255,message = "max length of password is 255")
    private String password;
    @Length(min = 8,max = 11,message = "length of phone must between 8 and 11")
    private String phone;
    @NotNull
    private String avatar;
    @Pattern(regexp = "/^(male|female)$/")
    private String gender;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String location;
    private double x;
    private double y;
    private String description;
    private String summary;
    @Pattern(regexp = "/^(one|two|three|four|five)$/")
    private String hotelStar;
    @NotNull
    private String picture;
}
