package com.kirtasth.springboot.menciones.app.mencionesappfinal.exception.domain;

public class GivenClassAlreadyRegistered extends RuntimeException {
    public GivenClassAlreadyRegistered(String message) {
        super(message);
    }
}
