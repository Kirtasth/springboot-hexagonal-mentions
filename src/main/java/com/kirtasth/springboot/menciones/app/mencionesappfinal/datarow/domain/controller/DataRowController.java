package com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.domain.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.domain.DataRow;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface DataRowController {

    ResponseEntity<List<DataRow>> getData() throws GeneralSecurityException, IOException;
}
