package com.example.nazar.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nazar on 16.04.2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "persons.db";
    public static final String TABLE_NAME = "persons";
    public static final String COL_ID = "id";
    public static final String COL_PERSONNAME = "personname";
    public static final String COL_PERSONAGE = "personage";
    public static final String COL_PERSONCOLOREYE = "personcoloreye";
    public static final String COL_PERSONTEMPERAMENT = "persontemperament";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_PERSONNAME + " TEXT, " +
                COL_PERSONAGE + " INTEGER, " +
                COL_PERSONCOLOREYE + " TEXT, " +
                COL_PERSONTEMPERAMENT + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add a new row to the database
    public void addPerson(Person person) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PERSONNAME, person.getPersonName());
        values.put(COL_PERSONAGE, person.getAgeOfPerson());
        values.put(COL_PERSONCOLOREYE, person.getColorEye());
        values.put(COL_PERSONTEMPERAMENT, person.getTemperament());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Delete a person from the database
    public void deletePerson(String personName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL_PERSONNAME + "=\"" + personName + "\";");
    }

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        //c.moveToFirst();
        //!!!!!!check in google !!!!!!
        while (c.moveToNext()) {
            if (c.getString(c.getColumnIndex("personname")) != null) {
                dbString += c.getString(c.getColumnIndex(COL_PERSONNAME)) + " ";
                dbString += c.getString(c.getColumnIndex(COL_PERSONAGE)) + " ";
                dbString += c.getString(c.getColumnIndex(COL_PERSONCOLOREYE)) + " ";
                dbString += c.getString(c.getColumnIndex(COL_PERSONTEMPERAMENT)) + " ";
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}















