package de.macoda.gesundheitstagebuch.blutdruck;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BlutdruckMessungDbHelper extends SQLiteOpenHelper {

    /*********************************************************************************************/
    /*                          Klassenkonstanten                                                 */
    /*********************************************************************************************/

    private static final String CLASS_NAME = BlutdruckMessungDbHelper.class.getSimpleName();

    public static final String TABLE_NAME = "tbl_blutdruckmessungen";
    private static final int TABLE_VERSION = 1;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MESSUNG_AM = "messung_am";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_MMHG_SYSTOLISCH = "mmhg_systolisch";
    public static final String COLUMN_MMHG_DIASTOLISCH = "mmhg_diastolisch";
    public static final String COLUMN_PULS = "puls";
    public static final String COLUMN_KOMMENTAR = "kommentar";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MESSUNG_AM + " TEXT NOT NULL, " +
                    COLUMN_POSITION + " INTEGER NOT NULL, " +
                    COLUMN_MMHG_SYSTOLISCH + " REAL NOT NULL, " +
                    COLUMN_MMHG_DIASTOLISCH + " REAL NOT NULL, " +
                    COLUMN_PULS + " REAL NOT NULL, " +
                    COLUMN_KOMMENTAR + " TEXT NULL);";

    /**
     * BlutdruckMessungDbHelper Constructor
     *
     * @param context Context
     */
    public BlutdruckMessungDbHelper(Context context) {
        super(context, TABLE_NAME, null, TABLE_VERSION);

        Log.d(CLASS_NAME, "Datenbank " + getDatabaseName() + " wurde erfolgreich erzeugt");
    }


    /**
     * onCreate
     *  wird nur aufgerufen, falls die Tabelle noch nicht existiert
     *
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(CLASS_NAME, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        } catch(Exception ex) {
            Log.e(CLASS_NAME, "Beim Anlegen der Tabelle " + ex.getMessage() + " ist ein Fehler aufgetreten");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
