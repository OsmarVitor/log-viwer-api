package com.log.viewer.domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiException> entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND)
                .body(createResponseException(ex.getMessage(), NOT_FOUND.value()));
    }

    private ApiException createResponseException(String message, int statusCode) {
        return new ApiException(message, statusCode);
    }

}
