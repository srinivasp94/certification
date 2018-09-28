package com.tiqs.rapmedix;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.tiqs.rapmedix.adapters.Health_adapater_checkup;
import com.tiqs.rapmedix.adapters.Home_adapter;
import com.tiqs.rapmedix.data_base.DataBase_Helper;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Home_Page extends FragmentActivity implements LocationListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    TabLayout tabLayout, tabLayout1;
    TextView tab11;
    ImageView icon, menu, mainMenu;
    ArrayList<Integer> imag_res = new ArrayList<>();
    ArrayList<String> imag_res1 = new ArrayList<>();
    ArrayList<String> image_title = new ArrayList<>();
    ArrayList<String> Id = new ArrayList<>();
    ArrayList<String> Images = new ArrayList<String>();
    ArrayList<String> Title = new ArrayList<String>();
    ArrayList<String> Description = new ArrayList<String>();
    ArrayList<String> ConsultationId = new ArrayList<String>();
    ArrayList<Integer> Clors = new ArrayList<Integer>();
    ArrayList<Integer> ViewClors = new ArrayList<Integer>();
    ArrayList<Integer> LinearImage = new ArrayList<Integer>();
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<String> deals_off = new ArrayList<>();
    ArrayList<String> deals_img = new ArrayList<>();
    ArrayList<String> deals_description = new ArrayList<>();

    ArrayList<Integer> MenuImages = new ArrayList<Integer>();
    ArrayList<String> MenuName = new ArrayList<String>();

    ArrayList<String> diagonstic_serach = new ArrayList<>();

    int[] drawables;

    DrawerLayout mDrawerLayout;
    RelativeLayout MenuLayout;
    ExpandableHeightGridView1 MenuList;

    GridView gridview;
    Bitmap smallMarker;

    String Image, type, referral_code, mobile_number;

    private AutoCompleteTextView actv, actv2;
    ArrayAdapter<String> adapter;
    GoogleApiClient mGoogleApiClient;
    GoogleApiClient mGoogleApiClient1;
    GoogleMap googlemap1;
    PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(17.3660, 78.4760), new LatLng(17.3660, 78.4760));
    double currentLatitude, currentLongitude;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    SharedPreferences sp;
    SharedPreferences ref_code_sp;
    public static final String pref = "Location";
    public static final String ref_code = "referral";
    double lat, longg;
    String selected_address, CatId = "", Consultation = "", UserId = "", Url = "", xx = "";
    int loginType;
    boolean isNet;
    View view;
    CircleImageView profile_pic;
    ListViewAdapter la;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 125;
    LocationManager manager;
    ImageView deals_image;
    TextView deals_text;
    public final static int REQUEST_CODE = 10101;

    ExpandableHeightGridView1 list1, list_categorie;
    TwoWayView list2;
    String[] sub_cat = {"CATEGORIES", "LABS"};
    ConnectionDetector connectionDetector;
    boolean isnet_available;
    TextView Name, active_tv, pending_tv, completed_tv, text_about, text_terms, text_privacy, text_faqs;
    LinearLayout active_lin, pending_lin, complete_lin;


    ArrayList<Diagnostic_data_model> diagnostic_data_models;

    //ArrayList<com.tiqs.rapmedix.Hcheckup_data_model> hcheckup_data_model;

    int pending = 0, active = 0, completed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.home_page_up_main);

        ConnectionDetector cd = new ConnectionDetector(Home_Page.this);
        isNet = cd.isConnectingToInternet();
