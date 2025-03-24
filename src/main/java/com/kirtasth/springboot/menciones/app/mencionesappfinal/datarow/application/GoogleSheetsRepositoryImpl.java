package com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.application;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.domain.DataRow;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.infrastructure.GoogleSheetsRepository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;


@Service
public class GoogleSheetsRepositoryImpl implements GoogleSheetsRepository {

    private final String APPLICATION_NAME = "Google Sheets Menciones Profesores";
    private final GsonFactory GSON_FACTORY = GsonFactory.getDefaultInstance();
    private final String TOKENS_DIRECTORY_PATH = "/json/token.json";

    // Conso get the data
    private final String EXCEL_ID = "1lP_8StTbNDGs8e0ivYoCrOSCkUKWMcwi46yuVKLnawc";
    private final String EXCEL_RANGE = "A1:Z3000";

    private final int CLASS_ID_COL = 0;
    private final int TEACHER_ID_COL = 1;
    private final int TEACHER_NAME_COL = 2;
    private final int COURSE_ID_COL = 3;
    private final int COURSE_START_COL = 4;
    private final int COURSE_NAME_COL = 5;
    private final int DAY_WEEK_COL = 6;
    private final int HOURS_COL = 7;
    private final int MEET_LINK_COL = 8;
    private final int MATERIAL_LINK_COL = 9;
    private final int COL_COUNT = 10;

    /*
    *  Global instance
    * */
    private final int PORT = 8888;
    private final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private final String CREDENTIALS_FILE_PATH = "/json/credentials.json";

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException{

        InputStream in = GoogleSheetsRepositoryImpl.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null){
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(GSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, GSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(PORT).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

    }

    private List<List<Object>> getDataFromGoogleSheets() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, GSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(EXCEL_ID, EXCEL_RANGE)
                .execute();
        return response.getValues();
    }

    @Override
    public List<DataRow> getData() throws GeneralSecurityException, IOException {
        List<List<Object>> rawData = getDataFromGoogleSheets();
        rawData.removeFirst();

        return rawData.stream().filter(row -> !row.isEmpty() && (long) row.size() == COL_COUNT).map(row ->

            new DataRow(
                    Long.parseLong(row.get(CLASS_ID_COL).toString()),
                    Long.parseLong(row.get(TEACHER_ID_COL).toString()),
                    row.get(TEACHER_NAME_COL).toString(),
                    Long.parseLong(row.get(COURSE_ID_COL).toString()),
                    LocalDate.parse(row.get(COURSE_START_COL).toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    row.get(COURSE_NAME_COL).toString(),
                    DayOfWeek.valueOf(row.get(DAY_WEEK_COL).toString().toUpperCase()),
                    LocalTime.parse(row.get(HOURS_COL).toString().split("-")[0], DateTimeFormatter.ofPattern("HH:mm")),
                    LocalTime.parse(row.get(HOURS_COL).toString().split("-")[1], DateTimeFormatter.ofPattern("HH:mm")),
                    row.get(MEET_LINK_COL).toString(),
                    row.get(MATERIAL_LINK_COL).toString()
            )
        ).toList();
    }

}
