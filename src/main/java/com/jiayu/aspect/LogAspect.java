package com.jiayu.aspect;

import com.jiayu.aop.LogAop;
import com.jiayu.commons.AopOrder;
import com.jiayu.exceptions.BadRequestException;
import com.jiayu.exceptions.BadSafeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ruanjiayu
 * @dateTime 2019/5/7 15:54
 */
@Component
@Aspect
@Order(AopOrder.LOGIN_ORDER)
@Slf4j
public class LogAspect {


    /**
     *  配置切入点
     */
    @Pointcut("@annotation(com.jiayu.aop.LogAop)")
    public void logPointcut(){
        // 该方法无方法体，主要是让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知，使用方法在logPointcut()上注册的切入点
     * @param joinPoint
     * @return
     */
   @Around("logPointcut()")
   public Object logAround(ProceedingJoinPoint joinPoint) throws Exception {
       Object result = null;
       try {
           result = joinPoint.proceed();
       } catch (BadRequestException e) {
           log.info("【BadRequestException】");
           throw new BadRequestException(e.getMessage());
       }
       // 一旦上面的触发了，那么下面的异常就不会再执行了
       catch (Throwable e){
           log.info("【Throwable】");
           throw new Exception(e.getMessage());
       }
       //可以进行的操作 一般我们放置在service层进行操作
       MethodSignature signature = (MethodSignature) joinPoint.getSignature();
       Method method = signature.getMethod();
       LogAop logAop = method.getAnnotation(LogAop.class);
       // 方法路径
       String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
       //参数值
       Object[] argValues = joinPoint.getArgs();

       log.info("【程序一切正常】");
       return result;
   }

    /**
     * 程序出现异常时候会进行的操作,具体的异常捕获查看方法里面类名,目前只会接受切面中抛出的BadRequestException异常
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointcut()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, BadRequestException e){
        log.info("【切面获取到程序出现异常】:",e);
    }
}
