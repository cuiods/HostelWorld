package edu.nju.web.json;

import lombok.Data;

/**
 * a page of data
 */
@Data
public class PageJson {
    private int page;
    private int pageSize;
}
