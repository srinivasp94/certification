package com.srinivas.com.distrct;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.srinivas.com.distrct.activities.RegistrationActivity;
import com.srinivas.com.distrct.adapters.SlidingImage_Adapter;
import com.srinivas.com.distrct.adapters.dashboardAdapter;
import com.srinivas.com.distrct.models.Categories;
import com.srinivas.com.distrct.models.DashBoard;
import com.srinivas.com.distrct.models.SubCategoriesModel;
import com.srinivas.com.distrct.network.APIUtils;
import com.srinivas.com.distrct.network.ApiInterface;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private GridView grid_dashboard;
    private dashboardAdapter dashboardAdapter;
    private List<DashBoard> boardList = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ProgressDialog mprogressDialog;
    private ApiInterface apiInterface;
    private List<SubCategoriesModel> subCategoriesList = new ArrayList<>();
    private List<Categories> categoriesList;
    private Dialog dialog;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.bgr_img,R.drawable.schoolbuilding_graphics,R.drawable.finallogo,R.drawable.lead_gen_hard};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mprogressDialog = new ProgressDialog(this);
        dialog = new Dialog(this);
        setSupportActionBar(toolbar);
        apiInterface = APIUtils.getAPIService();
        initcomponents();

    }

    private void initcomponents() {
        setreferences();
        initnavigation();
        init();
        setoncliclks();
        forCategoriesWS();
        mprogressDialog.show();
//        setactions();
    }

    private void initnavigation() {

        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        navigationView.setNavigationItemSelectedListener(this);

        //add this line to display menu1 when the activity is loaded
//        displaySelectedScreen(R.id.nav_education);
    }


    private void setreferences() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        grid_dashboard = (GridView) findViewById(R.id.grid_dashbnoard);
    }

    private void setoncliclks() {
    }

    private void setactions() {
        boardList.add(new DashBoard("Education", Color.parseColor("#FF30034D"), 1));
        boardList.add(new DashBoard("Medical", Color.parseColor("#111111"), 1));
        boardList.add(new DashBoard("Finance", Color.parseColor("#2f7dc7"), 1));
        boardList.add(new DashBoard("Banking", Color.parseColor("#2f7dc7"), 1));
        boardList.add(new DashBoard("Automobile", Color.parseColor("#FF07878E"), 1));
        boardList.add(new DashBoard("ECommerce", Color.parseColor("#FF064F8F"), 1));
        boardList.add(new DashBoard("Business", Color.parseColor("#FF733B07"), 1));
        boardList.add(new DashBoard("MeSeva", Color.parseColor("#FF705F01"), 1));
        boardList.add(new DashBoard("Education", Color.parseColor("#FF196B04"), 1));
        boardList.add(new DashBoard("Education", Color.parseColor("#FF036C6C"), 1));

//        dashboardAdapter = new dashboardAdapter(this, boardList);
       /* grid_dashboard.setAdapter(dashboardAdapter);
        grid_dashboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(DashBoardActivity.this,DisplayActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });*/
    }

    private void forCategoriesWS() {
        apiInterface.getCategories().enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                mprogressDialog.dismiss();
                Log.i("CATEGORIES", response.body().toString());
//                categoriesList.add(new Categories(1, "Select Categories"));
                categoriesList = response.body();
                dashboardAdapter = new dashboardAdapter(DashBoardActivity.this, categoriesList);
                grid_dashboard.setAdapter(dashboardAdapter);
                grid_dashboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        switch (i) {
//                            case i:
                        Intent intent = new Intent(DashBoardActivity.this, DisplayActivity.class);
                        intent.putExtra("POSITION", i);
                        intent.putExtra("Category", categoriesList.get(i).categoryName);
                        startActivity(intent);
//                                break;
//                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                mprogressDialog.dismiss();
                Log.i("Fail", "Categories" + t.toString());
            }
        });
    }


    private void displaySelectedScreen(int itemId) {
        switch (itemId) {
            case R.id.nav_register:
                Intent intent = new Intent(DashBoardActivity.this, RegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_mandal_villages:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Information");
                builder.setMessage(R.string.mandalsandvillages);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
                dialog.setCancelable(true);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      /*  getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;*/
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_navigation, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_register:
                Intent intent = new Intent(DashBoardActivity.this, RegistrationActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        displaySelectedScreen(menuItem.getItemId());
        return true;
    }

    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(DashBoardActivity.this,ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}
