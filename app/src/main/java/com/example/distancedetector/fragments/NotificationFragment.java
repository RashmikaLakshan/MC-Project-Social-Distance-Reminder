package com.example.distancedetector.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.distancedetector.Activities.HealthTipsListAdapter;
import com.example.distancedetector.Models.Country;
import com.example.distancedetector.Models.HealthTips;
import com.example.distancedetector.R;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    private View c_view;
    private ListView tipsListVew;
    private ArrayList<HealthTips> tipsData = new ArrayList<HealthTips>();

    public NotificationFragment() {
        HealthTips tip1 = new HealthTips(1, "Get Vaccinated", R.drawable.image1);
        HealthTips tip2 = new HealthTips(2, "Wear a mask", R.drawable.image2);
        HealthTips tip3 = new HealthTips(3, "Stay 6 feet away from others", R.drawable.image3);
        HealthTips tip4 = new HealthTips(1, "Avoid crowds and poorly ventilated spaces", R.drawable.image4);
        HealthTips tip5 = new HealthTips(2, "Test to prevent spread to others", R.drawable.image5);
        HealthTips tip6 = new HealthTips(3, "Wash your hands often", R.drawable.image6);
        HealthTips tip7 = new HealthTips(1, "Cover coughs and sneezes", R.drawable.image7);
        HealthTips tip8 = new HealthTips(2, "Clean and disinfect", R.drawable.image8);
        HealthTips tip9 = new HealthTips(3, "Monitor your health daily", R.drawable.image9);
        tipsData.add(tip1);
        tipsData.add(tip2);
        tipsData.add(tip3);
        tipsData.add(tip4);
        tipsData.add(tip5);
        tipsData.add(tip6);
        tipsData.add(tip7);
        tipsData.add(tip8);
        tipsData.add(tip9);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        c_view = inflater.inflate(R.layout.fragment_notification, container, false);

        //Initialize the elements
        tipsListVew = c_view.findViewById(R.id.tipsList);

        //Initialize the list view
        tipsListVew.setAdapter(new HealthTipsListAdapter(getActivity(), R.layout.healthtips_list_view, tipsData));

        return c_view;
    }
}