package com.log.utils;

public class Log {
    
    private String projectName;
    private String message;
    private String statusCode;
    private String date;
    private String url;
    private String method;
    private String exceptionName;

    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getExceptionName() {
        return exceptionName;
    }
    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    
}
