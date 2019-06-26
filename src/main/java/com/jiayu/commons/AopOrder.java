package com.jiayu.commons;

/**
 * aop切面优先级，数值越小越优先,但是优先级越高是最先执行，最后退出，中间的过程是优先级低的切面先进行操作
 * @author ruanjiayu
 * @dateTime 2019/6/25 9:31
 */
public interface AopOrder {
    /**日志的优先级*/
    int LOGIN_ORDER = 5;
}
