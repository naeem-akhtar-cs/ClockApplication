package com.example.clockapplication;

import java.util.ArrayList;
import java.util.Hashtable;

public interface ICityDAO {
    void save(Hashtable<String, String> attributes);
    ArrayList<Hashtable<String,String>> load(boolean loadSelected);
    void updateCheck(City city);
}
