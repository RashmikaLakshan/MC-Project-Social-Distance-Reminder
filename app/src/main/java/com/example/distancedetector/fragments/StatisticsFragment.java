package com.example.distancedetector.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.distancedetector.Activities.CountryListAdapter;
import com.example.distancedetector.Models.Country;
import com.example.distancedetector.R;
import com.example.distancedetector.Statistics.StatisticsViewPageAdapter;
import com.example.distancedetector.Widget.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

public class StatisticsFragment extends Fragment {

    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private View c_view;

    TextView confirmView, activeView, deathView;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        c_view = inflater.inflate(R.layout.fragment_statistics, container, false);

        tabLayout = c_view.findViewById(R.id.tab_layout);
        viewPager = c_view.findViewById(R.id.statistics_viewpager);

        confirmView = c_view.findViewById(R.id.confirmed);
        activeView = c_view.findViewById(R.id.active);
        deathView = c_view.findViewById(R.id.deaths);

        StatisticsViewPageAdapter adapter = new StatisticsViewPageAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);

        tabLayout.setupWithViewPager(viewPager);

        getData();

        return c_view;
    }

    public void getData(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this.getActivity());
        String API_URL = "https://www.hpb.health.gov.lk/api/get-current-statistical";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,API_URL,null,new Response.Listener<JSONObject>(){
            String confirmed, death, active;
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONObject data = (JSONObject) response.get("data");

                    confirmed = data.getString("local_new_cases") == "null" ? "0" : data.getString("local_new_cases");
                    active = data.getString("local_active_cases") == "null" ? "0" : data.getString("local_active_cases");
                    death = data.getString("local_new_deaths") == "null" ? "0" : data.getString("local_new_deaths");

                    confirmView.setText(confirmed);
                    activeView.setText(active);
                    deathView.setText(death);



                }catch (Exception e){
                    e.printStackTrace();
                }
//                recyclerView.setAdapter(new Users(arrayList));
            }
        }, error -> Log.d("my-api","went Wrong"));

        requestQueue.add(jsonArrayRequest);
    }

}