package com.modulosocios.ModuloSocios.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.Data;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ErrorTransactionException extends ResponseEntityExceptionHandler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        ErrorResponse errorResponse = new ErrorResponse(timestamp, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Error", ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    private static class ErrorResponse {
        private final String timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final String path;
    }

}
