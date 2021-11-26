package com.example.distancedetector.Statistics;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.distancedetector.Activities.DistrictListAdapter;
import com.example.distancedetector.Models.Country;
import com.example.distancedetector.R;

import java.util.ArrayList;

public class InfectedAreasFragment extends Fragment {

    private View c_view;
    private ListView districtListVew;
    private ArrayList<Country> districtsData = new ArrayList<Country>();

    public InfectedAreasFragment() {
        //testinggggggg
        Country country1 = new Country(1, "Galle", 2000, 1200, 30);
        Country country2 = new Country(1, "Matara", 560, 328, 9);
        Country country3 = new Country(1, "Hambantota", 483, 562, 61);

        this.districtsData.add(country1);
        this.districtsData.add(country2);
        this.districtsData.add(country3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        c_view = inflater.inflate(R.layout.fragment_infected_areas, container, false);

        //Initialize the elements
        districtListVew = c_view.findViewById(R.id.distirctList);

        //Initialize the list view
        districtListVew.setAdapter(new DistrictListAdapter(getActivity(), R.layout.district_list_view, districtsData));

        return c_view;
    }
}