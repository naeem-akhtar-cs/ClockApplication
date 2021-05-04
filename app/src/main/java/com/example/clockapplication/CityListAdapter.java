package com.example.clockapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.viewHolder> implements Filterable {

    private final ArrayList<City> citiesList;
    private final ArrayList<City> SelectedCities;

    private ArrayList<City> FilteredCitiesList;
    private Filter filter;

    public CityListAdapter(ArrayList<City> citiesList, ArrayList<City> SelectedCities){
        super();
        this.citiesList=citiesList;
        this.FilteredCitiesList=citiesList;
        this.SelectedCities=SelectedCities;
    }

    public City getItem(int position){ return FilteredCitiesList.get(position); }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View convertView = inflater.inflate(R.layout.list_items,parent,false);

        return new viewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        City city=getItem(position);

        holder.cBox.setOnClickListener(v -> {
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

        holder.cBox.setChecked(city.getCheck());

        holder.tView.setText(city.getName());

        //Time
        DateFormat df = new SimpleDateFormat("h:mm a");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Calendar cal=Calendar.getInstance();
        int uct=(int) (city.getZone()*60);
        cal.add(Calendar.MINUTE, uct);
        Date newDate=cal.getTime();

        String MyTime=df.format(newDate.getTime());

        holder.time.setText(MyTime);
    }

    @Override
    public int getItemCount() {
        return FilteredCitiesList.size();
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

    public class viewHolder extends RecyclerView.ViewHolder {
        public CheckBox cBox;
        public TextView tView;
        public TextView time;

        public viewHolder(View view) {
            super(view);

            cBox=view.findViewById(R.id.cBox);
            tView=view.findViewById(R.id.myText);
            time=view.findViewById(R.id.timeText);
        }
    }
}
