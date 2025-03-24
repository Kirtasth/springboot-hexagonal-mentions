package com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.domain.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.domain.DataRow;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.infrastructure.GoogleSheetsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/data")
public class DataRowControllerImpl implements DataRowController{

    private final GoogleSheetsRepository googleSheetsRepository;

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<DataRow>> getData() throws GeneralSecurityException, IOException {
        return ResponseEntity.ok().body(
                googleSheetsRepository.getData()
        );
    }
}
