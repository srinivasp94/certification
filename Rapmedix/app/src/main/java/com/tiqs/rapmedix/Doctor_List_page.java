package com.tiqs.rapmedix;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sohail on 1/5/2017.
 */
public class Doctor_List_page extends FragmentActivity implements LocationListener,OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener

{
    ArrayList<Integer> imag_res=new ArrayList<>();
    ArrayList<String> doctor_name=new ArrayList<>();
    ArrayList<String> mobile_number=new ArrayList<>();
    ListView doctor_list;
    CheckBox search_for;
    TextView header,address;
    EditText search_input;
    private ArrayList<Doctor_list_helper> deptList = new ArrayList<>();
    private LinkedHashMap<Integer, Doctor_list_helper> myDepartments = new LinkedHashMap<Integer, Doctor_list_helper>();
    InputMethodManager inputMethodManager;
    private AutoCompleteTextView actv;
    ArrayAdapter<String> adapter;
    ImageView menu,add_address;

    GoogleApiClient mGoogleApiClient;
    GoogleApiClient mGoogleApiClient1;
    GoogleMap googlemap1;
    PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(17.3660, 78.4760), new LatLng(17.3660, 78.4760));
    double currentLatitude,currentLongitude;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    SharedPreferences sp;
    public static final String pref="Location";
    double lat,longg;
    String selected_address="",id,location;
    MapFragment supportMapFragment;
    Doctor_list_adapter doctor_list_adapter;
    ScoreComparator compare;
    Distance_sorter distance_sorter;
    View  rootview;

    ArrayList<String>doctor_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.doctors_list);
        rootview =findViewById(R.id.root_view);

        doctor_list=(ListView)findViewById(R.id.doctors_list);
        menu=(ImageView)findViewById(R.id.menu);
        add_address=(ImageView)findViewById(R.id.add_address);
        address=(TextView) findViewById(R.id.location);

        doctor_names =new ArrayList<>();

        actv = (AutoCompleteTextView) findViewById(R.id.search_input);
        actv.setHint(Html.fromHtml("<small>" +
                "Enter Doctor Name" + "</small>"));

        if (getIntent()!=null) {
            currentLatitude = getIntent().getDoubleExtra("lat", 0.0);
            currentLongitude = getIntent().getDoubleExtra("longg", 0.0);
            id = getIntent().getStringExtra("id");
            Log.e("aa", currentLatitude + id + "ccc" + currentLongitude);

        }      //   address.setText(getIntent().getStringExtra("location"));

        final SharedPreferences sp = getSharedPreferences(Home_Page.pref,MODE_PRIVATE);
        currentLatitude=Double.longBitsToDouble(sp.getLong("lattitude", Double.doubleToLongBits(0.0)));
        currentLongitude=Double.longBitsToDouble(sp.getLong("longitude", Double.doubleToLongBits(0.0)));
        location=sp.getString("address","City");

        Log.e("aa", location+"ccc" + longg);
        compare = new ScoreComparator();
        distance_sorter=new Distance_sorter();



        //   location=getIntent().getStringExtra("location");
        Log.e("log", location+"Fetching details for ID: " + currentLongitude);

        if (location!=null)address.setText(location);

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("specialisation_id",id);
            Log.e("spc",""+id);
            jsonObject.put("latitude",currentLatitude);
            jsonObject.put("longitude",currentLongitude);

            mobile_verification(jsonObject, Constants.getDoctors);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter= new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,doctor_names);
        actv.setAdapter(adapter);


        search_for=(CheckBox)findViewById(R.id.search_for);
        header=(TextView) findViewById(R.id.header);

        inputMethodManager =
                (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        search_for.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    header.setVisibility(View.GONE);
                    actv.setVisibility(View.VISIBLE);
                    actv.requestFocus();


                    inputMethodManager.toggleSoftInputFromWindow(
                            compoundButton.getApplicationWindowToken(),
                            InputMethodManager.SHOW_FORCED, 0);
                    //search_input.setFocusable(true);

                }
                else
                {


                    header.setVisibility(View.VISIBLE);
                    actv.setVisibility(View.GONE);

                    inputMethodManager.toggleSoftInputFromWindow(
                            compoundButton.getApplicationWindowToken(),
                            InputMethodManager.RESULT_HIDDEN, 0);

                }
            }
        });
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int duration = 500;  //miliseconds
                int offset = 0;      //fromListTop
                String starttext=adapterView.getItemAtPosition(i).toString();
                int i0=doctor_names.indexOf(starttext);

                Log.e("zz",starttext+"  nn  "+i+"  aa  "+adapterView.getSelectedItemPosition());
                doctor_list.smoothScrollToPositionFromTop(  i0,offset,duration);

            }

        });



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });



        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location_popup();
            }
        });



    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(Doctor_List_page.this, v);

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu1, popup.getMenu());


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            private String filterInterval;
            private String filterTitle;

            @Override
            public boolean onMenuItemClick(
                    android.view.MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Sort_by_exp:
                    {
                        Collections.sort(deptList, compare);
                        doctor_list_adapter.notifyDataSetChanged();
                        fadein();

                    }

                    return true;

                    case R.id.Sort_by_distance:
                    {
                        Collections.sort(deptList, distance_sorter);
                        doctor_list_adapter.notifyDataSetChanged();

                        fadein();

                    }

                    return true;
                }
                return false;
            }
        });
        popup.show();

    }

    public  void fadein()
    {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setBackgroundColor(Color.RED);
        fadeIn.setDuration(3000);
        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        doctor_list.setAnimation(animation);
    }
    private  void mobile_verification(JSONObject jo, String url)
    {
        CustomAsync ca=new CustomAsync(Doctor_List_page.this, jo, url, new OnAsyncCompleteRequest() {

            @Override
            public void asyncResponse(String result) {
                // TODO Auto-generated method stub
                if(result==null||result.equals(""))
                {
                    Toast.makeText(Doctor_List_page.this, "Please Retry", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        JSONObject j = new JSONObject(result);
                        String status = j.getString("status");


                        if (status.equals("success"))
                        {
                            doctor_names.clear();

                            JSONArray doctors = j.getJSONArray("0");
                            Log.e("st", "st" + status);
                            for (int i = 0; i < doctors.length(); i++) {
                                JSONObject c = doctors.getJSONObject(i);
                               /* names.add();
                                ids.add(c.getString("id"));
                                specialisation_names.add();
                                experiences.add();
                                degree_names.add();
                                hospital_names.add();
                                distances.add();
                                profile_pics.add();*/

                                String name = c.getString("name");
                                String image = c.getString("profile_pic");
                                String spec = deDup(c.getString("specialisation_name"));
                                String degree = deDup(c.getString("degree_name"));
                                String hosp = c.getString("hospital_name");
                                String distance = c.getString("distance");
                                String exp = c.getString("experience");
                                String doctorId = c.getString("id");

                                doctor_names.add(name);
                                Doctor_list_helper doctor_list_helper = myDepartments.get(i);
                                doctor_list_helper = null;
                                if (doctor_list_helper == null) {
                                    doctor_list_helper = new Doctor_list_helper();
                                    doctor_list_helper.setImage_url(image);
                                    doctor_list_helper.setDoctor_name(name);
                                    doctor_list_helper.setDoctor_speciality(spec);
                                    doctor_list_helper.setDoctor_degree(degree);
                                    doctor_list_helper.setDoctor_hospital(hosp);
                                    doctor_list_helper.setDoctor_distance(distance);
                                    doctor_list_helper.setExperience(exp);
                                    doctor_list_helper.setDoctorId(doctorId);

                                    myDepartments.put(i, doctor_list_helper);
                                    deptList.add((i), doctor_list_helper);
                                }
                                //    Collections.sort(deptList.get(i).getExperience(),));

                            }



                            doctor_list_adapter = new Doctor_list_adapter(Doctor_List_page.this, deptList);
                            // doctor_list.setAdapter(new Doctor_list_adapter(Doctor_List_page.this,profile_pics,names,specialisation_names,experiences,degree_names,hospital_names,distances));
                            doctor_list.setAdapter(doctor_list_adapter);
                            Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.bottom_up);
                            doctor_list.setAnimation(slide_down);

                        }
                        else
                        {
                            if (status.equals("no data found")) {
                                Snackbar.make(rootview, "No doctors available in your location", Snackbar.LENGTH_LONG).show();
                                deptList.clear();
                                doctor_list_adapter.notifyDataSetChanged();
                            }
                        }



                    }
                    catch(Exception e)
                    {

                        e.printStackTrace();
                    }

                }
            }

        });
        ca.execute();
    }
    public String deDup(String s) {
        return new LinkedHashSet<String>(Arrays.asList(s.split(","))).toString().replaceAll("(^\\[|\\]$)", "").replace(", ", "-");
    }

    public  void location_popup() {

        final Dialog dialog = new Dialog(Doctor_List_page.this);
        // Include dialog.xml file
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logcation_page);

        mGoogleApiClient1 = new GoogleApiClient.Builder(Doctor_List_page.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(Doctor_List_page.this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(Doctor_List_page.this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(Doctor_List_page.this)
                .addOnConnectionFailedListener(this)
                .build();

        AutoCompleteTextView mAutocompleteTextView =
                (AutoCompleteTextView) dialog.findViewById(R.id.input_address);
        Button submit = (Button) dialog.findViewById(R.id.submit);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);


        Window window = dialog.getWindow();
        //   window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(
                Color.TRANSPARENT));


        dialog.setCancelable(false);


        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        supportMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        supportMapFragment.getMapAsync(this);

        if (googlemap1 != null)

        {
            Log.e("mapmap","map");
            googlemap1.clear();

            LatLng latLng0 = new LatLng(currentLatitude, currentLongitude);
            googlemap1.moveCamera(CameraUpdateFactory.newLatLng(latLng0));
            googlemap1.animateCamera(CameraUpdateFactory.zoomTo(15f));
            googlemap1.addMarker(new MarkerOptions()
                    .position(latLng0)
                    .title(selected_address)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }


        mAutocompleteTextView.setThreshold(2);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1, BOUNDS_MOUNTAIN_VIEW, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);

        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (actv.getText().toString().equals("")||actv.getText().toString().isEmpty()) {
                    sp = getSharedPreferences(pref, MODE_PRIVATE);


                    lat=Double.longBitsToDouble(sp.getLong("lattitude", Double.doubleToLongBits(0.0)));
                    longg=Double.longBitsToDouble(sp.getLong("longitude", Double.doubleToLongBits(0.0)));
                    location=sp.getString("address","City");

                    Log.e("loc", location+"ccc" + longg);


                    SharedPreferences.Editor edit = sp.edit();
                    edit.putLong("lattitude", Double.doubleToRawLongBits(currentLatitude));
                    edit.putLong("longitude", Double.doubleToRawLongBits(currentLongitude));
                    if (!selected_address.equals(""))
                    {
                        Log.e("aa", selected_address+"ccc");
                        edit.putString("address", selected_address);

                    }
                    else
                    {
                        Log.e("aap", location+"ccc");
                        edit.putString("address", location);
                    }
                    edit.commit();
                    Log.e("selected_address",currentLatitude+""+currentLongitude+"location"+location+"selected_address"+selected_address);
                    JSONObject jsonObject = new JSONObject();

                    try {
                        jsonObject.put("specialisation_id", id);
                        jsonObject.put("latitude", currentLatitude);
                        jsonObject.put("longitude", currentLongitude);
                        Log.e("log_loc", currentLatitude+"Fetching details for ID: 22" + currentLongitude);

                        mobile_verification(jsonObject, Constants.getDoctors);

                        if (doctor_list_adapter!=null)
                        {
                            doctor_list_adapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (supportMapFragment != null) {
                        getFragmentManager().beginTransaction().remove(supportMapFragment).commitAllowingStateLoss();
                        supportMapFragment = null;

                    }


                    dialog.dismiss();
                    googlemap1=null;

                    mGoogleApiClient1.stopAutoManage(Doctor_List_page.this);
                    mGoogleApiClient1.disconnect();


                }
                else
                {
                    Toast.makeText(Doctor_List_page.this, "Select Location ", Toast.LENGTH_LONG).show();

                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (supportMapFragment != null)
                {
                    getFragmentManager().beginTransaction().remove(supportMapFragment).commitAllowingStateLoss();
                    supportMapFragment = null;
                    googlemap1=null;

                }

                dialog.dismiss();

                mGoogleApiClient1.stopAutoManage(Doctor_List_page.this);
                mGoogleApiClient1.disconnect();

            }
        });


        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog0 = new AlertDialog.Builder(this);
            dialog0.setMessage("gps network not enabled");
            dialog0.setPositiveButton("open location settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog0.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });

            dialog0.show();
        }
        //show error dialog if GoolglePlayServices not available

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {

                // Snackbar.make(linearLayout, "Permission already granted.", Snackbar.LENGTH_LONG).show();

            } else {

                //Snackbar.make(linearLayout, "Please request permission.", Snackbar.LENGTH_LONG).show();
                requestPermission();
                //  Snackbar.make(linearLayout, "Permission already granted.", Snackbar.LENGTH_LONG).show();
            }
        }
        dialog.show();

    }


    AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.e("log", "Selected: " + item.description);
            selected_address=String.valueOf(item.description);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient1, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            Log.e("log", "Fetching details for ID: " + item.placeId);


        }
    };

    ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e("log", "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            LatLng latLng=place.getLatLng();
            currentLatitude=latLng.latitude;
            currentLongitude=latLng.longitude;
            Log.e("log_loc", currentLatitude+"Fetching details for ID 11: " + currentLongitude);

            googlemap1.clear();
            LatLng  latLng0 = new LatLng(currentLatitude,currentLongitude);
            googlemap1.moveCamera(CameraUpdateFactory.newLatLng(latLng0));
            googlemap1.animateCamera(CameraUpdateFactory.zoomTo(11.0f));
            googlemap1.addMarker(new MarkerOptions()
                    .position(latLng0)
                    .title(selected_address));
            address.setText(selected_address);



            CharSequence attributions = places.getAttributions();

           /* mNameTextView.setText(Html.fromHtml(place.getName() + ""));
            mAddressTextView.setText(Html.fromHtml(place.getAddress() + ""));
            mIdTextView.setText(Html.fromHtml(place.getId() + ""));
            mPhoneTextView.setText(Html.fromHtml(place.getPhoneNumber() + ""));
            mWebTextView.setText(place.getWebsiteUri() + "");*/
            if (attributions != null) {
                // mAttTextView.setText(Html.fromHtml(attributions.toString()));
            }
        }
    };
    private boolean checkPermission()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }
    }


    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            // Toast.makeText(this, "GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient1);

    }

    @Override
    public void onConnectionSuspended(int i)
    {
        mPlaceArrayAdapter.setGoogleApiClient(null);


    }

    @Override
    public void onLocationChanged(Location location)
    {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        mPlaceArrayAdapter.setGoogleApiClient(null);

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleApiClient.connect();

        if (googlemap1==null)
        {
            googlemap1=googleMap;
            googlemap1.getUiSettings().setMyLocationButtonEnabled(true);
            LatLng latLng=new LatLng(currentLatitude,currentLongitude);
            googlemap1.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googlemap1.animateCamera(CameraUpdateFactory.zoomTo(10));

            googlemap1.clear();
            LatLng  latLng0 = new LatLng(currentLatitude,currentLongitude);
            googlemap1.moveCamera(CameraUpdateFactory.newLatLng(latLng0));
            googlemap1.animateCamera(CameraUpdateFactory.zoomTo(14.0f));
            googlemap1.addMarker(new MarkerOptions()
                    .position(latLng0)
                    .title(selected_address)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        }

        // googlemap1.setMyLocationEnabled(true);



       /* Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        LatLng  latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        googlemap1.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googlemap1.animateCamera(CameraUpdateFactory.zoomTo(10));
*/


        googlemap1.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                //  googlemap1.addMarker(new MarkerOptions().position(latLng));


                Geocoder gcd = new Geocoder(Doctor_List_page.this, Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    Log.e("cityyyy",addresses+"city"+addresses.get(0).getLocality());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //   System.out.println(addresses.get(0).getLocality());

                // Log.e("city",addresses.get(0).getSubLocality()+"city"+addresses.get(0).getLocality());

               /* Intent intent=new Intent(Home_Page.this,Home_Page.class);
                intent.putExtra("area",addresses.get(0).getSubLocality());
                intent.putExtra("city",addresses.get(0).getLocality());
                startActivity(intent);*/


            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();


    }

}
