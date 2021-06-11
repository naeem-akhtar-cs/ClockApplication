package com.example.clockapplication;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

class City implements Serializable {

    private String id;
    private String cityName;
    private double timeZone;
    private Boolean status;
    private String counrtyCode;
    private final transient ICityDAO dao;

    public City(String name, double zone,String country, ICityDAO dao){
        this.id = UUID.randomUUID().toString();
        this.cityName=name;
        this.timeZone=zone;
        this.status=false;
        this.counrtyCode=country;
        this.dao=dao;
    }



    public String getID(){
        return id;
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

    public String getCounrtyCode(){
        return counrtyCode;
    }

    public void updateCheck(){
        dao.updateCheck(this);
    }

    public int getFlag(){
        switch (this.counrtyCode) {
            case "af": return R.drawable.af;
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
            case "ru": return R.drawable.ru;
            case "tr": return R.drawable.tr;
            case "tw": return R.drawable.tw;
            case "us": return R.drawable.us;
            case "il": return R.drawable.il;

            default:
                return R.drawable.pk;
        }
    }

    public void save() {

        if (dao != null) {

            Hashtable<String, String> data = new Hashtable<>();

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
        timeZone = Double.parseDouble(Objects.requireNonNull(data.get("timezone"))); //Converting String to Double
        cityName = data.get("cityname");
        status = data.get("status").equals("true"); //Converting String to Boolean Conversion
        counrtyCode = data.get("counrtycode");
    }

    public static ArrayList<City> load(ICityDAO dao, Boolean check){
        ArrayList<City> Cities = new ArrayList<City>();
        if(dao != null){

            ArrayList<Hashtable<String,String>> objects = dao.load(check);
            for(Hashtable<String,String> obj : objects){
                City city = new City("",0,"",dao); //Dummy Data
                city.load(obj);
                Cities.add(city);
            }
        }
        return Cities;
    }

        public static ArrayList<City> GenerateCities(CitiesDbDAO dao) {

            ArrayList<City> citiesList = new ArrayList<>();

            ArrayList<String> timeZones = new ArrayList<>();

            timeZones.add("Asia/Kabul");
            timeZones.add("Europe/Tirane");
            timeZones.add("Africa/Algiers");
            timeZones.add("Europe/Andorra");
            timeZones.add("Asia/Yerevan");
            timeZones.add("Australia/Adelaide");
            timeZones.add("Europe/Vienna");
            timeZones.add("Asia/Baku");
            timeZones.add("Asia/Bahrain");
            timeZones.add("Asia/Dhaka");
            timeZones.add("Europe/Minsk");
            timeZones.add("Europe/Brussels");
            timeZones.add("Asia/Shanghai");
            timeZones.add("Africa/Brazzaville");
            timeZones.add("America/Havana");
            timeZones.add("Asia/Famagusta");
            timeZones.add("Europe/Copenhagen");
            timeZones.add("Africa/Cairo");
            timeZones.add("Europe/Berlin");
            timeZones.add("Africa/Accra");
            timeZones.add("Europe/Athens");
            timeZones.add("Asia/Kolkata");
            timeZones.add("Asia/Baghdad");
            timeZones.add("Asia/Jerusalem");
            timeZones.add("Asia/Amman");
            timeZones.add("Asia/Kuwait");
            timeZones.add("Asia/Seoul");
            timeZones.add("Asia/Karachi");
            timeZones.add("Europe/Istanbul");
            timeZones.add("America/Adak");

            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {

                        for (int i = 0; i < timeZones.size(); i++) {

                            String CountryCode = null;
                            double UTCCode = 0;
                            String cityname = null;

                            String line;

                            try {
                                URL url = new URL("https://api.timezonedb.com/v2.1/get-time-zone?key=3ZN5USVAZ546&format=xml&by=zone&zone=" + timeZones.get(i));
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.connect();

                                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                                line = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ?
                                        reader.lines().collect(Collectors.joining()) :
                                        "Please use Android Nougat.";

                                String[] s2 = line.split("<countryCode>|</countryCode>");
                                CountryCode = s2[1];

                                s2 = line.split("<gmtOffset>|</gmtOffset>");
                                UTCCode = Double.parseDouble(s2[1]);

                                s2 = line.split("<zoneName>|</zoneName>");

                                s2 = s2[1].split("/");
                                cityname = s2[1];

                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            if (cityname != null) {
                                citiesList.add(new City(cityname, UTCCode / 3600, CountryCode.toLowerCase(), dao));
                            }
                        }
                }
            });

            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Adding List in Database
            for (City city : citiesList) {
                city.save();
            }

            //get it from database

            return citiesList;
        }
}
