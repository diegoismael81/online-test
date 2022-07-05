package com.online.test.onlinetest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {NoContentException.class})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ErrorDetails noContentException(NoContentException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError constraintViolationException(MethodArgumentNotValidException ex) {
        ValidationError errors = new ValidationError();
        for (FieldError violation : ex.getBindingResult().getFieldErrors()) {
            errors.addViolations(new ErrorDetails(violation.getDefaultMessage(), violation.getField()));
        }
        return errors;
    }


}
