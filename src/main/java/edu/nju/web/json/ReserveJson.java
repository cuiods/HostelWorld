package edu.nju.web.json;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * reserve info to create a reserve entity
 */
@Data
public class ReserveJson {
    @NotNull(message = "Room id cannot be empty.")
    private int roomId;
    @NotNull(message = "Member id cannot be empty.")
    private int memberId;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "Unsupported date format.")
    private String start;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "Unsupported date format.")
    private String end;
    @Length(max = 50, message = "max name length is 50.")
    private String name;
    @Length(min = 11, max = 11, message = "phone number length must be 11.")
    private String contact;
    private String extra;
}
