package com.example.ms.piratilapp.Gamer.Fragment;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ms.piratilapp.R;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.example.ms.piratilapp.Class.*;

import java.util.List;

public class DimondFinderFragment extends Fragment {

    private MapView mapView;
    private MapboxMap map;
    private PermissionsManager permissionsManager;
    LocationEngine getLocationEngine;
    Location location;
    LocationEngine locationEngine;
    RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Mapbox.getInstance(getContext(), "pk.eyJ1IjoiZGFsaXJlemEiLCJhIjoiY2p1Z2k1cXZsMHBkZzQ5cnIxajA2c2RlNyJ9.UOBgdrt6MM5glyeBn4xkSw");
        View view = inflater.inflate(R.layout.fragment_dimond_finder,container,false);

        requestQueue = Volley.newRequestQueue(getContext());





        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {


                        map = mapboxMap;
                        LatLng latLng = new LatLng(31.335858, 48.687427);
                        map.setCameraPosition(new CameraPosition.Builder()
                                .target(latLng)
                                .build()
                        );
                        enableLocationComponent();
                        LocationComponentOptions locationComponentOptions = LocationComponentOptions.builder(getContext()).build();
                        LocationComponentActivationOptions locationComponentActivationOptions = LocationComponentActivationOptions
                                .builder(getContext(),map.getStyle())
                                .locationComponentOptions(locationComponentOptions)
                                .build();

                        LocationComponent locationComponent = mapboxMap.getLocationComponent();
                        locationComponent.activateLocationComponent(locationComponentActivationOptions);



// Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });
            }
        });

        return view;

    }

    private void enableLocationComponent() {

        LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE );
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(statusOfGPS){
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {

            // Get an instance of the component
            LocationComponent locationComponent = map.getLocationComponent();

            // Activate with a built LocationComponentActivationOptions object
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(getContext(), map.getStyle()).build());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
        }else{
            customToast customToast = new customToast(getContext(),"لطفا مکان یاب تلفن همراه خود را روشن کنید", com.example.ms.piratilapp.Class.customToast.info, com.example.ms.piratilapp.Class.customToast.Bottom);
            customToast.getToast().show();
        }

        } else {

            permissionsManager = new PermissionsManager(new PermissionsListener() {
                @Override
                public void onExplanationNeeded(List<String> permissionsToExplain) {

                }

                @Override
                public void onPermissionResult(boolean granted) {

                }
            });

            permissionsManager.requestLocationPermissions(getActivity());

        }
    }



}
