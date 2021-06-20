package com.example.clockapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    ArrayList<City> citiesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
    }

    private void showList(){
       new WebContentTask(this).execute();
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

    // AsyncTask<Params, Progress, Result>
    private static class WebContentTask extends AsyncTask<Void, Void, ArrayList<City>> {

        private final WeakReference<MainActivity> activityReference;

        // only retain a weak reference to the activity
        WebContentTask(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected ArrayList<City> doInBackground(Void... voids) {
            return getWebContent();
        }

        @Override
        protected void onPostExecute(ArrayList<City> result) {

            // get a reference to the activity if it is still there
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            EditText text;

            RecyclerView recyclerView = activity.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity.getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.Adapter myAdapter = new CityListAdapter(result);
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getHeight()));
            recyclerView.setAdapter(myAdapter);

            //Setting Up Text Filter
            text = activity.findViewById(R.id.searchText);
            text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence text, int start, int before, int count) {
                    //GetFilter().filter(text.toString());
                    ((CityListAdapter)myAdapter).getFilter().filter(text.toString());
                }

                @Override
                public void afterTextChanged(Editable s) { }
            });

            // modify the activity's UI
            //TextView textView = activity.findViewById(R.id.textView);
            //textView.append(result);

            // access Activity member variables
            // activity.mSomeMemberVariable = 321;
        }

        public ArrayList<City> getWebContent(){
            CitiesDbDAO dao = new CitiesDbDAO(activityReference.get().getApplicationContext());
            //Loading from DatBase
            ArrayList<City> citiesList = City.load(dao, false);

            //if not found in DB, load from list
            if(citiesList.isEmpty()) {
                citiesList = City.GenerateCities(dao);
            }

            return citiesList;
        }

    }
}

