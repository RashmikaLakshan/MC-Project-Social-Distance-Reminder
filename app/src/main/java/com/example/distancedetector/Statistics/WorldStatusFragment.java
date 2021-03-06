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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.distancedetector.Activities.CountryListAdapter;
import com.example.distancedetector.Models.Country;
import com.example.distancedetector.R;

import org.json.JSONArray;
import org.json.JSONException;
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

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getData();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        c_view = inflater.inflate(R.layout.fragment_world_status, container, false);

        //Initialize the elements
        countryListVew = c_view.findViewById(R.id.countryList);

        //Get Data
        getData();


        // Inflate the layout for this fragment
        return c_view;
    }

    public void getData(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this.getActivity());
        String API_URL = "https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases2_v1/FeatureServer/2/query?where=1%3D1&outFields=*&outSR=4326&f=json";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,API_URL,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray features = (JSONArray) response.get("features");
                    int id, confirmed, death, recoverd;
                    String countryName;
                    for (int i=0;i<features.length();i++){
                        JSONObject country = features.getJSONObject(i).getJSONObject("attributes");
//                        Log.d("my-api","==== "+country.getString("OBJECTID"));
//                        Log.d("my-api","==== "+country.getString("Country_Region"));
//                        Log.d("my-api","==== "+country.getString("Confirmed"));
//                        Log.d("my-api","==== "+country.getString("Deaths"));
//                        Log.d("my-api","==== "+country.getString("Recovered"));

                        id = Integer.parseInt(country.getString("OBJECTID"));
                        countryName = country.getString("Country_Region");
                        confirmed = country.getString("Confirmed") == "null" ? 0 : Integer.parseInt(country.getString("Confirmed"));
                        recoverd = country.getString("Recovered") == "null" ? Integer.parseInt(country.getString("Confirmed")) - Integer.parseInt(country.getString("Deaths")) : Integer.parseInt(country.getString("Recovered"));
                        death = country.getString("Deaths") == "null" ? 0 : Integer.parseInt(country.getString("Deaths"));

                        countriesData.add( new Country(id,countryName,confirmed, recoverd, death));
                    }
                    countryListVew.setAdapter(new CountryListAdapter(getActivity(), R.layout.country_list_view, countriesData));
                }catch (Exception e){
                    e.printStackTrace();
                }
//                recyclerView.setAdapter(new Users(arrayList));
            }
        }, error -> Log.d("my-api","went Wrong"));

        requestQueue.add(jsonArrayRequest);
    }


}