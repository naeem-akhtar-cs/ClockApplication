package com.example.clockapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

class City implements Serializable,Persistable{

    private String cityName;
    private double timeZone;
    private Boolean status;
    private String counrtyCode;

    public City(String name, double zone,String country){
        this.cityName=name;
        this.timeZone=zone;
        this.status=false;
        this.counrtyCode=country;
    }

    public String getName(){
        return this.cityName;
    }

    public double getZone(){
        return timeZone;
    }

    public Boolean getCheck(){
        return this.status;
    }

    public void setCheck(Boolean val){
        this.status=val;
    }

    public int getFlag(){
        switch (this.counrtyCode) {
            case "ar": return R.drawable.ar;
            case "bd": return R.drawable.bd;
            case "br": return R.drawable.br;
            case "cd": return R.drawable.cd;
            case "cn": return R.drawable.cn;
            case "co": return R.drawable.co;
            case "eg": return R.drawable.eg;
            case "gb": return R.drawable.gb;
            case "hk": return R.drawable.hk;
            case "in": return R.drawable.in;
            case "iq": return R.drawable.iq;
            case "ir": return R.drawable.ir;
            case "jp": return R.drawable.jp;
            case "kr": return R.drawable.kr;
            case "mx": return R.drawable.mx;
            case "my": return R.drawable.my;
            case "ng": return R.drawable.ng;
            case "pe": return R.drawable.pe;
            case "pk": return R.drawable.pk;
            case "ru": return R.drawable.ru;
            case "tr": return R.drawable.tr;
            case "tw": return R.drawable.tw;
            case "us": return R.drawable.us;
            case "il": return R.drawable.il;
            default:
                return 0;
        }
    }

    @Override
    public void save(SQLiteDatabase dataStore) {
        ContentValues values = new ContentValues();
        values.put("Name",cityName);
        values.put("Zone",timeZone);
        values.put("Status",status);
        values.put("CountryCode",counrtyCode);

        dataStore.insertWithOnConflict("SelectedCities",null,values,SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void load(Cursor dataStore) {
        cityName = dataStore.getString(dataStore.getColumnIndex("Name"));
        timeZone = Double.parseDouble(dataStore.getString(dataStore.getColumnIndex("Zone"))); //Converting String to Double
        status = ((dataStore.getString(dataStore.getColumnIndex("Status"))).equals("true")); //Converting String to Boolean Conversion
        counrtyCode = dataStore.getString(dataStore.getColumnIndex("CountryCode"));
    }

    @Override
    public String getId() {
        return cityName;
    }

    @Override
    public String getType() {
        return getClass().getName();
    }
}
