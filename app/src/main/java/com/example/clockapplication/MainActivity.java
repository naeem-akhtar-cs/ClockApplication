package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<City> citiesList;
    ArrayList<City> SelectedCities;
    CityListAdapter myAdapter;
    ListView myList;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        SelectedCities = (ArrayList<City>) intent.getSerializableExtra("SelectedCities");
        citiesList=(ArrayList<City>) intent.getSerializableExtra("citiesList");

        if(SelectedCities==null){
            SelectedCities=new ArrayList<>();
        }

        if(citiesList == null){
            citiesList = new ArrayList<>();
            citiesList.add(new City("Shanghai",8,"cn"));
            citiesList.add(new City("Istanbul",20,"tr"));
            citiesList.add(new City("Buenos Aires",-3,"ar"));
            citiesList.add(new City("Tel Aviv",2,"il"));
            citiesList.add(new City("Mumbai",5.5,"in"));
            citiesList.add(new City("Mexico City",-6,"mx"));
            citiesList.add(new City("Beijing",8,"cn"));
            citiesList.add(new City("Karachi",5,"pk"));
            citiesList.add(new City("Tianjin",8,"cn"));
            citiesList.add(new City("Guangzhou",8,"hk"));
            citiesList.add(new City("Delhi",5.5,"in"));
            citiesList.add(new City("Moscow",3,"ru"));
            citiesList.add(new City("Shenzhen",8,"cn"));
            citiesList.add(new City("Dhaka",6,"bd"));
            citiesList.add(new City("Seoul",9,"kr"));
            citiesList.add(new City("Sao Paulo",-3,"br"));
            citiesList.add(new City("Lagos",1,"ng"));
            citiesList.add(new City("Jakarta",7,"my"));
            citiesList.add(new City("Tokyo",9,"jp"));
            citiesList.add(new City("New York City",-5,"us"));
            citiesList.add(new City("Taipei",8,"tw"));
            citiesList.add(new City("Kinshasa",1,"cd"));
            citiesList.add(new City("Lima",-5,"pe"));
            citiesList.add(new City("Cairo",2,"eg"));
            citiesList.add(new City("Bogotá",-5,"co"));
            citiesList.add(new City("London",0,"gb"));
            citiesList.add(new City("Baghdad",3,"iq"));
            citiesList.add(new City("Tehran",3.5,"ir"));
            citiesList.add(new City("Lahore",5,"pk"));
        }

        //Setting Up Text Filter
        text=findViewById(R.id.searchText);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                myAdapter.getFilter().filter(text.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        myList=findViewById(R.id.myList);
        if(myAdapter==null) { //Avoiding creating adapter again
            myAdapter = new CityListAdapter(this, this.citiesList,this.SelectedCities);
        }
        myList.setAdapter(myAdapter);
    }

    private void prepareResult(){
        Intent intent = new Intent();
        intent.putExtra("SelectedCities",this.SelectedCities);
        intent.putExtra("citiesList",this.citiesList);
        setResult(RESULT_OK, intent);
    }

    public void ShowList(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        prepareResult();
        super.onBackPressed();
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        try{
            savedInstanceState.putSerializable("citiesList",citiesList);
        }
        catch(Exception ex){ }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        try{
            citiesList = (ArrayList<City>) savedInstanceState.getSerializable("citiesList");
        }
        catch(Exception ex){ }
    }
}