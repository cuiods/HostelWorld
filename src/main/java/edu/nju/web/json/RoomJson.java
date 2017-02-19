package edu.nju.web.json;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * create room parameter
 */
@Data
public class RoomJson {
    @NotNull
    private int hotelId;
    @NotNull
    private String roomType;
    @NotNull
    private int size;
    @NotNull
    private int people;
    @NotNull
    private String bedType;
    @NotNull
    private String description;
    @Min(value = 1, message = "At least 1 room.")
    private int number;
    @Min(value = 1, message = "At least 1 yuan.")
    private double price;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "Unsupported date format.")
    private String start;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "Unsupported date format.")
    private String end;
}
