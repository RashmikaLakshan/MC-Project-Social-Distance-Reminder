package com.example.distancedetector.Models;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {

    ArrayList<Device> foundDevices = new ArrayList<Device>();
    ArrayList<String> foundDevicesIds = new ArrayList<String>();
    boolean isFinishedDiscovery = true;
    com.example.distancedetector.Models.LocalDB db;
    com.example.distancedetector.Models.DistanceCalculator distanceCalculator;

    public BluetoothBroadcastReceiver(com.example.distancedetector.Models.LocalDB db){

        this.db = db;
        distanceCalculator = new com.example.distancedetector.Models.DistanceCalculator();

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //Get action type
        String action = intent.getAction();
        System.out.println("Action : "+action );

        this.isFinishedDiscovery = false;
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            //Get detected bluetooth device
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            //Get RSSI value
            int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);

            //Save detected Device
            Device savedDevice = saveDeviceDetails2LocalStorage(context, device, rssi);
            //Notify users
            notifyUser(context, savedDevice);

        }

        if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            // if no devices found in a search
            System.out.println("Closed Discovery");
            this.isFinishedDiscovery = true;
        }
    }

    public Device saveDeviceDetails2LocalStorage(Context context, BluetoothDevice BTdevice, int rssi){

        /**
         * Save Device to LocalDB
         * Return Device object
         */

        long stimestamp = System.currentTimeMillis();
        System.out.println("Time Stamp: " + stimestamp);

        boolean hadDevice = false;
        boolean isSafeDevice = false;


        Device device = new Device(BTdevice.getAddress(), BTdevice.getName(), 0, stimestamp);
        double distance = distanceCalculator.calculateDistance(context, rssi, BTdevice.getAddress());

        device.setDistance(distance);
        System.out.println("CALCULATE: RSSI: "+ device.getRSSI() + " DISTANCE: "+device.getDistance());


        try{
            db.addDevices(device);
            Toast.makeText(context, "name: " + BTdevice.getName() + " " + BTdevice.getAddress() + " "+rssi, Toast.LENGTH_LONG).show();
        }catch (SQLiteConstraintException ce){
            hadDevice = true;
            db.updateLastDetectedTime(device);
            System.out.println("Same Device");
        }

        System.out.println("Saved Time: "+device.getLastDetected());

        if(hadDevice){
            long lastDetect = device.getLastDetected();
            device = db.getDevice(device.getDeviceId());
            device.setLastDetected(lastDetect);
        }

        device.setRSSI(rssi);
        device.setDistance(distance);

        System.out.println("CALCULATE: RSSI: "+ device.getRSSI() + " DISTANCE: "+device.getDistance());

        return device;
    }

    public void notifyUser(Context context, Device device){
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator() && !device.isSafeDevice() ) {
            System.out.println("HAS VIBRATOR");
            if(device.getDistance()==2){
                vibrator.vibrate(500);
            }else if (device.getDistance()==1 || device.getDistance()==0){
                long[] mVibratePattern = new long[]{0, 400, 200, 400};
                vibrator.vibrate(mVibratePattern, -1);
            }

        }
    }

    public boolean getIsFinishedDiscovery(){
        return this.isFinishedDiscovery;
    }

    public List<Device> getAvailableDevices(){
        return  foundDevices;
    }
}
