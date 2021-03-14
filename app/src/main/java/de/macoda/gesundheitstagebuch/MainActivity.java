package de.macoda.gesundheitstagebuch;

import de.macoda.gesundheitstagebuch.blutdruck.*;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String CLASS_NAME = MainActivity.class.getSimpleName();

    private BlutdruckMessungDataSource blutdruckMessungDataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initMenuBar();

        blutdruckMessungDataSource = new BlutdruckMessungDataSource(this);

        Log.v(CLASS_NAME, "Die Datenquelle wird geöffnet.");
        blutdruckMessungDataSource.open();

//        ContentValues values = new ContentValues();
//        values.put(BlutdruckMessungDbHelper.COLUMN_MESSUNG_AM, "2021-03-10");
//        values.put(BlutdruckMessungDbHelper.COLUMN_MMHG_SYSTOLISCH, "130");
//        values.put(BlutdruckMessungDbHelper.COLUMN_MMHG_DIASTOLISCH, "85");
//        values.put(BlutdruckMessungDbHelper.COLUMN_PULS, "70");
//        values.put(BlutdruckMessungDbHelper.COLUMN_POSITION, BlutdruckMessung.POSITION_RIGHT);
//        values.put(BlutdruckMessungDbHelper.COLUMN_KOMMENTAR,"Hallo Welt");

       // BlutdruckMessung blutdruckMessungObj = blutdruckMessungDataSource.insertBlutdruckMessung(values);
        //blutdruckMessungDataSource.getAllBlutdruckMessungen();

       BlutdruckMessung newestBlutdruckMessungRight = blutdruckMessungDataSource.getNewestBlutdruckMessung(BlutdruckMessung.POSITION_RIGHT);

       initButtonInsertBlutdruckmessung();


    }

    private void initMenuBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Gesundheitstagebuch");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    private void initButtonInsertBlutdruckmessung() {

        Button button = (Button) findViewById(R.id.buttonInsertBlutdruckmessung);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent explicitIntent = new Intent(MainActivity.this, BludruckMessungInsertActivity.class);

                startActivity(explicitIntent);
            }
        };


        button.setOnClickListener(onClickListener);

    }
}
