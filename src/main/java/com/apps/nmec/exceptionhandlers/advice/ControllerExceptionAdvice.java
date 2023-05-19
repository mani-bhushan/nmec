package com.apps.nmec.exceptionhandlers.advice;

import com.apps.nmec.exceptionhandlers.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(value = { CustomException.class })
    public ResponseEntity<?> handleCustomException(CustomException ex) {
    	// logger.error("Exception: ", ex.getMessage());
        return new ResponseEntity<>(ex, ex.getHttpStatus());
    }
    
    @ExceptionHandler(value = { NullPointerException.class })
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex) {
    	// logger.error("Exception: ", ex.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
   
}