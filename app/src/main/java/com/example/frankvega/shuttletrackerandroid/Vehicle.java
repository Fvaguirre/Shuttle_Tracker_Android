package com.example.frankvega.shuttletrackerandroid;

import org.json.JSONObject;

/**
 * Created by Frank Vega on 4/8/2018.
 */

public class Vehicle {

    public String name;
    public int heading;
    public double lat;
    public double lng;

    public Vehicle(JSONObject data) throws Exception {
        this.name = data.getString("name");
        JSONObject latestPos = data.getJSONObject("latest_position");
        this.heading = latestPos.getInt("heading");
        this.lat = latestPos.getDouble("latitude");
        this.lng = latestPos.getDouble("longitude");
    }

}
