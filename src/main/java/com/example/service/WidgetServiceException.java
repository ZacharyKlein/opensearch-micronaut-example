package com.example.service;

public class WidgetServiceException extends Throwable {
    public WidgetServiceException(String errorMessage, Exception e) {
        super(errorMessage, e);
    }
}
