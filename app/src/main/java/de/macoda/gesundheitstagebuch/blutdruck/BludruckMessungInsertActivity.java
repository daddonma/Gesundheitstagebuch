package de.macoda.gesundheitstagebuch.blutdruck;

import de.macoda.gesundheitstagebuch.*;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BludruckMessungInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_blutdruckmessung_insert);

        initMenuBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    private void initMenuBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_blutdruckmessung_insert);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Neue Messung dokumentieren");
    }


}
