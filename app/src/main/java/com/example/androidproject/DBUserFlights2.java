package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUserFlights2 extends SQLiteOpenHelper {

    public static final String DBName = "UserFlights2.db";

    public DBUserFlights2(Context context) {
        super(context, "UserFlights2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table flights(id TEXT primary key, userName TEXT, flightId TEXT, destination TEXT, date TEXT, way TEXT, price TEXT, longitude TEXT, latitude TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists flights");
        onCreate(MyDB);
    }

    public Boolean insertData(String id, String userName, String flightId, String destination, String date, String way, String price, String longitude, String latitude) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("userName", userName);
        contentValues.put("flightId", flightId);
        contentValues.put("destination", destination);
        contentValues.put("date", date);
        contentValues.put("way", way);
        contentValues.put("price", price);
        contentValues.put("longitude", longitude);
        contentValues.put("latitude", latitude);
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

    Cursor readAllData(){
        String query = "SELECT * FROM flights";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataByUsername(String userName){
        String query = "SELECT * FROM polls";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM " + "flights" + " where userName = '" + userName + "'" , null);
        }
        return cursor;
    }

    Cursor DeleteAllDataByFlightId(String flightId){
        String query = "SELECT * FROM polls";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("DELETE FROM " + "flights" + " where flightId = '" + flightId + "'" , null);
        }
        return cursor;
    }

    public Boolean deleteDataByFlightId(String flightId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("flightId", flightId);
        String where = "flightId=?";
        String[] whereArgs = new String[] {String.valueOf(flightId)};
        long result = MyDB.delete("flights", where, whereArgs);
        if(result == -1) return false;
        else return true;
    }

}

