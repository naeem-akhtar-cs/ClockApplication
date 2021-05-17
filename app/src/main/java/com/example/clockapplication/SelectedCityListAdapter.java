package com.example.clockapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SelectedCityListAdapter extends RecyclerView.Adapter<SelectedCityListAdapter.viewHolder> {

    private final ArrayList<City> SelectedCitiesList;

    public SelectedCityListAdapter(ArrayList<City> SelectedCitiesList) {
        super();
        this.SelectedCitiesList=SelectedCitiesList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View convertView = inflater.inflate(R.layout.first_activity_items,parent,false);

        return new viewHolder(convertView);
    }


    @Override
    public int getItemCount() {
        return SelectedCitiesList.size();
    }

    public City getItem(int position){
        return SelectedCitiesList.get(position);
    }

    @Override
    public void onBindViewHolder(SelectedCityListAdapter.viewHolder holder, int position) {

        City city=getItem(position);

        String str = "";

            DateFormat df = new SimpleDateFormat("hh:mm:ss a");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Calendar LocalCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            int uct = (int) (city.getZone() * 60);
            LocalCal.add(Calendar.MINUTE, uct);

            Date date = LocalCal.getTime();

            String MyTime = df.format(date.getTime());

            String STime[] = MyTime.split(":|\\ ");

            Calendar MyCal = Calendar.getInstance();

            if (city.getZone() >= 5) { //Cities ahead of us

                float UTCDiff = (float) (city.getZone() - 5);

                int mins = (int) (UTCDiff * 60);

                int HoursDiff = mins / 60;
                int MinsDiff = mins % 60;

                if ((HoursDiff + (MyCal.get(Calendar.HOUR_OF_DAY)) > 23)) {
                    str = str + "Tomorrow,";
                } else {
                    str = str + "Today,";
                }

                if (HoursDiff == 1) {
                    str = str + " " + HoursDiff + " hour";
                    if (MinsDiff != 0) {
                        str = str + " " + MinsDiff + " mins";
                    }
                    str = str + " ahead";
                } else if (HoursDiff > 1) {
                    str = str + " " + HoursDiff + " hours";
                    if (MinsDiff != 0) {
                        str = str + " " + MinsDiff + " mins";
                    }
                    str = str + " ahead";
                } else if (MinsDiff != 0) {
                    str = str + " " + MinsDiff + " mins ahead";
                }

                if (HoursDiff == 0 && MinsDiff == 0) {
                    str = str + " same time";
                }
            } else if (city.getZone() < 5) { //Cities ahead of us

                float UTCDiff = (float) (5 - city.getZone());

                int mins = (int) (UTCDiff * 60);

                int HoursDiff = mins / 60;
                int MinsDiff = mins % 60;

                if ((MyCal.get(Calendar.HOUR_OF_DAY)) - HoursDiff < 0) {
                    str = str + "Yesterday,";
                } else {
                    str = str + "Today,";
                }

                if (HoursDiff == 1) {
                    str = str + " " + HoursDiff + " hour";
                    if (MinsDiff != 0) {
                        str = str + " " + MinsDiff + " mins";
                    }
                    str = str + " behind";
                } else if (HoursDiff > 1) {
                    str = str + " " + HoursDiff + " hours";
                    if (MinsDiff != 0) {
                        str = str + " " + MinsDiff + " mins";
                    }
                    str = str + " behind";
                } else if (MinsDiff != 0) {
                    str = str + " " + MinsDiff + " mins behind";
                }
            }

            String myTime = STime[0] + ":" + STime[1];

            holder.Hmins.setText(myTime);
            holder.AP.setText(STime[3]);
            holder.seconds.setText(STime[2]);


        holder.cityName.setText(city.getName());
        holder.desc.setText(str);
        holder.image.setImageResource(city.getFlag());
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView Hmins;
        public TextView AP;
        public TextView seconds;

        public TextView desc;
        public TextView cityName;
        public ImageView image;

        public viewHolder(View view) {
            super(view);

            Hmins =view.findViewById(R.id.Hmins);
            AP=view.findViewById(R.id.AP);
            seconds=view.findViewById(R.id.seconds);

            desc=view.findViewById(R.id.Desc);
            cityName = view.findViewById(R.id.cityName);
            image=view.findViewById(R.id.countryImage);

        }
    }

}