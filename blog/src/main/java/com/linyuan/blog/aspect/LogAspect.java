package com.linyuan.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/1 11:16
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.linyuan.blog.controller.*.*(..))")
    public void setLogger(){

    }

    @Before("setLogger()")
    public void doBefore(JoinPoint joinPoint){
//        JoinPoint类，用来获取代理类和被代理类的信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null){

            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String ip = request.getRemoteAddr();
            String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            RequestLog requestLog = new RequestLog(url,ip,classMethod,args);

            logger.info("Request:{}",requestLog);
        }
    }

    @After("setLogger()")
    public void doAfter(){
//        System.out.println("after");
//        logger.info("----------doAfter-----------");
    }
    @AfterReturning(returning = "result",pointcut = "setLogger()")
    public void doAfterReturn(Object result){
        logger.info("Result:{}", result);
    }


    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        private RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
