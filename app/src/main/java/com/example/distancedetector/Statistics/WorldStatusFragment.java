package com.example.distancedetector.Statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.distancedetector.Activities.CountryListAdapter;
import com.example.distancedetector.Models.Country;
import com.example.distancedetector.R;

import java.util.ArrayList;

public class WorldStatusFragment extends Fragment {

    private View c_view;
    private ListView countryListVew;
    private ArrayList<Country> countriesData = new ArrayList<Country>();

    public WorldStatusFragment() {
        //testinggggggg
        Country country1 = new Country(1, "Australia", 200, 120, 3);
        Country country2 = new Country(1, "Sri Lanka", 5640, 3289, 956);
        this.countriesData.add(country1);
        this.countriesData.add(country2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        c_view = inflater.inflate(R.layout.fragment_world_status, container, false);

        //Initialize the elements
        countryListVew = c_view.findViewById(R.id.countryList);

        //Initialize the list view
        countryListVew.setAdapter(new CountryListAdapter(getActivity(), R.layout.country_list_view, countriesData));

        // Inflate the layout for this fragment
        return c_view;
    }
}