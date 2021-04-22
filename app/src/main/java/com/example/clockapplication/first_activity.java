package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static java.lang.System.exit;

public class first_activity extends AppCompatActivity {

    private ArrayList<City> SelectedCities;
    private ArrayList<City> citiesList;

    final int REQUEST_CODE = 1;
    ListView myList;
    SelectedCityListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
    }

    public void GoToList(View view){
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("SelectedCities",SelectedCities);
        intent.putExtra("citiesList",citiesList);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                SelectedCities= (ArrayList<City>) data.getSerializableExtra("SelectedCities");
                citiesList=(ArrayList<City>) data.getSerializableExtra("citiesList");

                myList=findViewById(R.id.mySelectedList);
                myAdapter = new SelectedCityListAdapter(this, SelectedCities);
                myList.setAdapter(myAdapter);
            }
        }
    }
}
