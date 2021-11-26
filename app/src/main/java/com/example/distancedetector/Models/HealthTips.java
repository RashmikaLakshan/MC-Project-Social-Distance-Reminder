package com.example.distancedetector.Models;

/**
 * For the Devices
 */

public class HealthTips {
    private int tipId;
    private String tipDescription = null;
    private Integer tipReference;

    public HealthTips(int id){
        this.tipId = id;
    }

    public HealthTips(int id, String name){
        this.tipId = id;
        this.tipDescription = name;
    }

    public HealthTips(int id, String tipDescription, int tipReference){
        this.tipId = id;
        this.tipDescription = tipDescription;
        this.tipReference = tipReference;
    }

    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    public String getTipDescription() {
        return tipDescription;
    }

    public void setTipDescription(String tipDescription) {
        this.tipDescription = tipDescription;
    }

    public Integer getTipReference() {
        return tipReference;
    }

    public void setTipReference(Integer tipReference) {
        this.tipReference = tipReference;
    }
}
