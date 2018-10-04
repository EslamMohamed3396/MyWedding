package com.example.eslam.mywedding.Activites;


import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Adapters.CustomInfoWindowAdapter;
import com.example.eslam.mywedding.Adapters.ListOffersHomeAdapters;
import com.example.eslam.mywedding.HelperMethod.ConnectionDetector;
import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.NearbyLocations.NearbyLocation;
import com.example.eslam.mywedding.Models.Offers.Offer;
import com.example.eslam.mywedding.Models.UserModle;
import com.example.eslam.mywedding.R;
import com.example.eslam.mywedding.ViewModels.ViewOffersHalls;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener, ListOffersHomeAdapters.offer {

    private static final String FINE_lOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_lOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int CODE = 1;
    private static final String OFFERS = "offers";
    private static final String OFFERS_SERVICE = "offers_service";
    private static final String OFFERS_HALL_IMAGE = "offers_image";
    private boolean mLocation = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Float CAMER_ZOOM = 15f;
    private boolean gps_enabled;
    private boolean network_enabled;
    private ListOffersHomeAdapters listHallsAdapters;
    private GridLayoutManager mGridLayoutManager;
    private ApiInterface mApiInterface;
    @BindView(R.id.fab_login)
    Button btn_login;
    @BindView(R.id.fab_home)
    Button btn_home;
    @BindView(R.id.fab_sign_up)
    Button btn_sign;
    @BindView(R.id.fab_account)
    Button btn_account_profile;
    @BindView(R.id.rc_home_offers)
    RecyclerView rv_home_offers;

    private HashMap<String, String> markers;
    private GoogleApiClient mGoogleApiClient;
    String latitude = "", longitude = "";
    private String placeName;
    private String vicinity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (ConnectionDetector.isConnectingToInternet(this)) {
            if (checkGPSStatus()) {
                getLocationPermission();
            }
            loadListOffers();
        } else {
            Toasty.error(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
        }
        checkUserIfLogIn();
        clickButton();

    }


    @Override
    protected void onResume() {
        super.onResume();
        checkUserIfLogIn();

    }

    private void preapareTheRecyclreOffers() {
        listHallsAdapters = new ListOffersHomeAdapters(new ArrayList<Offer>(), this);
        mGridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rv_home_offers.setLayoutManager(mGridLayoutManager);
        rv_home_offers.setAdapter(listHallsAdapters);
    }

    private void loadListOffers() {

        preapareTheRecyclreOffers();

        ViewOffersHalls model = ViewModelProviders.of(this).get(ViewOffersHalls.class);
        model.getOffers().observe(this, new Observer<List<Offer>>() {
            @Override
            public void onChanged(@Nullable List<Offer> offers) {
                if (offers != null) {
                    listHallsAdapters.setList(offers);

                } else {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkUserIfLogIn() {
        UserModle user = Shared_Preferences.getUser(this);
        if (user != null) {
            btn_login.setVisibility(View.GONE);
            btn_sign.setVisibility(View.GONE);


            btn_home.setVisibility(View.VISIBLE);
            btn_account_profile.setVisibility(View.VISIBLE);

        } else {
            btn_login.setVisibility(View.VISIBLE);
            btn_sign.setVisibility(View.VISIBLE);


            btn_home.setVisibility(View.GONE);
            btn_account_profile.setVisibility(View.GONE);

        }

    }

    private void clickButton() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LogIn.class));
            }
        });
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HomePage.class));
                finish();
            }
        });
        btn_account_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Profile.class));

            }
        });
    }

    private void getLocationPermission() {
        String[] Permission = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_lOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext()
                    , COARSE_lOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocation = true;
                initMaps();
            } else {
                ActivityCompat.requestPermissions(this, Permission, CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, Permission, CODE);
        }
    }

    private void cameraMove(LatLng latLng, Float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void cameraMoveMyLocation(LatLng latLng, Float zoom, String s) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        mMap.addMarker(new MarkerOptions().position(latLng).title(s));
    }

    private void initMaps() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_content);
        supportMapFragment.getMapAsync(this);
    }

    private boolean checkGPSStatus() {
        LocationManager locationManager = null;
        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (!gps_enabled && !network_enabled) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("GPS not enabled");
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //this will navigate user to the device location settings screen
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            AlertDialog alert = dialog.create();
            alert.show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocation = false;
        switch (requestCode) {
            case CODE:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocation = false;
                            return;
                        }
                    }
                    mLocation = true;
                    initMaps();
                }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mLocation) {
            getDeviceLocation();
        }
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocation) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                if (location != null) {
                    location.addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Location currentLocation1 = (Location) task.getResult();
                                if (currentLocation1 != null) {
                                    latitude = String.valueOf(currentLocation1.getLatitude());
                                    longitude = String.valueOf(currentLocation1.getLongitude());
                                    cameraMoveMyLocation(new LatLng(currentLocation1.getLatitude(), currentLocation1.getLongitude()), CAMER_ZOOM, getString(R.string.loc));
                                    init();
                                } else {
                                    Toasty.error(MainActivity.this, getString(R.string.error_location), Toast.LENGTH_LONG).show();
                                }
                            } else

                            {
                                Toasty.error(MainActivity.this, getString(R.string.error_location), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        } catch (
                SecurityException e)

        {
            Toasty.error(MainActivity.this, "" + R.string.error, Toast.LENGTH_LONG).show();
        }

    }

    private void init() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        new LoadNearItems().execute();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(Offer offer) {
        Intent intent = new Intent(MainActivity.this, DetailsOffersActivity.class);
        intent.putParcelableArrayListExtra(OFFERS_SERVICE, (ArrayList<? extends Parcelable>) offer.getServiceData());
        intent.putParcelableArrayListExtra(OFFERS_HALL_IMAGE, (ArrayList<? extends Parcelable>) offer.getHall().getImageModels());
        intent.putExtra(OFFERS, offer);
        startActivity(intent);
    }

    private void zoomToDistance(double distance) {

        double scale = distance / 500;
        float currentZoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
        float animateZomm = (float) (currentZoomLevel - 0 - .5);
        double late = Double.parseDouble(latitude);
        double lnge = Double.parseDouble(longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(late, lnge), animateZomm));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(animateZomm), 2000, null);
        // map.setOnMarkerClickListener(onMapMarkerClickListener);
    }


    private class LoadNearItems extends AsyncTask<Void, Void, Void> {

        double distance = 999999999;

        @Override
        protected Void doInBackground(Void... voids) {
            mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            HashMap<String, String> body = new HashMap<>();


            body.put("latitude", latitude);
            body.put("longitude", longitude);


            Call<NearbyLocation> call = mApiInterface.getNearByLocation(body);

            call.enqueue(new Callback<NearbyLocation>() {
                @Override
                public void onResponse(Call<NearbyLocation> call, Response<NearbyLocation> response) {

                    try {
                        mMap.clear();
                        if (response.isSuccessful()) {

                            if (response.body().getListings() == null) {
                                Toast.makeText(MainActivity.this, "No Halls Here", Toast.LENGTH_LONG).show();
                            }


                            for (int i = 0; i < response.body().getListings().size(); i++) {
                                double late = Double.parseDouble(response.body().getListings().get(i).getLat());
                                double lnge = Double.parseDouble(response.body().getListings().get(i).getLng());

                                placeName = response.body().getListings().get(i).getName();
                                vicinity = response.body().getListings().get(i).getAddress();

                                markers = new HashMap<>();
                                LatLng latLng = new LatLng(late, lnge);

                                Marker mark = mMap.addMarker(new MarkerOptions()
                                        .position(latLng)
                                        .title(placeName)
                                        .snippet(vicinity)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_home)));
                                for (int k = 0; k < response.body().getListings().get(i).getImages().size(); k++) {
                                    markers.put(mark.getId(), response.body().getListings().get(i).getImages().get(k).getImageUrl());
                                }
                                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MainActivity.this, markers.get(mark.getId())));

                                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                    @Override
                                    public void onInfoWindowClick(Marker marker) {
                                        Toasty.info(MainActivity.this, "" + marker.getTitle(), Toast.LENGTH_LONG).show();
                                    }
                                });
                                cameraMove(latLng, CAMER_ZOOM);
                                double tempDistance = response.body().getListings().get(i).getDistance();
                                if (tempDistance <= distance) {
                                    distance = tempDistance;
                                }

                            }
                            zoomToDistance(distance);
                        }
                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error " + e.getMessage());
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<NearbyLocation> call, Throwable t) {


                }
            });
            return null;
        }
    }
}

