package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Users.db";

    public DBHelper(Context context) {
        super(context, "Users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("Create Table polls(id TEXT primary key, name TEXT, time INTEGER)");
        MyDB.execSQL("Create Table questions(id TEXT primary key, name TEXT, answer1 TEXT, answer2 TEXT, answer3 TEXT, answer4 TEXT, pollId TEXT, foreign key (pollId) references polls(id) on delete cascade)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists polls");
        MyDB.execSQL("drop Table if exists questions");
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result == -1) return false;
        else return true;
    }


    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});

        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});

        if(cursor.getCount() > 0) return true;
        else return false;
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