//		checkDrawOverlayPermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.v("App", "Build Version Greater than or equal to M: " + Build.VERSION_CODES.M);
            checkDrawOverlayPermission();
        } else {
            Log.v("App", "OS Version Less than M");
            //No need for Permission as less then M OS.
        }

        diagnostic_data_models = new ArrayList<>();
        list1 = (ExpandableHeightGridView1) findViewById(R.id.categories);
        active_lin = (LinearLayout) findViewById(R.id.active_lin);
        pending_lin = (LinearLayout) findViewById(R.id.pending_lin);
        complete_lin = (LinearLayout) findViewById(R.id.complete_lin);


        DataBase_Helper db = new DataBase_Helper(this);
        UserId = db.getUserId("1");

        list2 = (TwoWayView) findViewById(R.id.deals_list);
        deals_image = (ImageView) findViewById(R.id.topic_grid);
        deals_text = (TextView) findViewById(R.id.reviews);
        list2.setItemMargin(10);
        profile_pic = (CircleImageView) findViewById(R.id.prflImg);
        active_tv = (TextView) findViewById(R.id.active_text);
        pending_tv = (TextView) findViewById(R.id.pending_text);
        completed_tv = (TextView) findViewById(R.id.completed_text);
        Name = (TextView) findViewById(R.id.Namee);
        //text_faqs = (TextView) findViewById(R.id.text_faqs);
        text_about = (TextView) findViewById(R.id.text_about);
        text_terms = (TextView) findViewById(R.id.text_terms);
        //text_privacy = (TextView) findViewById(R.id.text_privacy);

        Url = getString(R.string.server) + Constants.category_id;


        text_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_Page.this, AboutMe_Activity.class));
            }
        });
        text_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Home_Page.this, "clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home_Page.this, Terms_And_ConditionsActivity.class));
            }
        });
        Login_page login_page = new Login_page();
        login_page.finish();
        view = findViewById(R.id.hdrawer_layout);
        menu = (ImageView) findViewById(R.id.menu);
        mainMenu = (ImageView) findViewById(R.id.mainmenu);
        MenuLayout = (RelativeLayout) findViewById(R.id.menuLayout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.hdrawer_layout);
        MenuList = (ExpandableHeightGridView1) findViewById(R.id.menuList);

        actv = (AutoCompleteTextView) findViewById(R.id.search_for_doctor);
        adapter = new ArrayAdapter<String>
                (this, R.layout.auto_list, image_title);
        actv.setAdapter(adapter);

        ref_code_sp = getSharedPreferences(ref_code, MODE_PRIVATE);

        sp = getSharedPreferences(pref, MODE_PRIVATE);
        lat = Double.longBitsToDouble(sp.getLong("lattitude", Double.doubleToLongBits(0.0)));
        longg = Double.longBitsToDouble(sp.getLong("longitude", Double.doubleToLongBits(0.0)));
        tabs.clear();


        tabLayout = (TabLayout) findViewById(R.id.cat_tabs);
        tabLayout1 = (TabLayout) findViewById(R.id.cat_tabss);
        Intent intent1 = getIntent();
        Bundle blob = intent1.getExtras();

        ArrayList<String> tabs = new ArrayList<>();
        tabs.clear();
        tabs.add("Doctors");
        tabs.add("Diagnostics");
        tabs.add("Health Checkups");
        tabs.add("Best Quotes");
        tabs.add("Home Visits");

        int[] drawables = {R.drawable.tab_icon_chang, R.drawable.tab_icon_chang_diagnostics, R.drawable.tab_icon_chang_diagnostics, R.drawable.tab_icon_chang_pharmacy, R.drawable.tab_icon_chang_home_visit};
        int[] drawables1 = {R.drawable.subcat_selector, R.drawable.subcat_selector2};
        for (int i = 0; i < tabs.size(); i++) {
            Drawable img1 = getResources().getDrawable(drawables[i]);

            LinearLayout relativeLayout = (LinearLayout)
                    LayoutInflater.from(this).inflate(R.layout.custom_tab, tabLayout, false);

            tab11 = (TextView) relativeLayout.findViewById(R.id.tab_title);
            icon = (ImageView) relativeLayout.findViewById(R.id.tab_icon);


            icon.setImageDrawable(img1);
            tab11.setText(tabs.get(i));

            tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout));

        }
        for (int i = 0; i < sub_cat.length; i++) {
            Drawable img1 = getResources().getDrawable(drawables1[i]);

            LinearLayout relativeLayout = (LinearLayout)
                    LayoutInflater.from(this).inflate(R.layout.custom_tab_sub_cat, tabLayout1, false);

            tab11 = (TextView) relativeLayout.findViewById(R.id.tab_title);
            icon = (ImageView) relativeLayout.findViewById(R.id.tab_icon);


            icon.setImageDrawable(img1);
            tab11.setText(sub_cat[i]);

            tabLayout1.addTab(tabLayout1.newTab().setCustomView(relativeLayout));

        }

        active_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent active_intent = new Intent(Home_Page.this, Appointments.class);
                startActivity(active_intent);
            }
        });
        pending_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pending_intent = new Intent(Home_Page.this, Appointments.class)
                        .putExtra("Pending_Intent", 1);
                //pending_intent.putExtra("Pending_Intent","1");
                startActivity(pending_intent);
            }
        });
        complete_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent complete_intent = new Intent(Home_Page.this, Appointments.class)
                        .putExtra("Pending_Intent", 2);

                //complete_intent.putExtra("Complete_Intent","2");
                startActivity(complete_intent);
            }
        });


        tabLayout1.setVisibility(View.GONE);


        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDrawerLayout.isDrawerOpen(MenuLayout)) {
                    mDrawerLayout.closeDrawer(MenuLayout);
                } else {
                    mDrawerLayout.openDrawer(MenuLayout);
                }

            }
        });
        /*if (isNet) {

			try {

				JSONObject jo = new JSONObject();
				Log.e("deals_1",""+jo);

				today_dealss(jo, getResources().getString(R.string.webData) + Constants.offerDiscount_service);


			}catch (Exception e) {

				e.printStackTrace();
			}
		}

		else {

			Snackbar snackBar = Snackbar.make(view, "No Internent Connection!", Snackbar.LENGTH_INDEFINITE)
					.setAction("RETRY", new View.OnClickListener() {
						@Override
						public void onClick(View view) {

							finish();
							startActivity(getIntent());
						}
					});
			snackBar.setActionTextColor(Color.RED);
			View sbView = snackBar.getView();
			TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
			textView.setTextColor(Color.YELLOW);
			snackBar.show();

		}*/

        if (isNet) {

            try {

                JSONObject jo = new JSONObject();
                jo.put("user_id", UserId);
                //jo.put("name", Name.getText().toString().trim());

                Glide.with(Home_Page.this).
                        load(Image).
                        error(R.drawable.single).
                        //placeholder(R.drawable.single).
                                into(profile_pic);
                Log.e("id", "" + jo.toString());

                profileService(jo, Url);

            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {

            Snackbar snackBar = Snackbar.make(view, "No Internent Connection!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            finish();
                            startActivity(getIntent());
                        }
                    });
            snackBar.setActionTextColor(Color.RED);
            View sbView = snackBar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackBar.show();

        }
        if (isNet) {

            try {

                JSONObject jo = new JSONObject();
                jo.put("user_id", UserId);
                Log.e("id", "" + jo.toString());
                appointment_count(jo, getResources().getString(R.string.server) + Constants.userAppointments_service);


            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {

            Snackbar snackBar = Snackbar.make(view, "No Internent Connection!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            finish();
                            startActivity(getIntent());
                        }
                    });
            snackBar.setActionTextColor(Color.RED);
            View sbView = snackBar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackBar.show();

        }


        Images.clear();
        Title.clear();
        Description.clear();
        Clors.clear();
        ViewClors.clear();
        LinearImage.clear();
        ConsultationId.clear();

        MenuImages.clear();
        MenuName.clear();

        MenuImages.add(R.drawable.profile);
        MenuImages.add(R.drawable.family_member);
        MenuImages.add(R.drawable.prescription);
        MenuImages.add(R.drawable.prescription);
        MenuImages.add(R.drawable.health_records);
        MenuImages.add(R.drawable.invite);
        MenuImages.add(R.drawable.chnage_password);
        MenuImages.add(R.drawable.logout1);

        MenuName.add("Profile");
        MenuName.add("Family Members");
        MenuName.add("Appointments");
        MenuName.add("Prescriptions");
        MenuName.add("Health Records");
        MenuName.add("Invite Friends");
        MenuName.add("Change Password");
        MenuName.add("Logout");


        MenuAdapter ma = new MenuAdapter(Home_Page.this, MenuName, MenuImages);
        MenuList.setAdapter(ma);
        MenuList.setFocusableInTouchMode(false);

        MenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sajhskjh", "kkgasd" + position);
                if (position == 0) {

                    Intent intent = new Intent(Home_Page.this, ProfilePage.class);
                    startActivity(intent);
                } else if (position == 1) {

                    Intent intent = new Intent(Home_Page.this, Add_family_member.class);
                    intent.putExtra("Add_Family", "0");

                    startActivity(intent);

                } else if (position == 2) {

                    Intent intent = new Intent(Home_Page.this, Appointments.class);
                    startActivity(intent);

                } else if (position == 3) {

                    Intent intent = new Intent(Home_Page.this, Prescription.class);
                    intent.putExtra("id", 3);
                    startActivity(intent);

                } else if (position == 4) {

                    Intent intent = new Intent(Home_Page.this, Health_Records.class);
                    intent.putExtra("id", 4);
                    startActivity(intent);

                } else if (position == 5) {

                    Intent intent = new Intent(Home_Page.this, Refer_earn_page.class);
                    startActivity(intent);

                } else if (position == 6) {

                    Intent intent = new Intent(Home_Page.this, Change_password.class);
                    startActivity(intent);

                } else if (position == 7) {

                    DataBase_Helper dataBase_helper = new DataBase_Helper(Home_Page.this);
                    SQLiteDatabase db = dataBase_helper.getWritableDatabase();
                    db.execSQL("delete  from User ");
                    db.close();

                    Intent intent = new Intent(Home_Page.this, First_page.class);
                    startActivity(intent);
                    finish();

                }

            }
        });


        CatId = getString(R.string.server) + Constants.category_id;
        Consultation = getString(R.string.server) + Constants.consulationUrl;


        //new today_deals().execute();
        if (isNet) {

            try {
                new GetSpecifications().execute();

                Log.e("deals_ex", "dddd");

            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {

            Snackbar snackBar = Snackbar.make(view, "No Internent Connection!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            finish();
                            startActivity(getIntent());
                        }
                    });
            snackBar.setActionTextColor(Color.RED);
            View sbView = snackBar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackBar.show();

        }


        Log.e("MemberShipType", "123" + loginType);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    list1.setNumColumns(2);
                    new GetSpecifications().execute();
                    tabLayout1.setVisibility(View.GONE);
                }
                if (tab.getPosition() == 1) {
                    list1.setNumColumns(2);


                    new GetSpecifications_2().execute();
                    tabLayout1.setVisibility(View.VISIBLE);
                    tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {

                            if (tab.getPosition() == 0) {
                                list1.setNumColumns(2);
                                new GetSpecifications_2().execute();
                            } else {

                                if (isNet) {
                                    try {
                                        JSONObject jsonObject = new JSONObject();
                                        jsonObject.put("latitude", lat);
                                        jsonObject.put("longitude", longg);
                                        get_diagnostics(jsonObject, getResources().getString(R.string.webData) + Constants.getDiagnosticsLabs_service);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Snackbar.make(view, "No Internent Connection!", Snackbar.LENGTH_LONG).show();

                                }


                            }


                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                } else if (tab.getPosition() == 3) {

                    list1.setNumColumns(2);
                    new GetSpecifications_3().execute();
                    tabLayout1.setVisibility(View.GONE);


                } else if (tab.getPosition() == 4) {
                    list1.setNumColumns(2);
                    new GetSpecifications_4().execute();
				/*home_popup("Home Visits", "Call us to Book a Home Visit.");*/
                    tabLayout1.setVisibility(View.GONE);

                } else if (tab.getPosition() == 2) {

                    list1.setNumColumns(1);
                    // new GetSpecifications_5().execute();
                    JSONObject jsonObject = new JSONObject();
                    get_healthCheckup_service(jsonObject, getResources().getString(R.string.webData) + Constants.healthCheckup_service);

                    //  list1.setAdapter(null);
                  /*  list1.setNumColumns(1);
                    list1.setAdapter(new Health_adapater_checkup(Home_Page.this,R.layout.health_checkup_item));
               */
                    tabLayout1.setVisibility(View.GONE);

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });


        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) adapterView.getItemAtPosition(i);
                int pos = image_title.indexOf(selected);

                Intent intent = new Intent(Home_Page.this, Doctor_List_page.class);
                intent.putExtra("lat", lat);
                intent.putExtra("longg", longg);
                intent.putExtra("id", Id.get(pos));
                intent.putExtra("location", selected_address);
                Log.e("aa", pos + "   posi  " + lat + "ccc" + Id.get(i));

                startActivity(intent);
                actv.setText("");
            }
        });

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(Home_Page.this, ProfilePage.class);
                startActivity(profileIntent);
            }
        });


		/*list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				if (tabLayout.getSelectedTabPosition()==0)
				{
					Intent intent =new Intent (Home_Page.this,Categories_list_page.class);


					Log.e("aa", lat+"ccc" + longg);

					startActivity(intent);
					actv.setText("");

				}else

				{
					startActivity(new Intent(Home_Page.this,Diagnostic_page.class));
				}
			}
		});*/


        if (lat == 0.0 && longg == 0.0) {
            int perBool1 = ContextCompat.checkSelfPermission(Home_Page.this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (perBool1 == PackageManager.PERMISSION_GRANTED)

            {
                Intent intent = new Intent(this, Map_Loc.class);
                startActivity(intent);

            } else {
                int currentapiVersion = Build.VERSION.SDK_INT;

                if (currentapiVersion >= Build.VERSION_CODES.M) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                            showMessageOKCancel("You need to allow access Location ",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ActivityCompat.requestPermissions(Home_Page.this,
                                                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                                    REQUEST_CODE_ASK_PERMISSIONS);
                                        }
                                    });
                            return;
                        } else {
                            ActivityCompat.requestPermissions(Home_Page.this,
                                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_CODE_ASK_PERMISSIONS);
                        }
                    }


                } else {

                }
            }

        }


    }

    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }


	/*public void slideUpDown(final View view) {

			// Show the panel
			Animation bottomUp = AnimationUtils.loadAnimation(this,
					R.animator.bottom_up);

			startAnimation(bottomUp);


		}*/

    public void home_popup(String Header, String maintext) {
        final Dialog dialog = new Dialog(Home_Page.this);
        // Include dialog.xml file
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.home_popup);
        Window window = dialog.getWindow();
        //   window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        //window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);

        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(
                Color.TRANSPARENT));

        TextView header = (TextView) dialog.findViewById(R.id.header);
        TextView maintextt = (TextView) dialog.findViewById(R.id.main_text);
        Button skip = (Button) dialog.findViewById(R.id.skip);
        Button call = (Button) dialog.findViewById(R.id.call);

        header.setText(Header);
        maintextt.setText(maintext);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                tabLayout.getTabAt(0).select();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.mobile)));
                startActivity(intent);
                tabLayout.getTabAt(0).select();
                dialog.dismiss();


            }
        });

        dialog.show();

    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(Home_Page.this, v);


        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu2, popup.getMenu());


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            private String filterInterval;
            private String filterTitle;

            @Override
            public boolean onMenuItemClick(
                    android.view.MenuItem item) {
                switch (item.getItemId()) {
                /*    case R.id.Profile:
                        return true;

                    case R.id.More:

                        return true;*/
                    case R.id.logout:

                        DataBase_Helper dataBase_helper = new DataBase_Helper(Home_Page.this);
                        SQLiteDatabase db = dataBase_helper.getWritableDatabase();
                        db.execSQL("delete  from User ");
                        db.close();

                        SharedPreferences.Editor edit = sp.edit();
                        edit.putLong("lattitude", Double.doubleToRawLongBits(0.0));
                        edit.putLong("longitude", Double.doubleToRawLongBits(0.0));
                        edit.putString("address", "Hyderabad");
                        edit.commit();

                        Intent intent = new Intent(Home_Page.this, First_page.class);
                        startActivity(intent);
                        finish();
                        return true;
                }
                return false;
            }
        });
        popup.show();
    }

    public void location_popup() {

        final Dialog dialog = new Dialog(Home_Page.this);
        // Include dialog.xml file
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logcation_page_home);

        MapFragment supportMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        supportMapFragment.getMapAsync(this);

        mGoogleApiClient1 = new GoogleApiClient.Builder(Home_Page.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(Home_Page.this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();


        final AutoCompleteTextView mAutocompleteTextView =
                (AutoCompleteTextView) dialog.findViewById(R.id.input_address);
        Button submit = (Button) dialog.findViewById(R.id.submit);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);


        Window window = dialog.getWindow();
        //   window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(
                Color.TRANSPARENT));


        dialog.show();


        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);


        mAutocompleteTextView.setThreshold(2);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1, BOUNDS_MOUNTAIN_VIEW, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);

        // mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor edit = sp.edit();
                edit.putLong("lattitude", Double.doubleToRawLongBits(currentLatitude));
                edit.putLong("longitude", Double.doubleToRawLongBits(currentLongitude));
                edit.putString("address", selected_address);
                edit.commit();
                dialog.dismiss();


            }
        });


    }


    private boolean checkPermission() {
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


    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("App", "OnActivity Result.");
        //check if received result code
        //  is equal our requested code for draw permission
        if (requestCode == REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    //Permission Granted by Overlay!!!
                    //Do your Stuff
//					checkDrawOverlayPermission();
                }
            }
        }
    }


    public void RequestPermission(Activity thisActivity, String Permission, int Code) {
        if (ContextCompat.checkSelfPermission(thisActivity,
                Permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Permission)) {
            } else {
                ActivityCompat.requestPermissions(thisActivity,
                        new String[]{Permission},
                        Code);
            }
        }
    }

    public boolean CheckPermission(Context context, String Permission) {
        if (ContextCompat.checkSelfPermission(context,
                Permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(this, Map_Loc.class);
                    startActivity(intent);
                    // permission was granted, yay! do the
                    // calendar task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("TAG ", "permissions");
            } else{
                checkDrawOverlayPermission();
                //Toast.makeText(this, "Please Grant Permissions other wise app will close.!", Toast.LENGTH_SHORT).show();
            }
            return;


        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(Home_Page.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                //.setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {


    }

    @Override
    public void onConnectionSuspended(int i) {


    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private class GetSpecifications extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Home_Page.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            //pDialog.show();

        }


        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(Constants.getSpecification);

            Log.e("tag", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("0");

                    // looping through All Contacts
                    Id.clear();
                    image_title.clear();
                    imag_res1.clear();
                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id0 = c.getString("id").toString();
                        String name0 = c.getString("specialisation_name").toString();
                        String image_url = c.getString("specialisation_image").toString();

                        Id.add(id0);
                        image_title.add(name0);
                        imag_res1.add(image_url);
                    }

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", "30");

                    //today_dealss(jsonObject,"https://www.rapmedix.com/webservices/offerDiscount_service");
                    Log.e("deals_exten", "dddd");

                } catch (final JSONException e) {
                    Log.e("tag", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Home_Page.this,
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e("tag", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Home_Page.this,
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            list1.setVisibility(View.VISIBLE);
            list1.setAdapter(new cat_grid(Home_Page.this, imag_res1, image_title));

            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    SharedPreferences.Editor editor = ref_code_sp.edit();
                    editor.putString("spec_name", image_title.get(i));
                    editor.commit();
                    Log.e("adddssa", "key" + ref_code_sp.getString("spec_name", "em"));

                    Intent intent = new Intent(Home_Page.this, Doctor_List_page.class);

                    intent.putExtra("lat", lat);
                    intent.putExtra("longg", longg);
                    intent.putExtra("id", Id.get(i));
                    intent.putExtra("location", selected_address);
                    Log.e("aa", lat + "ccc" + longg);

                    startActivity(intent);
                    actv.setText("");

                }

            });
            Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.right_left);
            list1.setAnimation(slide_down);

            //actv.setAdapter(adapter);
        }

    }

    private class GetSpecifications_2 extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Home_Page.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(getResources().getString(R.string.webData) + Constants.getDiagnosticsCategory_service);

            Log.e("tag", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    String image_url = jsonObj.getString("img_url");

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("0");

                    // looping through All Contacts
                    Id.clear();
                    image_title.clear();
                    imag_res1.clear();
                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id0 = c.getString("id").toString();
                        String name0 = c.getString("diagnostics_name").toString();
                        String image_urll = image_url + c.getString("diagnostics_image");

                        Id.add(id0);
                        image_title.add(name0);
                        imag_res1.add(image_urll);

                    }
                } catch (final JSONException e) {
                    Log.e("tag", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Home_Page.this,
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e("tag", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Home_Page.this,
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            list1.setVisibility(View.VISIBLE);
            list1.setAdapter(new cat_grid(Home_Page.this, imag_res1, image_title));

            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {


                    final Dialog dialog = new Dialog(Home_Page.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.requst_callback);
                    Button callBack = (Button) dialog.findViewById(R.id.callback_btn);


                    callBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            try {
                                JSONObject jo = new JSONObject();
                                if (tabLayout.getSelectedTabPosition() == 0) {
                                    type = "0";
                                } else if (tabLayout.getSelectedTabPosition() == 1) {
                                    type = "6";

                                } else if (tabLayout.getSelectedTabPosition() == 2) {
                                    type = "7";

                                } else if (tabLayout.getSelectedTabPosition() == 3) {
                                    type = "3";

                                } else if (tabLayout.getSelectedTabPosition() == 4) {
                                    type = "4";

                                }

                                jo.put("type", type);
                                jo.put("service_id", Id.get(i));
                                jo.put("callbackMobile", new DataBase_Helper(Home_Page.this).getUserMobileNumber("1"));
                                Log.e("callback", "" + jo);
                                //doctorDetails(jo, Url);
                                call_back_service(jo, getResources().getString(R.string.server) + Constants.requestCallback);
                                dialog.dismiss();
                                Toast.makeText(Home_Page.this, "Thankyou for your request we will get back to you", Toast.LENGTH_SHORT).show();
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }


                        }
                    });

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    Window window = dialog.getWindow();
                    lp.copyFrom(window.getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp);
					/*Window window =dialog.getWindow();
					window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,95);*/
                    dialog.show();

					/*Intent intent = new Intent(Home_Page.this, Diagnostic_page.class);

					startActivity(intent);*/
                    //actv.setText("");
                }
            });
            Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.right_left);
            list1.setAnimation(slide_down);


            //	actv.setAdapter(adapter);
        }

    }

    private class GetSpecifications_3 extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Home_Page.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(getResources().getString(R.string.webData) + Constants.getBestQuote_service);

            Log.e("tag", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String image_url = jsonObj.getString("img_url");

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("0");

                    // looping through All Contacts
                    Id.clear();
                    image_title.clear();
                    imag_res1.clear();
                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id0 = c.getString("id").toString();
                        String name0 = c.getString("bestquote_name").toString();
                        String quote_image_url = image_url + c.getString("bestquote_image").toString();

                        Id.add(id0);
                        image_title.add(name0);
                        imag_res1.add(quote_image_url);

                    }
                } catch (final JSONException e) {
                    Log.e("tag", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Home_Page.this,
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e("tag", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Home_Page.this,
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            list1.setVisibility(View.VISIBLE);
            list1.setAdapter(new cat_grid(Home_Page.this, imag_res1, image_title));
            //   actv.setAdapter(adapter);
            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                    final Dialog dialog = new Dialog(Home_Page.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.requst_callback);
                    Button callBack = (Button) dialog.findViewById(R.id.callback_btn);

                    callBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                JSONObject jo = new JSONObject();
                                if (tabLayout.getSelectedTabPosition() == 0) {
                                    type = "0";
                                } else if (tabLayout.getSelectedTabPosition() == 1) {
                                    type = "6";

                                } else if (tabLayout.getSelectedTabPosition() == 2) {
                                    type = "7";

                                } else if (tabLayout.getSelectedTabPosition() == 3) {
                                    type = "3";

                                } else if (tabLayout.getSelectedTabPosition() == 4) {
                                    type = "4";

                                }

                                jo.put("type", type);
                                jo.put("service_id", Id.get(i));
                                jo.put("callbackMobile", new DataBase_Helper(Home_Page.this).getUserMobileNumber("1"));
                                Log.e("callback", "" + jo);
                                //doctorDetails(jo, Url);
                                call_back_service(jo, getResources().getString(R.string.server) + Constants.requestCallback);
                                dialog.dismiss();
                                Toast.makeText(Home_Page.this, "Thankyou for your request we will get back to you", Toast.LENGTH_SHORT).show();
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                    });

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    Window window = dialog.getWindow();
                    lp.copyFrom(window.getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp);
                    dialog.show();


                    //Intent intent = new Intent(Home_Page.this, BestQuote_select_item.class);

                    //startActivity(intent);
                    //actv.setText("");
                }
            });
            Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.right_left);
            list1.setAnimation(slide_down);


        }

    }

    private class GetSpecifications_4 extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Home_Page.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(getResources().getString(R.string.webData) + Constants.getHomeVisit_service);

            Log.e("tag", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String image_url = jsonObj.getString("img_url");

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("0");

                    // looping through All Contacts
                    Id.clear();
                    image_title.clear();
                    imag_res1.clear();
                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id0 = c.getString("id").toString();
                        String name0 = c.getString("homeservice_name").toString();
                        String hvisit_image_url = image_url + c.getString("homeservice_image").toString();

                        Id.add(id0);
                        image_title.add(name0);
                        imag_res1.add(hvisit_image_url);

                    }
                } catch (final JSONException e) {
                    Log.e("tag", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Home_Page.this,
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e("tag", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Home_Page.this,
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            list1.setVisibility(View.VISIBLE);
            list1.setAdapter(new cat_grid(Home_Page.this, imag_res1, image_title));
            //	actv.setAdapter(adapter);
            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                    final Dialog dialog = new Dialog(Home_Page.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.requst_callback);
                    Button callBack = (Button) dialog.findViewById(R.id.callback_btn);

                    callBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                JSONObject jo = new JSONObject();
                                if (tabLayout.getSelectedTabPosition() == 0) {
                                    type = "0";
                                } else if (tabLayout.getSelectedTabPosition() == 1) {
                                    type = "6";

                                } else if (tabLayout.getSelectedTabPosition() == 2) {
                                    type = "7";

                                } else if (tabLayout.getSelectedTabPosition() == 3) {
                                    type = "3";

                                } else if (tabLayout.getSelectedTabPosition() == 4) {
                                    type = "4";

                                }

                                jo.put("type", type);
                                jo.put("service_id", Id.get(i));
                                jo.put("callbackMobile", new DataBase_Helper(Home_Page.this).getUserMobileNumber("1"));
                                Log.e("callback", "" + jo);
                                //doctorDetails(jo, Url);
                                call_back_service(jo, getResources().getString(R.string.server) + Constants.requestCallback);
                                dialog.dismiss();
                                Toast.makeText(Home_Page.this, "Thankyou for your request we will get back to you", Toast.LENGTH_SHORT).show();
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                    });

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    Window window = dialog.getWindow();
                    lp.copyFrom(window.getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp);
					/*Window window =dialog.getWindow();
					window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,95);*/
                    dialog.show();


					/*Intent intent = new Intent(Home_Page.this, HomeVisit_select_item.class);

					startActivity(intent);*/
                    //actv.setText("");
                }
            });
            Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.right_left);
            list1.setAnimation(slide_down);

        }

    }


    public void get_healthCheckup_service(JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(Home_Page.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result.equals("") || result == null) {


                    Snackbar snackBar = Snackbar.make(view, "Please try Again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();

                } else {

                    try {

                        JSONObject jo = new JSONObject(result);

                        String status = jo.getString("status");
                        String image_url = jo.getString("img_url");
                        if (status.equals("success")) {
                            JSONArray jsonArray = jo.getJSONArray("0");
                            diagnostic_data_models.clear();
                            image_title.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Diagnostic_data_model healthCheckup_data_model = new Diagnostic_data_model();
                                healthCheckup_data_model.id = jsonObject.getString("id");
                                healthCheckup_data_model.name = jsonObject.getString("plan_name");
                                healthCheckup_data_model.description = jsonObject.getString("description");
                                healthCheckup_data_model.checkup_image = image_url + jsonObject.getString("plan_image");
                                diagnostic_data_models.add(healthCheckup_data_model);
                                image_title.add(healthCheckup_data_model.name);


                            }
                            list1.setNumColumns(1);
                            Health_checkup_adapater_home health_checkup_adapter_home = new Health_checkup_adapater_home(Home_Page.this, R.layout.health_checkup_item, diagnostic_data_models);
                            list1.setAdapter(health_checkup_adapter_home);
                            //actv.setAdapter(adapter);
                            Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.right_left);
                            list1.setAnimation(slide_down);
                            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                                    final Dialog dialog = new Dialog(Home_Page.this);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setContentView(R.layout.requst_callback);
                                    Button callBack = (Button) dialog.findViewById(R.id.callback_btn);

                                    callBack.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {
                                                JSONObject jo = new JSONObject();
                                                if (tabLayout.getSelectedTabPosition() == 0) {
                                                    type = "0";
                                                } else if (tabLayout.getSelectedTabPosition() == 1) {
                                                    type = "6";

                                                } else if (tabLayout.getSelectedTabPosition() == 2) {
                                                    type = "7";

                                                } else if (tabLayout.getSelectedTabPosition() == 3) {
                                                    type = "3";

                                                } else if (tabLayout.getSelectedTabPosition() == 4) {
                                                    type = "4";

                                                }

                                                jo.put("type", type);
                                                jo.put("service_id", diagnostic_data_models.get(i).id);
                                                jo.put("callbackMobile", new DataBase_Helper(Home_Page.this).getUserMobileNumber("1"));
                                                Log.e("callback", "" + jo);
                                                //doctorDetails(jo, Url);
                                                call_back_service(jo, getResources().getString(R.string.server) + Constants.requestCallback);
                                                dialog.dismiss();
                                                Toast.makeText(Home_Page.this, "Thankyou for your request we will get back to you", Toast.LENGTH_SHORT).show();
                                                return;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                return;
                                            }
                                        }
                                    });

                                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                                    Window window = dialog.getWindow();
                                    lp.copyFrom(window.getAttributes());
                                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                                    window.setAttributes(lp);
					/*Window window =dialog.getWindow();
					window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,95);*/
                                    dialog.show();


					/*Intent intent = new Intent(Home_Page.this, HomeVisit_select_item.class);

					startActivity(intent);*/
                                    //actv.setText("");
                                }
                            });


                        } else {

                            Toast.makeText(Home_Page.this, "" + status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

            }
        });
        ca.execute();

    }

    public void get_diagnostics(JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(Home_Page.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result.equals("") || result == null) {


                    Snackbar snackBar = Snackbar.make(view, "Please try Again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();

                } else {

                    try {

                        JSONObject jo = new JSONObject(result);

                        String status = jo.getString("status");
                        String image_url = jo.getString("img_url");
                        if (status.equals("success")) {
                            JSONArray jsonArray = jo.getJSONArray("0");
                            diagnostic_data_models.clear();
                            image_title.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Diagnostic_data_model diagnostic_data_model = new Diagnostic_data_model();
                                diagnostic_data_model.hname = jsonObject.getString("hospital_name");
                                diagnostic_data_model.category_name = jsonObject.getString("category_names");
                                diagnostic_data_model.image = image_url + jsonObject.getString("profile_pic");
                                diagnostic_data_model.address = jsonObject.getString("location");
                                diagnostic_data_model.distance = jsonObject.getDouble("distance");
                                diagnostic_data_models.add(diagnostic_data_model);
                                image_title.add(diagnostic_data_model.category_name);

                            }
                            list1.setNumColumns(1);
                            list1.setAdapter(new Health_adapater_checkup(Home_Page.this, R.layout.diagnostic_item, diagnostic_data_models));
                            Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.right_left);
                            list1.setAnimation(slide_down);
                            //	actv.setAdapter(adapter);

                        } else {

                            Toast.makeText(Home_Page.this, "" + status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

            }
        });
        ca.execute();
    }

    public void profileService(JSONObject jo, final String url) {

        CustomAsync ca = new CustomAsync(Home_Page.this, jo, url, new OnAsyncCompleteRequest() {

            Intent intent2 = getIntent();
            Bundle b = intent2.getExtras();

            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Snackbar snackBar = Snackbar.make(view, "Please try again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();
                } else {

                    try {

                        JSONObject jo = new JSONObject(result);
                        String status = jo.getString("status");

                        if (status.equals("success")) {

                            Image = jo.getString("profile_pic");
                            Name.setText(jo.getString("name"));

                            SharedPreferences.Editor editor = ref_code_sp.edit();
                            editor.putString("referral_code", jo.getString("referral"));
                            editor.commit();


                            //City.setText(jo.getString("city"));
                            //State.setText(jo.getString("state"));

							/*Glide.with(Home_Page.this).
									load(b.getString("Image")).
									error(R.drawable.single).
									//placeholder(R.drawable.single).
											into(profile_pic);*/
                            Glide.with(Home_Page.this).
                                    load(Image).
                                    error(R.drawable.single).
                                    //placeholder(R.drawable.single).
                                            into(profile_pic);
                            //profileService(jo,url);

                        } else {

                            Toast.makeText(Home_Page.this, "" + status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }


                }

            }
        });
        ca.execute();
    }

    public void appointment_count(JSONObject jo, final String url) {

        CustomAsync ca = new CustomAsync(Home_Page.this, jo, url, new OnAsyncCompleteRequest() {

            Intent intent2 = getIntent();
            Bundle b = intent2.getExtras();

            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Snackbar snackBar = Snackbar.make(view, "Please try again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();
                } else {

                    try {

                        JSONObject jo = new JSONObject(result);
                        String status = jo.getString("status");

                        if (status.equals("success")) {

                            JSONArray jsonArray_active = jo.getJSONArray("0");
                            for (int i = 0; i < jsonArray_active.length(); i++) {
                                JSONObject jsonObject = jsonArray_active.getJSONObject(i);
                                if (jsonObject.getString("status").equals("0")) {
                                    pending++;
                                    pending_tv.setText("" + pending);

                                } else if (jsonObject.getString("status").equals("1")) {
                                    active++;
                                    active_tv.setText("" + active);
                                } else if (jsonObject.getString("status").equals("3")) {
                                    completed++;
                                    completed_tv.setText("" + completed);
                                }
                            }
                        } else {

                            //Toast.makeText(Home_Page.this, ""+status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }


                }

            }
        });
        ca.execute();
    }

    public void call_back_service(JSONObject jo, final String url) {

        CustomAsync ca = new CustomAsync(Home_Page.this, jo, url, new OnAsyncCompleteRequest() {

            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Snackbar snackBar = Snackbar.make(view, "Please try again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();
                } else {

                    try {

                        JSONObject jo = new JSONObject(result);
                        String status = jo.getString("status");

                        if (status.equals("success")) {


                        } else {

                            //Toast.makeText(Home_Page.this, ""+status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }


                }

            }
        });
        ca.execute();
    }


    public void today_dealss(JSONObject jo, final String url) {

        CustomAsync ca = new CustomAsync(Home_Page.this, jo, url, new OnAsyncCompleteRequest() {

            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Snackbar snackBar = Snackbar.make(view, "Please try again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();
                } else {

                    try {

                        JSONObject jo = new JSONObject(result);
                        String status = jo.getString("status");

                        if (status.equals("success")) {
                            String image_url = jo.getString("img_url");
                            JSONArray contacts = jo.getJSONArray("0");

                            for (int i = 0; i < contacts.length(); i++) {

                                JSONObject c = contacts.getJSONObject(i);

                                String name0 = c.getString("title").toString();
                                String image_offer = image_url + c.getString("profile_pic").toString();
                                String discription = c.getString("description").toString();


                                deals_off.add(name0);
                                deals_img.add(image_offer);
                                deals_description.add(discription);
                                Log.e("deals", deals_off.size() + " dd " + deals_img.size() + deals_description.size());

                                list2.setAdapter(new Home_adapter(Home_Page.this, R.layout.deals_item, deals_off, deals_img, deals_description));

                            }
                        } else {

                            //Toast.makeText(Home_Page.this, ""+status, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                        Log.e("today", "" + e);
                    }


                }

            }
        });
        ca.execute();
    }


    public class ListViewAdapter extends BaseAdapter {

        Context con;
        ArrayList<String> Images;
        ArrayList<Integer> Clors;
        ArrayList<Integer> ViewColors;
        ArrayList<Integer> LinearImage;
        ArrayList<String> Title;
        ArrayList<String> Description;
        ArrayList<String> ConsultationId;
        LayoutInflater inflater;

        public ListViewAdapter(Context con, ArrayList<String> Images, ArrayList<String> Title, ArrayList<String> Description, ArrayList<Integer> Clors, ArrayList<Integer> ViewColors, ArrayList<Integer> LinearImage, ArrayList<String> ConsultationId) {

            this.con = con;
            this.Images = Images;
            this.Title = Title;
            this.Description = Description;
            this.Clors = Clors;
            this.ViewColors = ViewColors;
            this.LinearImage = LinearImage;
            this.ConsultationId = ConsultationId;
            inflater = LayoutInflater.from(this.con);

        }

        @Override
        public int getCount() {
            return Images.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder_List vh;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.free_consultent, parent, false);
                vh = new ViewHolder_List();
                vh.textView1 = (TextView) convertView.findViewById(R.id.free_title);
                vh.textView2 = (TextView) convertView.findViewById(R.id.free_desc);
                vh.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                vh.Relative = (RelativeLayout) convertView.findViewById(R.id.relative);
                vh.ImageLayout = (LinearLayout) convertView.findViewById(R.id.image);
                vh.view = (View) convertView.findViewById(R.id.leftView);
                View myView = (View) convertView.findViewById(R.id.view);

                myView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

                convertView.setTag(vh);

            } else {
                vh = (ViewHolder_List) convertView.getTag();

            }

            vh.Relative.setBackgroundColor(Clors.get(position));
            vh.ImageLayout.setBackgroundResource(LinearImage.get(position));
            vh.textView1.setText(Title.get(position));
            vh.textView2.setText(Description.get(position));
            vh.view.setBackgroundColor(ViewColors.get(position));

            Glide.with(con).
                    load(Images.get(position)).
                    error(R.drawable.doctor_white).
                    placeholder(R.drawable.doctor_white).
                    into(vh.imageView);


            return convertView;
        }
    }

    class ViewHolder_List {
        RelativeLayout Relative;
        LinearLayout ImageLayout;
        TextView textView1, textView2;
        ImageView imageView;
        View view;
    }

    public void mainCategory(JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(Home_Page.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result == null || result.equals("")) {

                    Snackbar snackBar = Snackbar.make(view, "Please try Again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();

                } else {

                    try {

                        JSONObject jo = new JSONObject(result);

                        String Status = jo.getString("status");

                        if (Status.equals("success")) {

                            loginType = Integer.parseInt(jo.getString("membership_type"));

                            tabsSize();
                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

            }

        });
        ca.execute();
    }

    public void consultationData(JSONObject jo, String url) {

        CustomAsync ca = new CustomAsync(Home_Page.this, jo, url, new OnAsyncCompleteRequest() {
            @Override
            public void asyncResponse(String result) {

                if (result.equals("") || result == null) {

                    Snackbar snackBar = Snackbar.make(view, "Please try Again!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                    snackBar.setActionTextColor(Color.RED);
                    View sbView = snackBar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackBar.show();
                } else {

                    try {

                        JSONObject jo = new JSONObject(result);

                        String status = jo.getString("status");

                        if (status.equals("success")) {

                            gridview.setVisibility(View.VISIBLE);

                            Images.clear();
                            Title.clear();
                            Description.clear();
                            Clors.clear();
                            ViewClors.clear();
                            LinearImage.clear();
                            ConsultationId.clear();

                            String desc = jo.getString("text");
                            JSONArray ja = jo.getJSONArray("0");

                            int z = 1;

                            for (int i = 0; i < ja.length(); i++) {

                                JSONObject j = ja.getJSONObject(i);

                                String image = j.getString("specialisation_image");
                                String title = j.getString("specialisation_name");
                                String id = j.getString("id");
                                String fimage = getString(R.string.serverHost) + "uploads/specialisation_image/" + image;
                                String desccc = desc;

                                if (z == 1) {
                                    Clors.add(Color.parseColor("#ffd4d4"));
                                    ViewClors.add(Color.parseColor("#ed1c24"));
                                    LinearImage.add(R.drawable.redcercle);
                                } else if (z == 2) {
                                    Clors.add(Color.parseColor("#c8e2ff"));
                                    ViewClors.add(Color.parseColor("#0079ff"));
                                    LinearImage.add(R.drawable.blue);
                                } else if (z == 3) {
                                    Clors.add(Color.parseColor("#ffdfb2"));
                                    ViewClors.add(Color.parseColor("#ff9700"));
                                    LinearImage.add(R.drawable.yellow);
                                } else if (z == 4) {
                                    Clors.add(Color.parseColor("#c7ffac"));
                                    ViewClors.add(Color.parseColor("#4eb61c"));
                                    LinearImage.add(R.drawable.green);
                                }

                                if (z == 4) {
                                    z = 0;
                                }
                                z++;

                                Images.add(fimage);
                                Title.add(title);
                                Description.add(desccc);
                                ConsultationId.add(id);
                            }

                            la = new ListViewAdapter(Home_Page.this, Images, Title, Description, Clors, ViewClors, LinearImage, ConsultationId);
                            gridview.setNumColumns(1);
                            gridview.setAdapter(la);

                        }

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

            }
        });
        ca.execute();
    }

    public void tabsSize() {

        if (loginType == 1) {

            tabs.add("Doctors");
            tabs.add("Diagnostics");
            tabs.add("Pharmacy");
            tabs.add("Home Visits");

            drawables = new int[]{R.drawable.tab_icon_chang, R.drawable.tab_icon_chang_diagnostics, R.drawable.tab_icon_chang_pharmacy, R.drawable.tab_icon_chang_home_visit};

            for (int i = 0; i < tabs.size(); i++) {
                Drawable img1 = getResources().getDrawable(drawables[i]);

                LinearLayout relativeLayout = (LinearLayout)
                        LayoutInflater.from(this).inflate(R.layout.custom_tab, tabLayout, false);

                tab11 = (TextView) relativeLayout.findViewById(R.id.tab_title);
                icon = (ImageView) relativeLayout.findViewById(R.id.tab_icon);

                icon.setImageDrawable(img1);
                tab11.setText(tabs.get(i));
                tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout));

            }

            //new GetSpecifications().execute();
        } else {

            tabs.add("Free Consultations");
            tabs.add("Doctors");
            tabs.add("Diagnostics");
            tabs.add("Pharmacy");
            tabs.add("Home Visits");

            drawables = new int[]{R.drawable.tab_icon_chang, R.drawable.tab_icon_chang, R.drawable.tab_icon_chang_diagnostics, R.drawable.tab_icon_chang_pharmacy, R.drawable.tab_icon_chang_home_visit};

            for (int i = 0; i < tabs.size(); i++) {
                Drawable img1 = getResources().getDrawable(drawables[i]);

                LinearLayout relativeLayout = (LinearLayout)
                        LayoutInflater.from(this).inflate(R.layout.custom_tab, tabLayout, false);

                tab11 = (TextView) relativeLayout.findViewById(R.id.tab_title);
                icon = (ImageView) relativeLayout.findViewById(R.id.tab_icon);

                String textView = tabs.get(i);
                if (textView.length() > 11) {

                    textView = textView.substring(0, 11) + "...";
                }
                icon.setImageDrawable(img1);
                tab11.setText(textView);
                tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout));

            }


            if (isNet) {

                try {

                    JSONObject jo = new JSONObject();
                    jo.put("user_id", UserId);
                    consultationData(jo, Consultation);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            } else {

                Snackbar snackBar = Snackbar.make(view, "No Internent Connection!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                finish();
                                startActivity(getIntent());
                            }
                        });
                snackBar.setActionTextColor(Color.RED);
                View sbView = snackBar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackBar.show();


            }
        }
    }

    public class MenuAdapter extends BaseAdapter {

        ArrayList<String> MenuItems;
        ArrayList<Integer> MenuIcon;
        LayoutInflater inflater;

        public MenuAdapter(Context context, ArrayList<String> MenuItems, ArrayList<Integer> MenuIcon) {
            // TODO Auto-generated constructor stub
            this.MenuItems = MenuItems;
            this.MenuIcon = MenuIcon;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            Log.e("DAtaofArray", "123    " + MenuItems);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return MenuItems.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View viewww, ViewGroup parent) {
            // TODO Auto-generated method stub
            viewww = inflater.inflate(R.layout.menu_row, parent, false);
            TextView tvNameView = (TextView) viewww.findViewById(R.id.row_name);
            ImageView iv = (ImageView) viewww.findViewById(R.id.row_image);
            iv.setImageResource(MenuImages.get(position));
            tvNameView.setText(MenuItems.get(position));
            return viewww;
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

}
