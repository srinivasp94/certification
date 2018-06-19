package com.srinivas.com.distrct;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.srinivas.com.distrct.activities.RegistrationActivity;
import com.srinivas.com.distrct.adapters.dashboardAdapter;
import com.srinivas.com.distrct.models.Categories;
import com.srinivas.com.distrct.models.DashBoard;
import com.srinivas.com.distrct.models.SubCategoriesModel;
import com.srinivas.com.distrct.network.APIUtils;
import com.srinivas.com.distrct.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mprogressDialog = new ProgressDialog(this);
        setSupportActionBar(toolbar);
        apiInterface = APIUtils.getAPIService();
        initcomponents();

    }

    private void initcomponents() {
        setreferences();
        initnavigation();
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
        displaySelectedScreen(R.id.nav_education);
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
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(DashBoardActivity.this, DisplayActivity.class);
                                startActivity(intent);
                                break;
                        }
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
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        displaySelectedScreen(menuItem.getItemId());
        return true;
    }
}
