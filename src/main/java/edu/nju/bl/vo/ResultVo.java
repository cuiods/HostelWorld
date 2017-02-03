package edu.nju.bl.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Operation result info vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class ResultVo<T> {

    private boolean success;
    private String message;
    private T data;

    public ResultVo(T data) {
        this.data = data;
    }

}
