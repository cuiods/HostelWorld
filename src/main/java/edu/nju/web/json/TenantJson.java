package edu.nju.web.json;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * tenant parameter
 */
@Data
public class TenantJson {
    @Length(max = 50, message = "max length of tenant name is 50")
    private String name;
    @Length(min = 11, max = 11, message = "length of tenant phone is 11")
    private String phone;
    @Length(min = 18, max = 18, message = "length of id card is 18")
    private String idCard;
}
