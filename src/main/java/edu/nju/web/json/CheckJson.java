package edu.nju.web.json;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Check record json
 */
@Data
public class CheckJson {
    @NotNull
    private int roomId;
    private int reserveId;
    @Pattern(regexp = "/^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])$/",message = "Unsupported date format.")
    private String start;
    @Pattern(regexp = "/^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])$/",message = "Unsupported date format.")
    private String end;
    @Size(min = 1, max = 6, message = "tenant number should between 1 to 6")
    private List<Integer> tenants;
}
