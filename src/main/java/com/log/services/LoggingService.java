package com.log.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoggingService {
    
    void saveControllerLog(String projectName,String message,String statusCode,String url,String method,String exceptionName);
    void saveExceptionLog(String projectName,HttpServletRequest request,HttpServletResponse response,Exception e);
}
