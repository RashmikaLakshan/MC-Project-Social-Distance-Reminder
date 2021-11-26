package com.example.distancedetector.Models;

/**
 * For the Devices
 */

public class Country {
    private int countryId;
    private String countryName = null;
    private int noOfConfirmed = 0;
    private int noOfRecovered = 0;
    private int noOfDeaths = 0;

    public Country(int id){
        this.countryId = id;
    }

    public Country(int id, String name){
        this.countryId = id;
        this.countryName = name;
    }

    public Country(int id, String countryName, int noOfConfirmed, int noOfRecovered, int noOfDeaths){
        this.countryId = id;
        this.countryName = countryName;
        this.noOfConfirmed = noOfConfirmed;
        this.noOfRecovered = noOfRecovered;
        this.noOfDeaths = noOfDeaths;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getNoOfConfirmed() {
        return noOfConfirmed;
    }

    public void setNoOfConfirmed(int noOfConfirmed) {
        this.noOfConfirmed = noOfConfirmed;
    }

    public int getNoOfRecovered() {
        return noOfRecovered;
    }

    public void setNoOfRecovered(int noOfRecovered) {
        this.noOfRecovered = noOfRecovered;
    }

    public int getNoOfDeaths() {
        return noOfDeaths;
    }

    public void setNoOfDeaths(int noOfDeaths) {
        this.noOfDeaths = noOfDeaths;
    }

}
