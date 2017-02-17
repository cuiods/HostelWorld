package edu.nju.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * General hostel world exception
 */
@Getter
@Setter
@AllArgsConstructor
public class HostelException extends Exception{
    private int code;
    private String message;
}
