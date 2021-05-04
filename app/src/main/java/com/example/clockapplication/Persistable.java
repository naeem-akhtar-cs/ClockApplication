package com.example.clockapplication;

import android.database.*;
import android.database.sqlite.*;

public interface Persistable {
    public void save(SQLiteDatabase dataStore);
    public void load(Cursor dataStore);
    public String getId();
    public String getType();
}
