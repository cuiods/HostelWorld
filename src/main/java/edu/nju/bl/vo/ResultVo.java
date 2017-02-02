package edu.nju.bl.vo;

import lombok.Data;

/**
 * Operation result info vo
 * @author cuihao
 */
@Data
public class ResultVo<T> {

    private boolean result;
    private String message;
    private T data;

    public ResultVo(T data) {
        this.data = data;
    }

}
