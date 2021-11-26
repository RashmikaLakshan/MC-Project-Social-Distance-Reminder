package com.example.distancedetector.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.distancedetector.Models.Country;
import com.example.distancedetector.Models.HealthTips;
import com.example.distancedetector.R;

import java.util.ArrayList;

/**
 * Device List View
 * Show device list which are detected
 */

public class HealthTipsListAdapter extends ArrayAdapter {
    ArrayList<HealthTips> tips;
    public HealthTipsListAdapter(Context context, int layout, ArrayList<HealthTips> tips) {
        super(context, layout);
        this.tips = tips;
    }

    public class ViewHolder{
        TextView tipDescription;
        ImageView tipReference;
    }

    @Override
    public int getCount() {
        return this.tips.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View row;
       row = convertView;
       ViewHolder viewHolder;

       if(row==null){
           row = LayoutInflater.from(getContext()).inflate(R.layout.healthtips_list_view, parent,false);
           viewHolder = new ViewHolder();
           viewHolder.tipDescription = row.findViewById(R.id.tipDescription);
           viewHolder.tipReference = row.findViewById(R.id.tipReference);
           row.setTag(viewHolder);
       }else{
           viewHolder = (ViewHolder)row.getTag();
       }

       viewHolder.tipDescription.setText(tips.get(position).getTipDescription());

       viewHolder.tipReference.setImageResource(tips.get(position).getTipReference());
//       viewHolder.tipReference.setImageResource(Integer.intValue(tips.get(position).getTipReference()));

       return row;
    }
}
