package com.jiayu.exceptions.handle;

import com.jiayu.exceptions.BadRequestException;
import com.jiayu.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 实现全局异常处理
 * @author ruanjiayu
 * @dateTime 2019/6/26 9:28
 */
@Slf4j
/**开启全局异常处理*/
@RestControllerAdvice
public class GlobalExceptionHandle {

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiError apiError = new ApiError(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理自定义异常，value内的值是异常值
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiError apiError = new ApiError(e.getStatus(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 统一返回
     * @param apiError
     * @return
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<ApiError>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
