package com.example.frankvega.shuttletrackerandroid.Api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Frank Vega on 4/8/2018.
 */

public class VehicleApi extends TrackingApi {
    private final String updatesUrl = "http://shuttles.rpi.edu/updates";
    @Override
    public String getData(String urlString) throws Exception{
        //Connect
        URL targetUrl = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();

        String line = "";
        StringBuffer data = new StringBuffer("");

        //Read data
        InputStream inStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        line = reader.readLine();
        while (line != null){
            data.append(line);
            line = reader.readLine();
        }

        //Close connection
        connection.disconnect();

        //Return data
        return data.toString();
    }
}

