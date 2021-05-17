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
            data.put("cityname", cityName);
            data.put("timezone", Double.toString(timeZone));
            data.put("status", Boolean.toString(status));
            data.put("counrtycode", counrtyCode);
            dao.save(data);
        }
    }

    public void load(Hashtable<String,String> data){
        id = data.get("id");
        timeZone = Double.parseDouble(data.get("timezone")); //Converting String to Double
        cityName = data.get("cityname");
        status = data.get("status").equals("true"); //Converting String to Boolean Conversion
        counrtyCode = data.get("counrtycode");
    }

    public static ArrayList<City> load(ICityDAO dao, Boolean check){
        ArrayList<City> Cities = new ArrayList<City>();
        if(dao != null){

            ArrayList<Hashtable<String,String>> objects = dao.load();
            for(Hashtable<String,String> obj : objects){
                City city = new City("",0,"",dao); //Dummy Data
                city.load(obj);
                if(check) {
                    if(city.getCheck()) {
                        Cities.add(city);
                    }
                }
                else {
                    Cities.add(city);
                }
            }
        }
        return Cities;
    }

    public static ArrayList<City> GenerateCities(CitiesDbDAO dao){

        ArrayList<City> citiesList = new ArrayList<>();

        citiesList.add(new City("Shanghai",8,"cn",dao));
        citiesList.add(new City("Istanbul",20,"tr",dao));
        citiesList.add(new City("Buenos Aires",-3,"ar",dao));
        citiesList.add(new City("Tel Aviv",2,"il",dao));
        citiesList.add(new City("Mumbai",5.5,"in",dao));
        citiesList.add(new City("Mexico City",-6,"mx",dao));
        citiesList.add(new City("Beijing",8,"cn",dao));
        citiesList.add(new City("Karachi",5,"pk",dao));
        citiesList.add(new City("Tianjin",8,"cn",dao));
        citiesList.add(new City("Guangzhou",8,"hk",dao));
        citiesList.add(new City("Delhi",5.5,"in",dao));
        citiesList.add(new City("Moscow",3,"ru",dao));
        citiesList.add(new City("Shenzhen",8,"cn",dao));
        citiesList.add(new City("Dhaka",6,"bd",dao));
        citiesList.add(new City("Seoul",9,"kr",dao));
        citiesList.add(new City("Sao Paulo",-3,"br",dao));
        citiesList.add(new City("Lagos",1,"ng",dao));
        citiesList.add(new City("Jakarta",7,"my",dao));
        citiesList.add(new City("Tokyo",9,"jp",dao));
        citiesList.add(new City("New York City",-5,"us",dao));
        citiesList.add(new City("Taipei",8,"tw",dao));
        citiesList.add(new City("Kinshasa",1,"cd",dao));
        citiesList.add(new City("Lima",-5,"pe",dao));
        citiesList.add(new City("Cairo",2,"eg",dao));
        citiesList.add(new City("Bogotá",-5,"co",dao));
        citiesList.add(new City("London",0,"gb",dao));
        citiesList.add(new City("Baghdad",3,"iq",dao));
        citiesList.add(new City("Tehran",3.5,"ir",dao));
        citiesList.add(new City("Lahore",5,"pk",dao));

        return citiesList;
    }
}
