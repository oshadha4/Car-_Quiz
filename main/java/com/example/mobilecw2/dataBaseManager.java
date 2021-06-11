package com.example.mobilecw2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dataBaseManager extends SQLiteOpenHelper {
    public dataBaseManager(Context context) {
        super(context, "films.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table filmDetails(title TEXT primary key, year TEXT, director TEXT, actors TEXT, rating TEXT, review TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists filmDetails");

    }

    public Boolean insertData(String title, String year, String director, String actors, String rating, String review){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("year", year);
        contentValues.put("director", director);
        contentValues.put("actors", actors);
        contentValues.put("rating", rating);
        contentValues.put("review", review);

        long output = database.insert("filmDetails", null, contentValues);

        if(output == -1){
            return  false;
        }else{
            return true;
        }
    }

    public Cursor displaydata() {
        SQLiteDatabase database = this.getWritableDatabase();
        String qry = "Select * from filmDetails" ;
        Cursor cursor =  database.rawQuery(qry,null);

        return cursor;
    }

    public Cursor editdata() {
        SQLiteDatabase database = this.getWritableDatabase();
        String qry = "Select * from filmDetails";
        Cursor cursor = database.rawQuery(qry, null);

        return cursor;
    }

    public Cursor readAllData() {
        SQLiteDatabase database = this.getReadableDatabase();
        String qry = "Select * from filmDetails";
        Cursor cursor = database.rawQuery(qry, null);

        return cursor;
    }

    public Cursor editDataUp() {
        SQLiteDatabase database = this.getReadableDatabase();
        String qry = "Select * from filmDetails";
        Cursor cursor = database.rawQuery(qry, null);

        return cursor;
    }
}
