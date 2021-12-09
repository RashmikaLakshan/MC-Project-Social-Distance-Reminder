package com.example.distancedetector.Statistics;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.distancedetector.Activities.DistrictListAdapter;
import com.example.distancedetector.Models.Country;
import com.example.distancedetector.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class InfectedAreasFragment extends Fragment {

    private View c_view;
    private ListView districtListVew;
    private ArrayList<Country> districtsData = new ArrayList<Country>();

    public InfectedAreasFragment() {
//        //testinggggggg
//        Country country1 = new Country(1, "Galle", 2000, 1200, 30);
//        Country country2 = new Country(1, "Matara", 560, 328, 9);
//        Country country3 = new Country(1, "Hambantota", 483, 562, 61);
//
//        this.districtsData.add(country1);
//        this.districtsData.add(country2);
//        this.districtsData.add(country3);
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
        c_view = inflater.inflate(R.layout.fragment_infected_areas, container, false);

        //Initialize the elements
        districtListVew = c_view.findViewById(R.id.distirctList);

        //Initialize the list view
        districtListVew.setAdapter(new DistrictListAdapter(getActivity(), R.layout.district_list_view, districtsData));

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
                        districtsData.add( new Country(Integer.parseInt(jsonObject.getString("userId")),jsonObject.getString("title"),Integer.parseInt(jsonObject.getString("id")), Integer.parseInt(jsonObject.getString("id")), Integer.parseInt(jsonObject.getString("id"))));
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