package com.example.clockapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import java.util.ArrayList;

public class CityListAdapter extends ArrayAdapter<City> implements Filterable {

    private final ArrayList<City> citiesList;
    private final ArrayList<City> SelectedCities;

    private ArrayList<City> FilteredCitiesList;
    private Filter filter;

    public CityListAdapter(Context context, ArrayList<City> citiesList,ArrayList<City> SelectedCities){
        super(context,0,citiesList);
        this.citiesList=citiesList;
        this.FilteredCitiesList=citiesList;
        this.SelectedCities=SelectedCities;
    }

    public City getItem(int position){ return FilteredCitiesList.get(position); }

    public int getCount() { return FilteredCitiesList.size(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city=getItem(position);

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_items,parent,false);
        }

        CheckBox cBox= convertView.findViewById(R.id.cBox);

        cBox.setOnClickListener(v -> {
            if(((CheckBox) v).isChecked()){
                city.setCheck(true);
                SelectedCities.add(city);
            } else {
                city.setCheck(false);

                //Inefficient
                for(int i=0;i<SelectedCities.size();i++){
                    if(SelectedCities.get(i).getName().equals(city.getName())){
                        SelectedCities.remove(i);
                        break;
                    }
                }
            }
        });

        cBox.setChecked(city.getCheck());

        TextView tView= convertView.findViewById(R.id.myText);
        tView.setText(city.getName());

        //Time
        DateFormat df = new SimpleDateFormat("h:mm a");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Calendar cal=Calendar.getInstance();
        int uct=(int) (city.getZone()*60);
        cal.add(Calendar.MINUTE, uct);
        Date newDate=cal.getTime();

        String MyTime=df.format(newDate.getTime());

        TextView time=convertView.findViewById(R.id.timeText);
        time.setText(MyTime);

        return convertView;
    }

    @Override
    public Filter getFilter(){
        if(filter==null){
            filter=new CitiesFilter();
        }
        return filter;
    }


    private class CitiesFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                ArrayList<City> filteredList = new ArrayList<>();
                for(int i=0; i < citiesList.size(); i++){
                    if(citiesList.get(i).getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(citiesList.get(i));
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
            }
            else{
                results.count = citiesList.size();
                results.values = citiesList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            FilteredCitiesList = (ArrayList<City>) results.values;
            notifyDataSetChanged();
        }
    }
}
