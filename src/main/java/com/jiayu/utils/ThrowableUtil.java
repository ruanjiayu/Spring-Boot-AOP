package com.jiayu.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * @author ruanjiayu
 * @dateTime 2019/6/26 9:33
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
