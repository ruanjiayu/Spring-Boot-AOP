package com.jiayu.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.LOCKED;

/**
 * @author ruanjiayu
 * @dateTime 2019/6/26 10:33
 */
public class BadSafeException extends RuntimeException{

    private Integer status = LOCKED.value();

    public BadSafeException(String msg){
        super(msg);
    }

    public BadSafeException(HttpStatus status,String msg){
        super(msg);
        this.status = status.value();
    }

}
