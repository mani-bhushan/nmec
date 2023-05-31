package com.apps.nmec.exceptionhandlers.advice;

import com.apps.nmec.responses.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@RestControllerAdvice
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {
    // public static final String BAD_REQUEST = "BAD_REQUEST";
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        String errorDetails = exception.getMessage();

        if (exception.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ifx = (InvalidFormatException) exception.getCause();
            if (ifx.getTargetType()!=null && ifx.getTargetType().isEnum()) {
                errorDetails = "Unprocessable Request : " + String.format("Invalid enum value: '%s' for the field: '%s'. The value must be one of: %s.",
                        ifx.getValue(), ifx.getPath().get(0).getFieldName(), Arrays.toString(ifx.getTargetType().getEnumConstants()));
            }
        }
        // String genericMessage = "Unprocessable Request : " + exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse().builder().status(HttpStatus.UNPROCESSABLE_ENTITY).message(errorDetails).build();

        return handleExceptionInternal(exception, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

}