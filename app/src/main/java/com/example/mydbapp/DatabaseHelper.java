package com.example.mydbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "kampus";
    private static final int DB_VERSION =1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlstament = "CREATE TABLE students(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT);";
        db.execSQL(sqlstament); //perintha untuk membuat table ---table students

        ContentValues values = new ContentValues();
        values.put("name","Rangga Saerang");
        values.put("email","rangga@yahoo.com");
        db.insert("students",null,values);

        ContentValues values1 = new ContentValues();
        values1.put("name","Veltie Wensen");
        values1.put("email","wensen@yahoo.com");
        db.insert("students",null,values1);

        ContentValues values2 = new ContentValues();
        values2.put("name","Umar wardani");
        values2.put("email","umar@yahoo.com");
        db.insert("students",null,values2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
