package org.utils;


public class ResponseDto {

    private Boolean error;
    private String message;

    public ResponseDto(String message, Boolean error) {
        this.message = message;
        this.error = error;
    }

}
