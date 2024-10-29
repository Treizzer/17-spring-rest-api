package com.treizer.spring_boot_rest.presentation.advice;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}
