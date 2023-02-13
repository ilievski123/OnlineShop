package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBFlights extends SQLiteOpenHelper {

    public static final String DBName = "Flights.db";

    public DBFlights(Context context) {
        super(context, "Flights.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table flights(id TEXT primary key, destination TEXT, date TEXT, way TEXT, price TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists flights");
        onCreate(MyDB);
    }

    public Boolean insertData(String id, String destination, String date, String way, String price) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("destination", destination);
        contentValues.put("date", date);
        contentValues.put("way", way);
        contentValues.put("price", price);
        long result = MyDB.insert("flights", null, contentValues);
        if(result == -1) return false;
        else return true;
    }


    public Boolean updateData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        String where = "username=?";
        String[] whereArgs = new String[] {String.valueOf(username)};
        long result = MyDB.update("users", contentValues, where, whereArgs);
        if(result == -1) return false;
        else return true;
    }
}
