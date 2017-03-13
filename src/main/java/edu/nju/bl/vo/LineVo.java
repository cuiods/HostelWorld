package edu.nju.bl.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by cuihao on 2017/3/13.
 */
@Data
@AllArgsConstructor
public class LineVo {
    private String name;
    private long reserve;
    private long check;
}
