import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.*;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SheetsQuickstart {
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Prints the names and majors of students in a sample spreadsheet:
     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
     */
    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        String spreadsheetId = "1nPWby8B_vgs-5dih_gIMUmtBpBh2K6ZurbYFwaM0FxA";
        String range = "Mar";
        Scanner in = new Scanner(System.in);
        Store s = new Store();
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        List<Sheet> sList = service.spreadsheets()
                .get(spreadsheetId)
                .execute().getSheets();

        for(Sheet e : sList){
          ValueRange response = service.spreadsheets().values()
                  .get(spreadsheetId, e.getProperties().getTitle() + "!A:B")
                  .execute();
          List<List<Object>> values = response.getValues();
          // System.out.println(e.getProperties().getTitle() + "!A:B");
          Month m = new Month();
          // List<RowData> rd = e.getData().getRowData();
          // List<List<Object>> values = new List<List<Object>>();
          // for(RowData f : rd){
          //   List<Object> o = new List<Object>();
          //   List<CellData> cd = f.getValues();
          //   for(CellData g : cd)
          //     o.add(g.getFormattedValue());
          //   values.add(o);
          if (values == null || values.isEmpty()) {
              System.out.println("No data found.");
          } else {
              // System.out.println("");
              m.parseList(values);
              s.addMonth(m);
          }
        }
        String [] add = new String[]{
          "Business Promotion",
          "Cash Discounts",
          "Repairs Janitorial",
          "Repairs Building",
          "Travel",
          "Waste Oil Disposal Fee"
        };
        s.addCategories(add);
        s.print();
        System.out.println();
        //   ValueRange response = service.spreadsheets().values()
        //           .get(spreadsheetId, e.getProperties().getTitle())
        //           .execute();

        // Month m = new Month();
        // ValueRange response = service.spreadsheets().values()
        //         .get(spreadsheetId, range)
        //         .execute();
        // List<List<Object>> values = response.getValues();
        // if (values == null || values.isEmpty()) {
        //     System.out.println("No data found.");
        // } else {
        //     System.out.println("");
        //     m.parseList(values);
        // }
        // m.print();

      }

}
