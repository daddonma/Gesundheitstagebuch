package de.macoda.gesundheitstagebuch.blutdruck;

import de.macoda.gesundheitstagebuch.*;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BlutdruckMessungInsertActivity extends AppCompatActivity {

    public static final String CLASS_NAME = BlutdruckMessungInsertActivity.class.getSimpleName();

    private static final String DROPDOWN_TEXT_POS_RIGHT = "Rechte Seite";
    private static final String DROPDOWN_TEXT_POS_LEFT = "Linke Seite";

    private BlutdruckMessungDataSource blutdruckMessungDataSource;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int diastolisch;
    private int systolisch;
    private int puls;
    private String kommentar;
    private short position;
    private String messungAm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(CLASS_NAME, "asd");


        super.onCreate(savedInstanceState);

        blutdruckMessungDataSource = new BlutdruckMessungDataSource(this);
        blutdruckMessungDataSource.open();

        setContentView(R.layout.activity_blutdruckmessung_insert);

        initMenuBar();

        initDropDownPosition();

        initInsertButton();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    private void initMenuBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_blutdruckmessung_insert);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Neue Messung hinterlegen");
    }

    private void initDropDownPosition() {

        Spinner dropdown = (Spinner) findViewById(R.id.dropdown_position);

        String[] items = new String[]{DROPDOWN_TEXT_POS_RIGHT, DROPDOWN_TEXT_POS_LEFT};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
    }

    private void initInsertButton() {

        View.OnClickListener onButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                Spinner dropdownPosition = (Spinner) findViewById(R.id.dropdown_position);
                EditText editTextDiastolisch = (EditText) findViewById(R.id.input_number_diastolisch);
                EditText editTextSystolisch = (EditText) findViewById(R.id.input_number_systolisch);
                EditText editTextPuls = (EditText) findViewById(R.id.input_number_puls);
                EditText editTextKommentar = (EditText) findViewById(R.id.input_text_kommentar);

                diastolisch = Integer.parseInt(editTextDiastolisch.getText().toString());
                systolisch = Integer.parseInt(editTextSystolisch.getText().toString());
                puls = Integer.parseInt(editTextPuls.getText().toString());
                kommentar = editTextKommentar.getText().toString();
                messungAm = dateFormat.format(timestamp);

                switch(dropdownPosition.getSelectedItem().toString()) {
                    case DROPDOWN_TEXT_POS_RIGHT:
                        position = BlutdruckMessung.POSITION_RIGHT;
                        break;
                    case DROPDOWN_TEXT_POS_LEFT:
                        position = BlutdruckMessung.POSITION_LEFT;
                        break;
                }

                insertData();

            }
        };

        // Anfordern des Button-Objekts über seine Ressourcen-ID
        Button button = (Button) findViewById(R.id.button_insert_messung);

        // Registrieren der OnClickListener-Instanz für den Button
        button.setOnClickListener(onButtonClickListener);
    }

    private void insertData() {

        ContentValues values = new ContentValues();
        values.put(BlutdruckMessungDbHelper.COLUMN_MESSUNG_AM, messungAm);
        values.put(BlutdruckMessungDbHelper.COLUMN_MMHG_SYSTOLISCH, Integer.toString(systolisch));
        values.put(BlutdruckMessungDbHelper.COLUMN_MMHG_DIASTOLISCH, Integer.toString(diastolisch));
        values.put(BlutdruckMessungDbHelper.COLUMN_PULS, Integer.toString(puls));
        values.put(BlutdruckMessungDbHelper.COLUMN_POSITION, position);
        values.put(BlutdruckMessungDbHelper.COLUMN_KOMMENTAR,kommentar);

       long insertedID = blutdruckMessungDataSource.insertBlutdruckMessung(values);

       Toast.makeText(this, "Eintrag erfolgreich gespeichert (ID: " + insertedID +")", Toast.LENGTH_LONG).show();



    }
}
