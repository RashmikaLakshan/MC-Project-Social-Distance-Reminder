package com.example.distancedetector.Activities;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.distancedetector.Models.*;
import com.example.distancedetector.R;

import java.util.ArrayList;

/**
 * Device List View
 * Show device list which are detected
 */

public class DeviceListAdapter extends ArrayAdapter {
    ArrayList<Device> devices;
    Context context = null;
    public DeviceListAdapter(Context context, int layout, ArrayList<Device> devices) {
        super(context, layout);
        this.context = context;
        this.devices = devices;
    }

    public class ViewHolder{
        TextView deviceName;
        TextView deviceAddress;
        TextView distance;
        Button action;
        CardView singleRow;
    }

    @Override
    public int getCount() {
        return this.devices.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View row;
       row = convertView;
       ViewHolder viewHolder;

       if(row==null){
           row = LayoutInflater.from(getContext()).inflate(R.layout.device_list_view, parent,false);
           viewHolder = new ViewHolder();
           viewHolder.deviceName = row.findViewById(R.id.deviceName);
           viewHolder.deviceAddress = row.findViewById(R.id.deviceAddress);
           viewHolder.distance = row.findViewById(R.id.distance);
           viewHolder.action = row.findViewById(R.id.action);
           viewHolder.singleRow = row.findViewById(R.id.row_index_key);
           row.setTag(viewHolder);
       }else{
           viewHolder = (ViewHolder)row.getTag();
       }

       viewHolder.deviceName.setText(devices.get(position).getDeviceName());
       viewHolder.deviceAddress.setText(devices.get(position).getDeviceId());
       viewHolder.distance.setText(Double.toString(devices.get(position).getDistance()));
       if(devices.get(position).isSafeDevice()){
           viewHolder.action.setText("untrusted");

       }else{
           viewHolder.action.setText("trusted");
       }

       if(devices.get(position).getDistance()==1 || devices.get(position).getDistance()==0){
           viewHolder.singleRow.setBackgroundColor(Color.argb(120,255,0,0));
       }else if(devices.get(position).getDistance()==2){
            viewHolder.singleRow.setBackgroundColor(Color.argb(120,255,165,0));
        }else if(devices.get(position).getDistance()==3){
           viewHolder.singleRow.setBackgroundColor(Color.argb(120,255,255,0));
       }else{
           viewHolder.singleRow.setBackgroundColor(Color.argb(120,0,255,0));
       }

        viewHolder.action.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Device selectedDevice = devices.get(position);
                selectedDevice.setSafeDevice(!selectedDevice.isSafeDevice());
                new LocalDB(context).updateDeviceIsSafeState(selectedDevice);
                System.out.println(devices.get(position).getDeviceName());
            }
       });

       return row;
    }
}
