package com.aldeamo.publisher.exception;


import lombok.Data;

@Data
public class ValidateAuthorizedLineException extends RuntimeException {
    private int errorCode;
    private String errorMessage;

    public ValidateAuthorizedLineException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
