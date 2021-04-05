package de.macoda.gesundheitstagebuch;

import de.macoda.gesundheitstagebuch.blutdruck.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

       initButtonInsertBlutdruckmessung();

       //Neueste Blutdruckmessung laden
       loadNewestBlutdruckMessung();


        LinearLayout cardBlutdruck = (LinearLayout) findViewById(R.id.card_blutdruck);

        View.OnClickListener onClickListenerx = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent explicitIntent = new Intent(MainActivity.this, BlutdruckMessungUebersichtActivity.class);

                startActivity(explicitIntent);
            }
        };

        cardBlutdruck.setOnClickListener(onClickListenerx);

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadNewestBlutdruckMessung();
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

                Intent explicitIntent = new Intent(MainActivity.this, BlutdruckMessungInsertActivity.class);

                startActivity(explicitIntent);
            }
        };

        button.setOnClickListener(onClickListener);
    }

    private void loadNewestBlutdruckMessung() {

        BlutdruckMessung newestBlutdruckMessungRechts = blutdruckMessungDataSource.getNewestBlutdruckMessung(BlutdruckMessung.POSITION_RIGHT);
        BlutdruckMessung newestBlutdruckMessungLinks = blutdruckMessungDataSource.getNewestBlutdruckMessung(BlutdruckMessung.POSITION_LEFT);

        TextView neuesteMessungRechts = (TextView) findViewById(R.id.neueste_messung_rechts);
        TextView neuesteMessungLinks = (TextView) findViewById(R.id.neueste_messung_links);

        String textLeft;
        String textRight;


        if(newestBlutdruckMessungRechts != null) {
            textRight = "(R) " + newestBlutdruckMessungRechts.getMmhgSystolisch() + " / " + newestBlutdruckMessungRechts.getMmhgDiastolisch() + " \n " + newestBlutdruckMessungRechts.getLocalMessungAm();
        } else {
            textRight = "bisher keine Messung auf der rechten Seite";
        }

        if(newestBlutdruckMessungLinks != null) {
            textLeft = "(L) " + newestBlutdruckMessungLinks.getMmhgSystolisch() + " / " + newestBlutdruckMessungLinks.getMmhgDiastolisch() + " \n " + newestBlutdruckMessungLinks.getLocalMessungAm();
        } else {
            textLeft = "bisher keine Messung auf der linken Seite";
        }

        neuesteMessungRechts.setText(textRight);
        neuesteMessungLinks.setText(textLeft);



    }
}
