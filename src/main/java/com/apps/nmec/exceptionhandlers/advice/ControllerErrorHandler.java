package com.apps.nmec.exceptionhandlers.advice;

import com.apps.nmec.responses.AppError;
import com.apps.nmec.responses.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<AppError> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            Object[] errorObject = error.getArguments();
            String fieldName = errorObject[0].toString();
            fieldName =fieldName.substring(fieldName.lastIndexOf("[")+1,fieldName.lastIndexOf("]"));
            AppError validationError = new AppError(fieldName,error.getDefaultMessage());

            details.add(validationError);
        }

        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        // LOGGER.error(error.toString());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

}