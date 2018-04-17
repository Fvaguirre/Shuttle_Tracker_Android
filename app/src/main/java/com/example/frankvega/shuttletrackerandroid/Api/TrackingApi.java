package com.example.frankvega.shuttletrackerandroid.Api;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Frank Vega on 4/8/2018.
 */

public abstract class TrackingApi {
    private final String TAG = TrackingApi.class.getName();
    private static String updatesUrl;
//    public static String routesUrl = "http://shuttles.rpi.edu/routes";
//    public static String stopsUrl = "http://shuttles.rpi.edu/stops";

    abstract String getData(String urlString) throws Exception;

    public String getUpdates(){
        try {
//            Log.d("TEST", this.updatesUrl);
            String updatesData = getData(this.updatesUrl);
            Log.d("EXAMPLE", updatesData);
            return updatesData;
        }
        catch (Exception e){
            Log.v(TAG, e.toString());
        }
        return "";
    }

}




