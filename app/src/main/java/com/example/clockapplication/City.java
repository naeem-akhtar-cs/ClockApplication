package com.example.clockapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import android.content.ContentValues;
import android.database.sqlite.*;
import android.database.*;

class City implements Serializable{

    private String id;
    private String cityName;
    private double timeZone;
    private Boolean status;
    private String counrtyCode;
    private transient ICityDAO dao = null;

    public City(String name, double zone,String country){
        this.id = UUID.randomUUID().toString();
        this.cityName=name;
        this.timeZone=zone;
        this.status=false;
        this.counrtyCode=country;
    }

    public City(String name, double zone,String country, ICityDAO dao){
        this.id = UUID.randomUUID().toString();
        this.cityName=name;
        this.timeZone=zone;
        this.status=false;
        this.counrtyCode=country;
        this.dao=dao;
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


    public void save() {

        if (dao != null) {

            Hashtable<String, String> data = new Hashtable<String, String>();

            data.put("id", id);
            data.put("cityName", cityName);
            data.put("timeZone", Double.toString(timeZone));
            data.put("status", Boolean.toString(status));
            data.put("counrtyCode", counrtyCode);
            dao.save(data);
        }
    }

    public void load(Hashtable<String,String> data){
        id = data.get("id");
        timeZone = Double.parseDouble(data.get("timeZone")); //Converting String to Double
        cityName = data.get("cityName");
        status = data.get("status").equals("true"); //Converting String to Boolean Conversion
        counrtyCode = data.get("counrtyCode");
    }

    public static ArrayList<City> load(ICityDAO dao){
        ArrayList<City> Cities = new ArrayList<City>();
        if(dao != null){

            ArrayList<Hashtable<String,String>> objects = dao.load();
            for(Hashtable<String,String> obj : objects){
                City city = new City("",0,"",dao); //Dummy Data
                city.load(obj);
                Cities.add(city);
            }
        }
        return Cities;
    }
}
