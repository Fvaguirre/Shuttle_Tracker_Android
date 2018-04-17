package com.example.frankvega.shuttletrackerandroid.Api;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Frank Vega on 4/8/2018.
 */

public class RouteApi extends TrackingApi {
//    private static String updatesUrl = "http://shuttles.rpi.edu/routes";
    @Override
    public String getData(String urlString) throws Exception{
        // Connect to our API
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String line = "";
        StringBuffer data = new StringBuffer("");

        // Read and return API response data
        InputStream instr = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(instr));

        while ((line = rd.readLine()) != null) {
            data.append(line);
        }

        connection.disconnect();
        Log.d("TEST", data.toString());
        return data.toString();
    }
}
