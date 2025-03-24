package com.kirtasth.springboot.menciones.app.mencionesappfinal.exception.domain;

public class NotFoundException extends RuntimeException {

  private static final String DESCRIPTION = "Not found Exception ";

  public NotFoundException(String message) {
    super(DESCRIPTION + ". " + message);
  }
}
