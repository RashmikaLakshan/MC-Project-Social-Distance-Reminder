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
import com.android.volley.toolbox.JsonObjectRequest;
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
//    public InfectedAreasFragment() {
//        districtsData.clear();
//        districtsData.add(new Country(1, "Colombo",124,6,5));
//        districtsData.add(new Country(1, "Gampaha",113,4,0));
//        districtsData.add(new Country(1, "Jaffna",18,1,2));
//        districtsData.add(new Country(1, "Galle",15,0,1));
//        districtsData.add(new Country(1, "Badulla",13,4,2));
//        districtsData.add(new Country(1, "Kurunagala",13,1,0));
//        districtsData.add(new Country(1, "Puttalam",10,2,1));
//        districtsData.add(new Country(1, "Kandy",8,3,0));
//        districtListVew.setAdapter(new DistrictListAdapter(getActivity(), R.layout.district_list_view, districtsData));
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        c_view = inflater.inflate(R.layout.fragment_infected_areas, container, false);


        //Initialize the elements
        districtListVew = c_view.findViewById(R.id.distirctList);

        //Get Data
        getData();

        return c_view;
    }

    public void getData(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this.getActivity());
        String API_URL = "https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/localdata/services/ncov_cases2_v1/FeatureServer/2/query?where=1%3D1&outFields=*&outSR=4326&f=json";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,API_URL,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray features = (JSONArray) response.get("features");
                    int id, confirmed, death, recoverd;
                    String districtName;
                    for (int i=0;i<features.length();i++){
                        JSONObject country = features.getJSONObject(i).getJSONObject("attributes");

                        id = Integer.parseInt(country.getString("OBJECTID"));
                        districtName = country.getString("district");
                        confirmed = country.getString("Confirmed") == "null" ? 0 : Integer.parseInt(country.getString("Confirmed"));
                        recoverd = country.getString("Recovered") == "null" ? 0 : Integer.parseInt(country.getString("Recovered"));
                        death = country.getString("Deaths") == "null" ? 0 : Integer.parseInt(country.getString("Deaths"));

                        districtsData.add( new Country(id,districtName,confirmed, recoverd, death));
                    }
                    districtListVew.setAdapter(new DistrictListAdapter(getActivity(), R.layout.district_list_view, districtsData));
                }catch (Exception e){
                    e.printStackTrace();
                }
//                recyclerView.setAdapter(new Users(arrayList));
            }
        }, error -> Log.d("my-api","went Wrong"));

        requestQueue.add(jsonArrayRequest);
    }
}