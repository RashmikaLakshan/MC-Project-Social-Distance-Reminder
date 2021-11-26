package com.example.distancedetector.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.distancedetector.Models.Country;
import com.example.distancedetector.Models.Device;
import com.example.distancedetector.R;

import java.util.ArrayList;

/**
 * Device List View
 * Show device list which are detected
 */

public class CountryListAdapter extends ArrayAdapter {
    ArrayList<Country> countries;
    public CountryListAdapter(Context context, int layout, ArrayList<Country> countries) {
        super(context, layout);
        this.countries = countries;

    }

    public class ViewHolder{
        TextView country;
        TextView confirmed;
        TextView recovered;
        TextView deaths;
    }

    @Override
    public int getCount() {
        return this.countries.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View row;
       row = convertView;
       ViewHolder viewHolder;

       if(row==null){
           row = LayoutInflater.from(getContext()).inflate(R.layout.country_list_view, parent,false);
           viewHolder = new ViewHolder();
           viewHolder.country = row.findViewById(R.id.country);
           viewHolder.confirmed = row.findViewById(R.id.confirmed);
           viewHolder.recovered = row.findViewById(R.id.recovered);
           viewHolder.deaths = row.findViewById(R.id.deaths);
           row.setTag(viewHolder);
       }else{
           viewHolder = (ViewHolder)row.getTag();
       }

       viewHolder.country.setText(countries.get(position).getCountryName());
       viewHolder.confirmed.setText(Integer.toString(countries.get(position).getNoOfConfirmed()));
       viewHolder.recovered.setText(Integer.toString(countries.get(position).getNoOfRecovered()));
       viewHolder.deaths.setText(Integer.toString(countries.get(position).getNoOfDeaths()));

       return row;
    }
}
