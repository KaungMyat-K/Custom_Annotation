package com.log.function;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ExceptionLog {
    
   void saveFilterExceptionLog(String projectName,HttpServletRequest request, HttpServletResponse response, Exception e);
}
