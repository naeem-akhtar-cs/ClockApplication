package com.example.clockapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class CitiesDbDAO implements ICityDAO{

    private Context context;

    public CitiesDbDAO(Context ctx){
        context = ctx;
    }

    @Override
    public void save(Hashtable<String, String> attributes) {
        CitiesDBHelper dbHelper = new CitiesDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues content = new ContentValues();
        Enumeration<String> keys = attributes.keys();

        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            content.put(key,attributes.get(key));
        }

        db.insert("MyList1",null,content);
    }

    public void updateCheck(City city){

        CitiesDBHelper dbHelper = new CitiesDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("id", city.getID());
        contentValues.put("cityname", city.getName());
        contentValues.put("timezone", Double.toString(city.getZone()));
        contentValues.put("status", Boolean.toString(city.getCheck()));
        contentValues.put("counrtycode", city.getCounrtyCode());

        String whereClause = "id=?";
        String whereArgs[] = {city.getID()};

        db.update("MyList1", contentValues, whereClause, whereArgs);
    }

    @Override
    public ArrayList<Hashtable<String, String>> load(boolean loadSelected) {
        CitiesDBHelper dbHelper = new CitiesDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query= "SELECT * FROM MyList1";

        if(loadSelected) query += " WHERE status='true'";

        Cursor cursor = db.rawQuery(query,null);

        ArrayList<Hashtable<String,String>> objects = new ArrayList<>();
        while(cursor.moveToNext()){
            Hashtable<String,String> obj = new Hashtable<>();
            String [] columns = cursor.getColumnNames();
            for(String col : columns){
                obj.put(col.toLowerCase(),cursor.getString(cursor.getColumnIndex(col)));
            }
            objects.add(obj);
        }

        return objects;
    }
}
