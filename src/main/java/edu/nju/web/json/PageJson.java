package edu.nju.web.json;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * a page of data
 */
@Data
public class PageJson {
    @NotNull
    private int page;
    @NotNull
    private int pageSize;
}
