package com.example.sinensis;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.sinensis.databinding.ActivityMapaBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class activity_mapa extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private ActivityMapaBinding binding;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location userCurrentLocation;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchView = findViewById(R.id.idSearchView);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                String location = searchView.getQuery().toString();

                // below line is to create a list of address
                // where we will store the list of all address.
                List<Address> addressList = null;

                // checking if the entered location is null or not.
                if (location != null || location.equals("")) {
                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(activity_mapa.this);
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    Address address = addressList.get(0);

                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    // on below line we are adding marker to that position.
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                    // below line is to animate camera to that position.
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        binding.imgZoomOut.setOnClickListener(this);
        binding.imgZoomIn.setOnClickListener(this);
        binding.btnLocation.setOnClickListener(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        initLocationListener();
        getUserCurrentLocation();
        setMarkerAtmyLocation();


    }

    private void getUserCurrentLocation() {
        if (isPermissionGranted()) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                Log.v("method_called","here");
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    isPermissionGranted();
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            } else {
                userCurrentLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        if (userCurrentLocation!=null) {
            Log.v("lat_long", "lat:" + userCurrentLocation.getLatitude() + ",longi:" + userCurrentLocation.getLongitude());
            binding.edtLocation.setText("lat:" + userCurrentLocation.getLatitude() + ",longi:" + userCurrentLocation.getLongitude());
        }
    }
    private boolean isPermissionGranted() {
        Boolean permissionGranted=true;
        String[] permissions=new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        if (ActivityCompat.checkSelfPermission(this, permissions[0])!= PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this, permissions[1])!= PackageManager.PERMISSION_GRANTED
        ){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions,123);
            }
        }
        return permissionGranted;
    }

    private void initLocationListener() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                userCurrentLocation=location;
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                LocationListener.super.onProviderEnabled(provider);
                Toast.makeText(activity_mapa.this, "Provider Enabled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                LocationListener.super.onProviderDisabled(provider);
                Toast.makeText(activity_mapa.this, "Provider disable", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                LocationListener.super.onStatusChanged(provider, status, extras);
                Toast.makeText(activity_mapa.this, "Status changed", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getUserCurrentLocation();
        setMarkerAtmyLocation();
    }
    private void setMarkerAtmyLocation(){
        if (userCurrentLocation!=null){
            Log.v("curent_lat_long","lat:"+userCurrentLocation.getLatitude()+",longitute:"+userCurrentLocation.getLongitude());
            mMap.clear();
            Geocoder geocoder=new Geocoder(this);
            String userAddress="";
            try {
                List<Address>  addressList=geocoder.getFromLocation(userCurrentLocation.getLatitude(),userCurrentLocation.getLongitude(),5);
                Address address=addressList.get(0);
                userAddress=address.getLocality()+","+address.getAdminArea()+","+address.getCountryName()+","+address.getPostalCode();
                binding.edtLocation.setText(""+userAddress);

            } catch (IOException e) {
                e.printStackTrace();
            }
            LatLng myLocation = new LatLng(userCurrentLocation.getLatitude(), userCurrentLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(myLocation).title(""+userAddress));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        }
    }

    @Override
    public void onClick(View view) {
        Log.i("click_event","called");
        switch (view.getId()){
            case R.id.imgZoomIn:
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                break;
            case R.id.imgZoomOut:
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
                break;
            case R.id.btnLocation:
                getUserCurrentLocation();
                setMarkerAtmyLocation();
                break;
        }
    }
}