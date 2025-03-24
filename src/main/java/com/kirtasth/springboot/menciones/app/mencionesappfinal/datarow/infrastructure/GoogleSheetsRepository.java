package com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.domain.DataRow;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface GoogleSheetsRepository {

    List<DataRow> getData() throws GeneralSecurityException, IOException;

}
