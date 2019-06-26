package com.jiayu.controller;

import com.jiayu.aop.LogAop;
import com.jiayu.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ruanjiayu
 * @dateTime 2019/5/7 16:06
 */

@RestController
@RequestMapping("hello")
@Slf4j
public class HelloWorldController {

    @LogAop
    @RequestMapping("say")
    public String say() throws Exception{
            throw new BadRequestException("【出现异常了哈】");
    }

}
