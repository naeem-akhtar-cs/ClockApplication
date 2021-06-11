package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class first_activity extends AppCompatActivity {

    private ArrayList<City> SelectedCities;

    ICityDAO dao;

    final int REQUEST_CODE = 1;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        recyclerView= findViewById(R.id.mySelectedList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dao = new CitiesDbDAO(this);
    }

    public void GoToList(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /*
    public void startActivity(){



        //Jugaar Here (If Statement); Exception when returns from second activity
       // if(myAdapter ==null) {
       //     recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getHeight()));
       // }


    }
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                //Code to Implement
            }
        }
         */
    }

    public void onResume(){
        super.onResume();

        SelectedCities = City.load(dao,true);

        myAdapter = new SelectedCityListAdapter(this.SelectedCities);
        recyclerView.setAdapter(myAdapter);

        //Updating UI Very Second
        //------------------------------------------------------------//
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!this.isInterrupted()) {
                    try {
                        Thread.sleep(2000);
                        myAdapter = new SelectedCityListAdapter(SelectedCities);
                        recyclerView.setAdapter(myAdapter);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
        //------------------------------------------------------------//

    }
}