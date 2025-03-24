package com.kirtasth.springboot.menciones.app.mencionesappfinal.exception.domain;

public class ProfessorAlreadyRegistered extends RuntimeException {

    public ProfessorAlreadyRegistered(String message) {
        super(message);
    }
}
