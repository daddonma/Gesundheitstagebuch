package de.macoda.gesundheitstagebuch.blutdruck;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.macoda.gesundheitstagebuch.R;


public class BlutdruckMessungUebersichtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivity_blutdruckmessung_uebersicht);

        initMenuBar();
    }

    private void initMenuBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_blutdruckmessung_uebersicht);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Bludruck-Messungen");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }
}
