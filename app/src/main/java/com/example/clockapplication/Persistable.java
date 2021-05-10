package com.example.clockapplication;

import android.content.SharedPreferences;
import android.database.*;
import android.database.sqlite.*;

public interface Persistable {
    public void save(SharedPreferences dataStore);
    public void load(SharedPreferences dataStore);
    public String getId();
    public String getType();
}
