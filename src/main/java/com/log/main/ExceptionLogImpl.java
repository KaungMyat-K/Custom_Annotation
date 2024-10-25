package com.log.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.log.function.ExceptionLog;
import com.log.services.LoggingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExceptionLogImpl implements ExceptionLog {

    @Autowired
    private LoggingService loggingService;

    @Override
    public void saveFilterExceptionLog(String projectName,HttpServletRequest request, HttpServletResponse response, Exception e) {
       loggingService.saveExceptionLog(projectName, request, response, e);
    }
    
}
