package com.example.kanika.smartcitytraveller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class Location extends AppCompatActivity implements PlaceSelectionListener, OnConnectionFailedListener  {

    protected TextView mPlaceDetailsText;
    public static String latitude="";
    public static String longitude="";
    protected TextView mPlaceAttribution;
    private static final String TAG = MainActivity.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    TextView tvPlace;
    int PLACE_PICKER_REQUEST = 1;
    Button plan;
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    public static final String location = "LocationKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mGeoDataClient = Places.getGeoDataClient(this, null);
        getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        tvPlace = (TextView) findViewById(R.id.tvPlace);
        plan=(Button) findViewById(R.id.plan);


        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.
        autocompleteFragment.setOnPlaceSelectedListener(this);

        // Retrieve the TextViews that will display details about the selected place.
        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(Location.this, Chosen_Places.class);
                startActivity(i);


            }
        });

    }
    /**
     * Callback invoked when a place has been selected from the PlaceAutocompleteFragment.
     */
    @Override
    public void onPlaceSelected(Place place) {

        latitude=Double.toString(place.getLatLng().latitude);
        //b.setText(latitude);
        longitude=Double.toString(place.getLatLng().longitude);
        sharedpreferences=getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        Log.i(TAG, "Place Selected: " + place.getName());
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(location, latitude );
        editor.putString(location, longitude);
        editor.commit();




        // Format the returned place's details and display them in the TextView.
        mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(),
                place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri()));

        CharSequence attributions = place.getAttributions();
        if (!TextUtils.isEmpty(attributions)) {
            mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
        } else {
            mPlaceAttribution.setText("");
        }
    }

    /**
     * Callback invoked when PlaceAutocompleteFragment encounters an error.
     */
    @Override
    public void onError(Status status) {
        Log.e(TAG, "onError: Status = " + status.toString());

        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper method to format information about a place nicely.
     */
    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    public void getPlace(View v) {
        if(mGoogleApiClient == null || !mGoogleApiClient.isConnected())
            return;

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch ( GooglePlayServicesRepairableException e) {
            Log.d(TAG, "GooglePlayServicesRepairableException thrown");
        } catch ( GooglePlayServicesNotAvailableException e) {
            Log.d(TAG, "GooglePlayServicesNotAvailableException thrown");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            displayPlace(PlacePicker.getPlace( data, this ));
        }
    }

    private void displayPlace(Place place) {
        if (place == null)
            return;

        StringBuilder builder = new StringBuilder();

        if(!TextUtils.isEmpty(place.getName())) {
            builder.append("Name: " + place.getName() + "\n");
        }
        if(!TextUtils.isEmpty( place.getAddress())) {
            builder.append("Address: " + place.getAddress() + "\n");
        }
        if(!TextUtils.isEmpty( place.getPhoneNumber())) {
            builder.append("Phone: " + place.getPhoneNumber());
        }

        tvPlace.setText(builder.toString());


    }


    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION )
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please allow ACCESS_COARSE_LOCATION persmission.",
                    Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }



}
