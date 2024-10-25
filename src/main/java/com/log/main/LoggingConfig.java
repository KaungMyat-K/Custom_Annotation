package com.log.main;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.log.services.LoggingService;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingConfig {
    
    @Autowired
    private LoggingService loggingService;

    @Around("@within(com.log.function.Logging)")
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable{
            ObjectMapper mapper = new ObjectMapper();
            Object object = point.proceed();
            String status = null;
            String url = null;
            String message = null;
            String method = null;

            String data = mapper.writeValueAsString(object);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            Map<String,Object> res = mapper.readValue(data,Map.class);

            String body = mapper.writeValueAsString(res.get("body"));
            status = res.get("statusCodeValue").toString();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                url = request.getRequestURL().toString();
                method = request.getMethod();
            }
            if(!status.equals("200")){
                Map<String,Object> bodyData = mapper.readValue(body,Map.class);
                message = bodyData.get("message").toString();
            }
                loggingService.saveControllerLog("",message,status,url,method,"");
            return object;  
    }

    @AfterThrowing(pointcut = "@within(com.log.function.Logging)",throwing = "ex")
    public void exceptionLog(Exception ex){
            String status = null;
            String url = null;
            String message = null;
            String method = null;
            String exceptionName = null;
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            status = "500";
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                url = request.getRequestURL().toString();
                method = request.getMethod();
            }
                message = ex.getMessage();
                exceptionName = ex.getClass().getName();
                loggingService.saveControllerLog("", message, status, url, method, exceptionName);
            
    }
}
