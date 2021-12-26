package com.example.distancedetector.Home;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.distancedetector.Activities.DeviceListAdapter;
import com.example.distancedetector.Models.BluetoothScanService;
import com.example.distancedetector.Models.Device;
import com.example.distancedetector.Models.LocalDB;
import com.example.distancedetector.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    //Elements in the screen
    private Button scanButton;
    private ListView deviceListVew;
    private View c_view;

    private boolean scanStarted = false;
    private long scanStartTime;
    private Thread deviceSearchTread = null;
    private ArrayList<Device> detectedDevices = new ArrayList<Device>();

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        c_view = inflater.inflate(R.layout.fragment_search, container, false);

        //Initialize the elements
        scanButton = c_view.findViewById(R.id.button2);
        deviceListVew = c_view.findViewById(R.id.deviceList);

        //Initialize the view list
        deviceListVew.setAdapter(new DeviceListAdapter(getActivity(), R.layout.device_list_view, detectedDevices));

        //OnClickListener for scan button
        scanButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(scanStarted){
                    //scan is started
                    scanButton.setText("Scan");
                    detectedDevices = new ArrayList<Device>();
                    scanStarted = false;
                    getActivity().stopService(new Intent(getActivity(), BluetoothScanService.class));
                    if(deviceSearchTread!=null){
                        deviceSearchTread.interrupt();
//                        try {
//                            deviceSearchTread.sleep(1000*1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }

                }else{
                    scanStartTime = System.currentTimeMillis();
                    //scan is not started
                    scanButton.setText("Checking Bluetooth");
                    boolean canStartScan = checkBluetooth();

                    if (canStartScan){
                        scanStarted = true;
                        scanButton.setText("Stop Scan");
                        ComponentName cn =  getActivity().startService(new Intent(getActivity(), BluetoothScanService.class));

                        deviceSearchTread = new Thread(){
                            @Override
                            public void run() {

                                while (!deviceSearchTread.isInterrupted()){
                                    detectedDevices = getDetectedDeviceList();

                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            System.out.println("Thread running");
                                            deviceListVew.setAdapter(new DeviceListAdapter(getActivity(), R.layout.device_list_view, detectedDevices));
                                        }
                                    });

                                    try {
                                        Thread.sleep(2*1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        deviceSearchTread.interrupt();
                                    }
                                }

                            }
                        };
                        deviceSearchTread.start();


                    }else{
                        scanStarted = false;
                        scanButton.setText("Scan");
                        Toast.makeText(getActivity(), "Please turn on Bluetooth", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });

        return c_view;
    }

    //check the bluetooth is turn on
    public boolean checkBluetooth(){
        //TODO check bluetooth is enabled
        return true;
    }

    //Get detected list from local DB
    public ArrayList<Device> getDetectedDeviceList(){
        return  new LocalDB(getActivity()).getDetectedDevices(this.scanStartTime);
    }
}