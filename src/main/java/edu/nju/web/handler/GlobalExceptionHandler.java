package edu.nju.web.handler;

import edu.nju.bl.vo.ResultVo;
import edu.nju.exception.HostelException;
import edu.nju.util.constant.ErrorCode;
import edu.nju.util.constant.MessageConstant;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Web exception handler
 * @author cuihao
 */
@ControllerAdvice(basePackages = "edu.nju.web.controller")
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public ResultVo<List> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) throws Exception {
        List<ArgInvalidResult> invalidArguments = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            ArgInvalidResult invalidArgument = new ArgInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        }
        return new ResultVo<>(ErrorCode.WRONG_PARAMETER, MessageConstant.WRONG_PARAMETER,invalidArguments);
    }

    @ExceptionHandler(value = HostelException.class)
    public ResultVo<String> hostelExceptionHandler(HostelException exception) throws Exception {
        return new ResultVo<>(exception.getCode(),exception.getMessage(),exception.getLocalizedMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResultVo<String> accessDeniedExceptionHandler(AccessDeniedException exception)
            throws Exception {
        return new ResultVo<>(ErrorCode.AUTHORITY_FORBIDDEN,MessageConstant.AUTHORITY_FORBIDDEN,exception.getReason()
                +":"+exception.getMessage());
    }

}
