package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<City> citiesList;

    CitiesDbDAO dao;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao=new CitiesDbDAO(this);

        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Loading from DatBase
        citiesList = City.load(dao,false);

        //if not found in Db, load from list
       // if(citiesList.isEmpty()) {
       //     citiesList = City.GenerateCities(dao);
      //  }

        //Setting Up Text Filter
        text=findViewById(R.id.searchText);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                //myAdapter.getFilter().filter(text.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        myAdapter = new CityListAdapter(this.citiesList);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),layoutManager.getHeight()));
        recyclerView.setAdapter(myAdapter);
    }

    private void prepareResult(){
        Intent intent = new Intent();
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

    public void onPause(){
        super.onPause();

        for(City city : citiesList){
            city.save();
        }
    }
}