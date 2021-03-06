package com.example.distancedetector.fragments;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.distancedetector.Activities.DeviceListAdapter;
import com.example.distancedetector.Models.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.distancedetector.Home.HomeViewPageAdapter;
import com.example.distancedetector.R;
import com.example.distancedetector.Widget.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private View c_view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Check permission for location
        int permissionCheck = getActivity().checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
        permissionCheck += getActivity().checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
        if (permissionCheck != 0) {

            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
        }

        // Inflate the layout for this fragment
        c_view =  inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout = c_view.findViewById(R.id.home_tab_layout);
        viewPager = c_view.findViewById(R.id.home_viewpager);

        HomeViewPageAdapter adapter = new HomeViewPageAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);

        tabLayout.setupWithViewPager(viewPager);
        return c_view;
    }

}