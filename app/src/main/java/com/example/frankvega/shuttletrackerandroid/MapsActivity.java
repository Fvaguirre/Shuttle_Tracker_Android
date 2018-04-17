package com.example.frankvega.shuttletrackerandroid;

import com.example.frankvega.shuttletrackerandroid.Api.*;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.AsyncTask;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import org.json.JSONArray;
import org.json.JSONObject;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TileOverlay mCampusMap;
    private final String TAG = MapsActivity.class.getName();

    //    TileOverlay tileOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//        checkSetUpMap();
//    }
//
//    @Override
//    protected void onResume(){
//        super.onResume();
//        checkSetUpMap();
//    }
//
//    private void checkSetUpMap(){
//        //Check to see if map has already been instantiated
//        if (mMap == null){
//            //Try to obtain map
//            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map2);
//            mapFrag.getMapAsync(this);
//
//            if (mMap != null){
//                SetUpMap();
//            }
//        }
//    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap(mMap);
//        TileProvider tileProvider = new UrlTileProvider(256,256) {
//            @Override
//            public URL getTileUrl(int x, int y, int zoom) {
//                //URL location of tile
//                String s = String.format("https://stamen-tiles.a.ssl.fastly.net/toner-lite/%d/%d/%d.png",
//                        zoom, x, y);
//                if (!checkTileExists(x, y, zoom)){
//                    return null;
//                }
//                try{
//                    return new URL(s);
//                }
//                catch (MalformedURLException e){
//                    throw new AssertionError(e);
//                }
//            }
//            public boolean checkTileExists(int x, int y, int zoom){
//                int minZoom = 14;
//                int maxZoom = 17;
//                if ((zoom < minZoom || zoom > maxZoom)){
//                    return false;
//                }
//                else{
//                    return true;
//                }
//            }
//        };

//        mCampusMap = mMap.addTileOverlay(new TileOverlayOptions()
//                .tileProvider(tileProvider));


//        // Add a marker in Sydney and move the camera
//        LatLng Union = new LatLng(42.73,-73.6767);
//        mMap.addMarker(new MarkerOptions().position(Union).title("Marker of RPI Union"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(Union));
//        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(Union, 15.3f) );

    }

    private void setUpMap(GoogleMap mMap) {

        //Set up tiling for google maps
        TileProvider tileProvider = new UrlTileProvider(256, 256) {
            @Override
            public URL getTileUrl(int x, int y, int zoom) {
                //URL location of tile
                String s = String.format("https://stamen-tiles.a.ssl.fastly.net/toner-lite/%d/%d/%d.png",
                        zoom, x, y);
                if (!checkTileExists(x, y, zoom)) {
                    return null;
                }
                try {
                    return new URL(s);
                } catch (MalformedURLException e) {
                    throw new AssertionError(e);
                }
            }

            public boolean checkTileExists(int x, int y, int zoom) {
                int minZoom = 14;
                int maxZoom = 17;
                if ((zoom < minZoom || zoom > maxZoom)) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        //Set max an min zoom on map
        mMap.setMinZoomPreference(14.0f);
        mMap.setMaxZoomPreference(17.0f);

        mCampusMap = mMap.addTileOverlay(new TileOverlayOptions()
                .tileProvider(tileProvider));

        //On startup, position the camera over the Union
        LatLng Union = new LatLng(42.73, -73.6767);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Union, 14.3f));
        //Update the map's assets
        checkMapUpdates();
    }

    private void checkMapUpdates() {
        final Handler updatesHandler = new Handler();
        Timer updatesTimer = new Timer();

        TimerTask updatesTask = new TimerTask() {
            @Override
            public void run() {
                updatesHandler.post(new Runnable() {
                    public void run() {
//                        new MapUpdates().execute();
                    }
                });
            }
        };
        updatesTimer.schedule(updatesTask, 0, 7000);
    }

    RouteApi rApi = new RouteApi();
    String s = rApi.getUpdates();
    String a = s + "the";




}

//    private class MapUpdates extends AsyncTask<Void, Void, ArrayList<Vehicle>>{
//        private final String TAG = MapUpdates.class.getName();
//
//        protected ArrayList<Vehicle> doInBackground(Void...params){
//            TrackingApi api = new TrackingApi();
//            String updates = api.getUpdates();
//
//            try{
//                ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
//                JSONArray JSONupdate = new JSONArray(updates);
//
//                for (int i = 0; i < JSONupdate.length(); i++){
//                    JSONObject updateObj = JSONupdate.getJSONObject(i).getJSONObject("vehicle");
//                    Vehicle aVehicle = new Vehicle(updateObj);
//                    vehicles.add(aVehicle);
//                }
//                return vehicles;
//            }
//            catch (Exception e){
//                Log.v(TAG, e.toString());
//            }
//            return null;
//        }
//        protected void onPostExecute(ArrayList<Vehicle> vehicles) {
//            updateShuttlePositions(vehicles);
//            drawRoutes();
////            placeStops();
//        }
//    }
//
//    private void updateShuttlePositions(ArrayList<Vehicle> vehicles){
//        mMap.clear();
//        for (int i = 0; i < vehicles.size(); i++) {
//            Vehicle aVehicle = vehicles.get(i);
//            mMap.addMarker(new MarkerOptions().position(
//                    new LatLng(aVehicle.lat, aVehicle.lng))
//                    .title(aVehicle.name).icon(BitmapDescriptorFactory.fromResource(R.drawable.shuttle))
//                    .anchor((float) 0.5, (float) 0.5)
//                    .rotation(aVehicle.heading - 90));
//        }
//    }
//
//    private void drawRoutes(){
////        String[] eastCoords = getResources().getStringArray(R.array.east_campus_route);
//    }
//
//}
