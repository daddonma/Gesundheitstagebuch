package de.macoda.gesundheitstagebuch.blutdruck;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BlutdruckMessung {

    /*********************************************************************************************/
    /*                          Klassenkonstanten                                                 */
    /*********************************************************************************************/

    private static final String CLASS_NAME = BlutdruckMessung.class.getSimpleName();

    public static final short POSITION_RIGHT = 1;
    public static final short POSITION_LEFT = 2;

    /*********************************************************************************************/
    /*                          Klassenvariablen                                                  */
    /*********************************************************************************************/

    private long id;
    private String messungAm;
    private short position;
    private short mmhgSystolisch;
    private short mmhgDiastolisch;
    private short puls;
    private String kommentar;

    /**
     * BlutdruckMessung Constructor
     *  @param id long
     * @param messungAm String
     * @param position short
     * @param mmhgSystolisch short
     * @param mmhgDiastolisch short
     * @param puls short
     * @param kommentar String
     */
    public BlutdruckMessung(long id, String messungAm, short position, short mmhgSystolisch, short mmhgDiastolisch, short puls, String kommentar) {

        this.id = id;
        this.messungAm = messungAm;
        this.position = position;
        this.mmhgSystolisch = mmhgSystolisch;
        this.mmhgDiastolisch = mmhgDiastolisch;
        this.puls = puls;
        this.kommentar = kommentar;

    }

    public BlutdruckMessung(int id, Date date, int position, int mmhgSystolisch, int mmhgDiastolisch, int puls, String hallo) {
    }

    /*********************************************************************************************/
    /*                          Getter & Setter                                                  */
    /*********************************************************************************************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessungAm() {
        return messungAm;
    }

    public String getLocalMessungAm() {
        String originalStringFormat = "yyyy-MM-dd HH:mm:ss";
        String desiredStringFormat = "dd.MM.yyyy HH:mm 'Uhr'";

        SimpleDateFormat readingFormat = new SimpleDateFormat(originalStringFormat);
        SimpleDateFormat outputFormat = new SimpleDateFormat(desiredStringFormat);

        try {
            Date date = readingFormat.parse(this.messungAm);
            return outputFormat.format(date);
        } catch (ParseException e) {

            e.printStackTrace();
            Log.e(CLASS_NAME, "Error: " + e.getMessage());

            return this.messungAm;
        }
    }

    public void setMessungAm(String messungAm) {
        this.messungAm = messungAm;
    }

    public short getPosition() {
        return position;
    }

    public void setPosition(short position) {
        this.position = position;
    }

    public short getMmhgSystolisch() {
        return mmhgSystolisch;
    }

    public void setMmhgSystolisch(short mmhgSystolisch) {
        this.mmhgSystolisch = mmhgSystolisch;
    }

    public short getMmhgDiastolisch() {
        return mmhgDiastolisch;
    }

    public void setMmhgDiastolisch(short mmhgDiastolisch) {
        this.mmhgDiastolisch = mmhgDiastolisch;
    }

    public short getPuls() {
        return puls;
    }

    public void setPuls(short puls) {
        this.puls = puls;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
