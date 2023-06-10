package com.apps.nmec.exceptionhandlers.advice;

import com.apps.nmec.exceptionhandlers.CustomException;
import com.apps.nmec.responses.ErrorResponse;
import com.apps.nmec.responses.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(value = { CustomException.class })
    public ResponseEntity<?> handleCustomException(CustomException ex) {
    	// logger.error("Exception: ", ex.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().message(ex.getErrorMessage()).status(ex.getHttpStatus()).build(), HttpStatus.BAD_REQUEST);
    }


    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(value = { NullPointerException.class })
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex) {
    	// logger.error("Exception: ", ex.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<AppError> details = new ArrayList<>();
        AppError validationError =  new AppError(ex.getClass().getName(),ex.getLocalizedMessage());
        details.add(validationError);
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
}