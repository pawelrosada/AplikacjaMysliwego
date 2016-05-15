package com.example.am.aplikacjamyliwego;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HunterDB";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DRAWING_PINS_TABLE = "CREATE TABLE drawing_pins ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "latitude REAL, " +
                "longitude REAL, " +
                "type TEXT" + ")";

        String CREATE_HUNTING_AREA_TABLE = "CREATE TABLE huntingarea ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "description TEXT, " +
                "topleftcorner REAL, " +
                "toprightcorner REAL, " +
                "bottomleftcorner REAL, " +
                "bottomrightcorner REAL "+ ")";

        db.execSQL(CREATE_DRAWING_PINS_TABLE);
        db.execSQL(CREATE_HUNTING_AREA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS drawing_pins");
        db.execSQL("DROP TABLE IF EXISTS drawing_pins");

        this.onCreate(db);
    }

    public boolean addDrawingPin(DrawingPin newDrawingPin) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"id", "name", "latitude", "longitude", "type"};
        Cursor cursor =
                db.query("drawing_pins",
                        columns,
                        " name = ?",
                        new String[]{newDrawingPin.getName()},
                        null,
                        null,
                        null,
                        null);
        if(cursor==null || cursor.getCount()<=0){
            ContentValues values = new ContentValues();
            values.put("name", newDrawingPin.getName());
            values.put("latitude", newDrawingPin.getLatitude());
            values.put("longitude", newDrawingPin.getLongitude());
            values.put("type", newDrawingPin.getType());

            db.insert("drawing_pins", null, values);

            db.close();
            return true; //added item
        }else{
            return false; //item exist
        }

    }

    public DrawingPin getDrawingPin(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id", "name", "latitude", "longitude", "type"};
        Cursor cursor =
                db.query("drawing_pins",
                        columns,
                        " id = ?",
                        new String[]{String.valueOf(id)},
                        null,
                        null,
                        null,
                        null);

        if (cursor != null)
            cursor.moveToFirst();
        else
            return new DrawingPin();

        DrawingPin returnValue = new DrawingPin();
        returnValue.setId(cursor.getInt(0));
        returnValue.setName(cursor.getString(1));
        returnValue.setLatitude(cursor.getDouble(2));
        returnValue.setLongitude(cursor.getDouble(3));
        returnValue.setType(cursor.getString(4));

        return returnValue;
    }

    public List<DrawingPin> getAllDrawingPins(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM drawing_pins", null);

        List<DrawingPin> drawingPins = new LinkedList<DrawingPin>();

        DrawingPin drawingPin = null;
        if (cursor.moveToFirst()) {
            do {
                drawingPin = new DrawingPin();
                drawingPin.setId(cursor.getInt(0));
                drawingPin.setName(cursor.getString(1));
                drawingPin.setLatitude(cursor.getDouble(2));
                drawingPin.setLongitude(cursor.getDouble(3));
                drawingPin.setType(cursor.getString(4));

                drawingPins.add(drawingPin);
            } while (cursor.moveToNext());
        }
        cursor.moveToFirst();

        return drawingPins;
    }
    public boolean deleteDrawingPin(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete("drawing_pins", "id = " + id, null) > 0;
    }

    /**********************************************************************************
    HuntingArea
     ***********************************************************************************/
    public boolean addHuntingArea(HuntingArea newHuntingArea) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"id", "name", "description", "topleftcorner", "toprightcorner", "bottomleftcorner", "bottomrightcorner"};
        Cursor cursor =
                db.query("huntingarea",
                        columns,
                        " name = ?",
                        new String[]{newHuntingArea.getName()},
                        null,
                        null,
                        null,
                        null);
        if(cursor==null || cursor.getCount()<=0){
            ContentValues values = new ContentValues();
            values.put("name", newHuntingArea.getName());
            values.put("description", newHuntingArea.getDescription());
            values.put("topleftcorner", newHuntingArea.getTopLeftCorner());
            values.put("toprightcorner", newHuntingArea.getTopRightCorner());
            values.put("bottomleftcorner", newHuntingArea.getBottomLeftCorner());
            values.put("bottomrightcorner", newHuntingArea.getBottomRightCorner());

            db.insert("huntingarea", null, values);

            db.close();
            return true; //added item
        }else{
            return false; //item exist
        }

    }

    public HuntingArea getHuntingArea(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id", "name", "description", "topleftcorner", "toprightcorner", "bottomleftcorner", "bottomrightcorner"};
        Cursor cursor =
                db.query("huntingarea",
                        columns,
                        " id = ?",
                        new String[]{String.valueOf(id)},
                        null,
                        null,
                        null,
                        null);

        if (cursor != null)
            cursor.moveToFirst();
        else
            return new HuntingArea();

        HuntingArea returnValue = new HuntingArea();
        returnValue.setId(cursor.getInt(0));
        returnValue.setName(cursor.getString(1));
        returnValue.setDescription(cursor.getString(2));
        returnValue.setTopLeftCorner(cursor.getDouble(3));
        returnValue.setTopRightCorner(cursor.getDouble(4));
        returnValue.setBottomLeftCorner(cursor.getDouble(5));
        returnValue.setBottomRightCorner(cursor.getDouble(6));

        return returnValue;
    }

    public List<HuntingArea> getAllHuntingAreas(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM huntingarea", null);

        List<HuntingArea> huntingAreas = new LinkedList<HuntingArea>();

        HuntingArea huntingArea = null;
        if (cursor.moveToFirst()) {
            do {
                huntingArea = new HuntingArea();
                huntingArea.setId(cursor.getInt(0));
                huntingArea.setName(cursor.getString(1));
                huntingArea.setDescription(cursor.getString(2));
                huntingArea.setTopLeftCorner(cursor.getDouble(3));
                huntingArea.setTopRightCorner(cursor.getDouble(4));
                huntingArea.setBottomLeftCorner(cursor.getDouble(5));
                huntingArea.setBottomRightCorner(cursor.getDouble(6));

                huntingAreas.add(huntingArea);
            } while (cursor.moveToNext());
        }
        cursor.moveToFirst();

        return huntingAreas;
    }

    public boolean deleteHuntingArea(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete("huntingarea", "id = " + id, null) > 0;
    }
}
