package com.example.distancedetector.Models;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Objects;

/**
 * For calculate the distance from RSSI
 */

public class DistanceCalculator {

    public String deviceType;

    public double calculateDistance(Context context, int rssi, String macAddress){


        getDeviceType(context, macAddress, new VolleyCallback(){
            @Override
            public String onSuccessResponse(String result) {
                System.out.println(result);
                deviceType = result;
                return result;
            }
        });
        double distance = distanceCalculator(deviceType, rssi);
        return distance;
    }


    public void getDeviceType(Context context, String macAddress, final VolleyCallback callback){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String API_URL = "https://api.macaddress.io/v1?apiKey=at_uwIK4IWLQe4AOsyZx2uH4fZi08t0K&output=json&search="+ macAddress;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,API_URL,null,new Response.Listener<JSONObject>(){
            public String deviceBrand;
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject data = (JSONObject) response.getJSONObject("vendorDetails");
                    this.deviceBrand = data.getString("companyName");
                    callback.onSuccessResponse(this.deviceBrand);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            public String getDeviceBrand(){
                return this.deviceBrand;
            }
        },error -> Log.d("my-api","went Wrong"));

        requestQueue.add(jsonObjectRequest);
//        return jsonObjectRequest

    }

    public double distanceCalculator(String type, int rssi){
        double distance = 0;

        if (Objects.equals(type, "Xiaomi Communications Co Ltd")){
            System.out.println("Xiaomi Communications Co Ltd");
            distance = xiomiDistance(rssi);
        } else if( Objects.equals(type, "Samsung Electronics Co, Ltd" )){
            System.out.println("Samsung");
            distance = samsungDistance(rssi);
        } else {
            distance = xiomiDistance(rssi);
        }

        System.out.println("TYPE: "+type+"DISTANCE: "+ distance);

        return distance;
    }

    public double xiomiDistance(int rssi){
        if(rssi>-46){
            return 0;
        }else if(rssi>-55){
            return 1;
        }else if(rssi>-70){
            return 2;
        }else if(rssi>-80){
            return 3;
        }else {
            return 5;
        }
    }

    public double samsungDistance(int rssi){
        if(rssi>-46){
            return 0;
        }else if(rssi>-55){
            return 1;
        }else if(rssi>-70){
            return 2;
        }else if(rssi>-80){
            return 3;
        }else {
            return 5;
        }
    }

}

interface VolleyCallback {
    String onSuccessResponse(String result);
}


