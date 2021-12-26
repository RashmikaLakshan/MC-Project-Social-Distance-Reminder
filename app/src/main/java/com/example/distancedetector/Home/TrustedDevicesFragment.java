package com.example.distancedetector.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.distancedetector.Activities.DeviceListAdapter;
import com.example.distancedetector.Models.Device;
import com.example.distancedetector.Models.LocalDB;
import com.example.distancedetector.R;

import java.util.ArrayList;

public class TrustedDevicesFragment extends Fragment {

    private View c_view;
    private ListView trustedDevicesListView;

    private ArrayList<Device> trustedDevices = new ArrayList<Device>();
    private Thread trustedDeviceThread = null;


    public TrustedDevicesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        c_view = inflater.inflate(R.layout.fragment_trusted_devices, container, false);

        trustedDevicesListView = c_view.findViewById(R.id.trustedDeviceList);

        trustedDevicesListView.setAdapter(new DeviceListAdapter(getActivity(),R.layout.device_list_view,trustedDevices));

        trustedDeviceThread = new Thread(){
          @Override
          public void run(){
              while (true){
                  getActivity().runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          trustedDevices = new LocalDB(getActivity()).getTrustedDevices();
                          trustedDevices.add(new Device("FC:42:03:AA:64:23", "RedmiNova2i",1,5L,1));
                          trustedDevices.add(new Device("BC:AE:4F:FB:23:3A", "RedmiNote8",1,5L,2));
                          trustedDevices.add(new Device("CC:CB:FA:DE:16:CC", "SamsungJ2",1,5L,3));
                          trustedDevices.add(new Device("EF:08:2D:10:AE:BF", "SamsungJ7",1,5L,5));
                          System.out.println("Trusted device thread running size: "+trustedDevices.size());
                          trustedDevicesListView.setAdapter(new DeviceListAdapter(getActivity(),R.layout.device_list_view,trustedDevices));
                      }
                  });

                  try {
                      Thread.sleep(2*1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        };

        trustedDeviceThread.start();


        return c_view;
    }
}