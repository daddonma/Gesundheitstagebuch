package de.macoda.gesundheitstagebuch.blutdruck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlutdruckMessungDataSource {


    private static final String CLASS_NAME = BlutdruckMessungDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private BlutdruckMessungDbHelper dbHelper;

    private String[] columns = {
            BlutdruckMessungDbHelper.COLUMN_ID,
            BlutdruckMessungDbHelper.COLUMN_MESSUNG_AM,
            BlutdruckMessungDbHelper.COLUMN_POSITION,
            BlutdruckMessungDbHelper.COLUMN_MMHG_SYSTOLISCH,
            BlutdruckMessungDbHelper.COLUMN_MMHG_DIASTOLISCH,
            BlutdruckMessungDbHelper.COLUMN_PULS,
            BlutdruckMessungDbHelper.COLUMN_KOMMENTAR,
    };


    public BlutdruckMessungDataSource(Context context) {
        Log.d(CLASS_NAME, "Die DataSource erzeugt nun den DB Helper");
        dbHelper = new BlutdruckMessungDbHelper(context);
    }

    public void open() {
        Log.d(CLASS_NAME, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(CLASS_NAME, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(CLASS_NAME, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public Long insertBlutdruckMessung(ContentValues values) {

        String tableName = BlutdruckMessungDbHelper.TABLE_NAME;

        long insertedID = database.insert(tableName, null, values);

        Cursor cursor = database.query(tableName, columns, BlutdruckMessungDbHelper.COLUMN_ID + " = " + insertedID, null, null, null, null);

        cursor.moveToFirst();

        BlutdruckMessung blutdruckMessungObj = createBlutdruckMessungObjByCursor(cursor);

        return insertedID;
    }

    private BlutdruckMessung createBlutdruckMessungObjByCursor(Cursor cursor) {

        long id = cursor.getLong(cursor.getColumnIndex(BlutdruckMessungDbHelper.COLUMN_ID));
        String messungAm = cursor.getString(cursor.getColumnIndex(BlutdruckMessungDbHelper.COLUMN_MESSUNG_AM));
        short position = cursor.getShort(cursor.getColumnIndex(BlutdruckMessungDbHelper.COLUMN_POSITION));
        short mmhgSystolisch = cursor.getShort(cursor.getColumnIndex(BlutdruckMessungDbHelper.COLUMN_MMHG_SYSTOLISCH));
        short mmhgDiastolisch = cursor.getShort(cursor.getColumnIndex(BlutdruckMessungDbHelper.COLUMN_MMHG_DIASTOLISCH));
        short puls = cursor.getShort(cursor.getColumnIndex(BlutdruckMessungDbHelper.COLUMN_PULS));
        String kommentar = cursor.getString(cursor.getColumnIndex(BlutdruckMessungDbHelper.COLUMN_KOMMENTAR));

        BlutdruckMessung obj = new BlutdruckMessung(id, messungAm,position, mmhgSystolisch, mmhgDiastolisch, puls,  kommentar);
        return obj;
    }

    public List<BlutdruckMessung> getAllBlutdruckMessungen() {
        List<BlutdruckMessung> blutdruckMessungen = new ArrayList<>();

        Cursor cursor = database.query(BlutdruckMessungDbHelper.TABLE_NAME, columns, null, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            BlutdruckMessung blutruckMessungObj = createBlutdruckMessungObjByCursor(cursor);
            blutdruckMessungen.add(blutruckMessungObj);
            cursor.moveToNext();
        }

        cursor.close();

        return blutdruckMessungen;
    }

    public BlutdruckMessung getNewestBlutdruckMessung() {

        getNewestBlutdruckMessung(0);
        Cursor cursor = database.query(BlutdruckMessungDbHelper.TABLE_NAME, columns, null, null, null, null, BlutdruckMessungDbHelper.COLUMN_MESSUNG_AM + " DESC", "1");

        cursor.moveToFirst();

        BlutdruckMessung obj = createBlutdruckMessungObjByCursor(cursor);

        return obj;
    }

    public BlutdruckMessung getNewestBlutdruckMessung(int position) {

        String filter = "";

        if(position != 0) {
            filter = BlutdruckMessungDbHelper.COLUMN_POSITION + " = " + position;
        }

        Cursor cursor = database.query(BlutdruckMessungDbHelper.TABLE_NAME, columns, filter, null, null, null, BlutdruckMessungDbHelper.COLUMN_MESSUNG_AM + " DESC", "1");

        if(((cursor != null) && (cursor.getCount() > 0))) {
            cursor.moveToFirst();

            BlutdruckMessung obj = createBlutdruckMessungObjByCursor(cursor);

            return obj;
        } else {
            return null;
        }

    }


}
