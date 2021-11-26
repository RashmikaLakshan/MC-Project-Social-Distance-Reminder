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
        HealthTips tip1 = new HealthTips(1, "Wash your Hands", R.drawable.handwash);
        HealthTips tip2 = new HealthTips(2, "keep distance from your unsafe", R.drawable.covid);
        HealthTips tip3 = new HealthTips(3, "third notification", R.drawable.handwash);
        tipsData.add(tip1);
        tipsData.add(tip2);
        tipsData.add(tip3);
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