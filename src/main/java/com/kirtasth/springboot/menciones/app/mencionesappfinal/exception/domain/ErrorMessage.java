package com.kirtasth.springboot.menciones.app.mencionesappfinal.exception.domain;


import lombok.Data;

@Data
public class ErrorMessage {
    private String exception;
    private String message;
    private String path;

    public ErrorMessage(Exception exception, String path){
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }

}
