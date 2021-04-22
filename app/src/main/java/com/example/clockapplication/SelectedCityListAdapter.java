package com.example.clockapplication;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SelectedCityListAdapter extends ArrayAdapter<City> {

    public SelectedCityListAdapter(Context context, ArrayList<City> SelectedCitiesList) {
        super(context, 0, SelectedCitiesList);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.first_activity_items, parent, false);
        }

        TextView cityName = convertView.findViewById(R.id.cityName);
        cityName.setText(city.getName());

        DateFormat df = new SimpleDateFormat("hh:mm:ss a");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Calendar LocalCal=Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        int uct=(int) (city.getZone()*60);
        LocalCal.add(Calendar.MINUTE, uct);

        Date date=LocalCal.getTime();

        String MyTime=df.format(date.getTime());

        String STime[]=MyTime.split(":|\\ ");

        Calendar MyCal=Calendar.getInstance();

        TextView desc = (TextView) convertView.findViewById(R.id.Desc);

        String str="";
        if(city.getZone()>=5){ //Cities ahead of us

            float UTCDiff= (float) (city.getZone()-5);

            int mins= (int) (UTCDiff*60);

            int HoursDiff=mins/60;
            int MinsDiff=mins%60;

            if((HoursDiff+(MyCal.get(Calendar.HOUR_OF_DAY))>23)){
                str=str+"Tomorrow,";
            }
            else{
                str=str+"Today,";
            }

            if(HoursDiff==1){
                str=str+" "+HoursDiff+" hour";
                if(MinsDiff!=0){
                    str=str+" "+MinsDiff+" mins";
                }
                str=str+" ahead";
            }
            else if(HoursDiff>1){
                str=str+" "+HoursDiff+" hours";
                if(MinsDiff!=0){
                    str=str+" "+MinsDiff+" mins";
                }
                str=str+" ahead";
            }
            else if(MinsDiff!=0){
                str=str+" "+MinsDiff+" mins ahead";
            }

            if(HoursDiff==0 && MinsDiff==0){
                str=str+" same time";
            }
        }
        else if(city.getZone()<5){ //Cities ahead of us

            float UTCDiff= (float)(5-city.getZone());

            int mins= (int) (UTCDiff*60);

            int HoursDiff=mins/60;
            int MinsDiff=mins%60;

            if((MyCal.get(Calendar.HOUR_OF_DAY))-HoursDiff<0){
                str=str+"Yesterday,";
            }
            else{
                str=str+"Today,";
            }

            if(HoursDiff==1){
                str=str+" "+HoursDiff+" hour";
                if(MinsDiff!=0){
                    str=str+" "+MinsDiff+" mins";
                }
                str=str+" behind";
            }
            else if(HoursDiff>1){
                str=str+" "+HoursDiff+" hours";
                if(MinsDiff!=0){
                    str=str+" "+MinsDiff+" mins";
                }
                str=str+" behind";
            }
            else if(MinsDiff!=0){
                str=str+" "+MinsDiff+" mins behind";
            }
        }

        desc.setText(str);

        TextView Hmins=(TextView) convertView.findViewById(R.id.Hmins);
        String myTime=STime[0]+":"+STime[1];
        Hmins.setText(myTime);

        TextView AP=(TextView) convertView.findViewById(R.id.AP);
        AP.setText(STime[3]);

        TextView seconds=(TextView) convertView.findViewById(R.id.seconds);
        seconds.setText(STime[2]);


        ImageView image=(ImageView) convertView.findViewById(R.id.countryImage);

        image.setImageResource(city.getFlag());

        return convertView;
    }
}