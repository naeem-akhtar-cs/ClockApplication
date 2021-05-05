package com.example.clockapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CitiesDBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SelectedCities.db";

    public CitiesDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db){

        String sql = "CREATE TABLE SelectedCities (id TEXT PRIMARY KEY," +
                            "cityName TEXT," +
                            "timeZone TEXT," +
                            "status TEXT," +
                            "counrtyCode TEXT)";

        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SelectedCities");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}
