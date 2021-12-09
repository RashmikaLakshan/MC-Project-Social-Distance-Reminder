package com.example.distancedetector.Statistics;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.distancedetector.Activities.CountryListAdapter;
import com.example.distancedetector.Models.Country;
import com.example.distancedetector.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WorldStatusFragment extends Fragment {

    private View c_view;
    private ListView countryListVew;
    private ArrayList<Country> countriesData = new ArrayList<Country>();

    public WorldStatusFragment() {
//        //testinggggggg
//        Country country1 = new Country(1, "Australia", 200, 120, 3);
//        Country country2 = new Country(1, "Sri Lanka", 5640, 3289, 956);
//        this.countriesData.add(country1);
//        this.countriesData.add(country2);

//        getData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
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

    public void getData(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this.getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,"https://jsonplaceholder.typicode.com/todos",null,new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0;i<response.length();i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        Log.d("my-api","==== "+jsonObject.getString("title"));
                        Log.d("my-api","==== "+jsonObject.getString("userId"));
                        Log.d("my-api","==== "+jsonObject.getString("id"));
                        countriesData.add( new Country(Integer.parseInt(jsonObject.getString("userId")),jsonObject.getString("title"),Integer.parseInt(jsonObject.getString("id")), Integer.parseInt(jsonObject.getString("id")), Integer.parseInt(jsonObject.getString("id"))));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
//                recyclerView.setAdapter(new Users(arrayList));
            }
        }, error -> Log.d("my-api","went Wrong"));

        requestQueue.add(jsonArrayRequest);
    }
}