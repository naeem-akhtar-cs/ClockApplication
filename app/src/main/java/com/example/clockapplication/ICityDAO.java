package com.example.clockapplication;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Hashtable;

public interface ICityDAO {
    public void save(Hashtable<String,String> attributes);
    public void save(ArrayList<Hashtable<String,String>> objects);
    public ArrayList<Hashtable<String,String>> load();
    public Hashtable<String,String> load(String id);
    public void updateCheck(City city);
}
