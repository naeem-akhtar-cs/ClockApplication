package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class first_activity extends AppCompatActivity {

    private ArrayList<City> SelectedCities;

    ICityDAO dao;

    final int REQUEST_CODE = 1;
    ListView myList;
    SelectedCityListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        dao = new CitiesDbDAO(this);
        setContentView(R.layout.first_activity);
    }

    public ICityDAO getDAO(){
        return dao;
    }

    public void GoToList(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void startActivity(){
        myList=findViewById(R.id.mySelectedList);
        myAdapter = new SelectedCityListAdapter(this, SelectedCities);
        myList.setAdapter(myAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                startActivity();
            }
        }
    }

    public void onResume(){
        super.onResume();

        SelectedCities = City.load(dao,true);
        startActivity();
    }
}