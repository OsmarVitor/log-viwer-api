package com.log.viewer.domain.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends Throwable {

    private String message;
    private int status;

    public ApiException(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
