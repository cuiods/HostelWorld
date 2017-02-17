package edu.nju.bl.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Operation result info vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> {

    private int code;
    private String message;
    private T data;

    public ResultVo(T data) {
        this.data = data;
    }

}
