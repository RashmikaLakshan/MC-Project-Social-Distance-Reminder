package com.example.distancedetector.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.distancedetector.Models.Country;
import com.example.distancedetector.R;

import java.util.ArrayList;

/**
 * Device List View
 * Show device list which are detected
 */

public class DistrictListAdapter extends ArrayAdapter {
    ArrayList<Country> districts;
    public DistrictListAdapter(Context context, int layout, ArrayList<Country> districts) {
        super(context, layout);
        this.districts = districts;

    }

    public class ViewHolder{
        TextView district;
        TextView confirmed;
        TextView recovered;
        TextView deaths;
    }

    @Override
    public int getCount() {
        return this.districts.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View row;
       row = convertView;
       ViewHolder viewHolder;

       if(row==null){
           row = LayoutInflater.from(getContext()).inflate(R.layout.district_list_view, parent,false);
           viewHolder = new ViewHolder();
           viewHolder.district = row.findViewById(R.id.district);
           viewHolder.confirmed = row.findViewById(R.id.confirmed);
           viewHolder.recovered = row.findViewById(R.id.recovered);
           viewHolder.deaths = row.findViewById(R.id.deaths);
           row.setTag(viewHolder);
       }else{
           viewHolder = (ViewHolder)row.getTag();
       }

       viewHolder.district.setText(districts.get(position).getCountryName());
       viewHolder.confirmed.setText(Integer.toString(districts.get(position).getNoOfConfirmed()));
       viewHolder.recovered.setText(Integer.toString(districts.get(position).getNoOfRecovered()));
       viewHolder.deaths.setText(Integer.toString(districts.get(position).getNoOfDeaths()));

       return row;
    }
}
