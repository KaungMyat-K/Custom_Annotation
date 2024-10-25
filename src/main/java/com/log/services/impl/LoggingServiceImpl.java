package com.log.services.impl;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.log.services.LoggingService;
import com.log.utils.Log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Mono;

@Service
public class LoggingServiceImpl implements LoggingService {

    @Autowired
    private WebClient webClient;

    @Override
    public void saveControllerLog(String projectName, String message, String statusCode, String url,
            String method,String exceptionName) {
        Log log = creatLog(projectName, statusCode, url, method, message,exceptionName);
        saveLog(log);
    }

    @Override
    public void saveExceptionLog(String projectName,HttpServletRequest request, HttpServletResponse response, Exception e) {
        Log log = creatLog(projectName, String.valueOf(response.getStatus()), request.getRequestURL().toString(), request.getMethod(), e.getMessage(),e.getClass().getName());
        saveLog(log);
    }

    private Log creatLog(String projectName,String statusCode,String url,String method,String message,String exceptionName){
        Log log = new Log();
            log.setProjectName(projectName);
            log.setStatusCode(statusCode);
            log.setUrl(url);
            log.setMethod(method);
            log.setMessage(message);
            log.setDate(LocalDateTime.now().toString());
            return log;             
    }
    
    private void saveLog(Log log){
        try {
            webClient.post()
                         .uri("/saveLog")
                         .body(Mono.just(log),Log.class)
                         .retrieve()
                         .bodyToMono(String.class)
                         .block();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
