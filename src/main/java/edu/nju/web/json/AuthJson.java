package edu.nju.web.json;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * auth json
 */
@Data
public class AuthJson {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
