package com.log.viewer.domain.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{

    private static final long serialVersionUID =  1L;

    public EntityNotFoundException(long id){
        super("Entity with id [" + id + "] not found.");
    }
}