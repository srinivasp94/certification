package com.srinivas.com.distrct.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.srinivas.com.distrct.R;
import com.srinivas.com.distrct.adapters.categoriesAdapter;
import com.srinivas.com.distrct.adapters.mandalAdapter;
import com.srinivas.com.distrct.adapters.subcatAdapter;
import com.srinivas.com.distrct.models.Categories;
import com.srinivas.com.distrct.models.Mandals;
import com.srinivas.com.distrct.models.SubCategoriesModel;
import com.srinivas.com.distrct.models.simpleResponse;
import com.srinivas.com.distrct.network.APIUtils;
import com.srinivas.com.distrct.network.ApiInterface;
import com.srinivas.com.distrct.utils.ConnectionDetector;
import com.srinivas.com.distrct.utils.Validations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private EditText et_title, et_name, et_mobile, et_address;
    private Spinner spinner_mandal, s_categorie, s_subcategorie;
    private Button btn_register;
    private LinearLayout make;
    private String[] categorie_items = {"Select Categorie", "Education", "Hospitality", "RealEstate"};
    private boolean isInternet = false;
    private Validations validations;
    private ConnectionDetector detector;
    private ApiInterface apiInterface;

    private ProgressDialog mprogressDialog;

    private List<Categories> categoriesList = new ArrayList<>();
    private List<SubCategoriesModel> subCategoriesList = new ArrayList<>();
    private List<Mandals> mandalsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        validations = new Validations();
        detector = new ConnectionDetector(this);
        isInternet = detector.isConnectingToInternet();
        apiInterface = APIUtils.getAPIService();
        mprogressDialog = new ProgressDialog(this);


        initComponents();


    }

    private void initComponents() {
        setReferences();
        setbuttononclicklistners();
        calldataformServer();
//        setspinnerOnitemClickListners();

    }


    private void setbuttononclicklistners() {
        btn_register.setOnClickListener(this);
    }

    private void setReferences() {
        et_title = (EditText) findViewById(R.id.edt_titlename);
        et_name = (EditText) findViewById(R.id.edt_name);
        et_mobile = (EditText) findViewById(R.id.edt_phone);
        et_address = (EditText) findViewById(R.id.edt_address);
        btn_register = (Button) findViewById(R.id.btn_register);
        spinner_mandal = (Spinner) findViewById(R.id.spinner_mandal);
        s_categorie = (Spinner) findViewById(R.id.spinnr_categorie);
        s_subcategorie = (Spinner) findViewById(R.id.spinnr_subcategorie);
        make = (LinearLayout) findViewById(R.id.snackabr);
    }

    private void setspinnerOnitemClickListners() {

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesList);
        categoriesAdapter adapter = new categoriesAdapter(RegistrationActivity.this, R.layout.spinner_value_layout, categoriesList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_categorie.setAdapter(adapter);

        s_categorie.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (view.getId()) {
            case R.id.spinner_mandal:

                break;
            case R.id.spinnr_categorie:
//                selectCategorie(i);
                forSubcategoriesWS();
                break;
            case R.id.spinnr_subcategorie:
                break;
        }
    }

    private void selectCategorie(int position) {
       /* if (position == 1) {
            Toast.makeText(this, "Please select Categorie", Toast.LENGTH_SHORT).show();
        } else {*/
/*
            ArrayAdapter<String> adapterSub = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorie_items);
            adapterSub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s_subcategorie.setAdapter(adapterSub);
            s_subcategorie.setOnItemSelectedListener(this);*/

            forSubcategoriesWS();
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                sendRegisterdatatoWS();
                break;
        }
    }

    private void sendRegisterdatatoWS() {
        if (!isInternet) {
            final Snackbar snackbar = Snackbar.make(make, "", 3000).setAction("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        } else {
            if (checkvalidations()) {

                String title = et_title.getText().toString();
                String personName = et_name.getText().toString();
                String mobile = et_mobile.getText().toString();
                String mAddress = et_address.getText().toString();
                apiInterface.registerCall(title, personName, mobile, mAddress, 1, 1, 1, "", 1, 1, "")
                        .enqueue(new Callback<simpleResponse>() {
                            @Override
                            public void onResponse(Call<simpleResponse> call, Response<simpleResponse> response) {
                                mprogressDialog.dismiss();
                                Log.i("Success", response.body().toString());
                            }

                            @Override
                            public void onFailure(Call<simpleResponse> call, Throwable t) {
                                mprogressDialog.dismiss();
                                Log.i("failure", t.toString());
                            }
                        });

            } else {
                Snackbar snackbar = Snackbar.make(make, "All Fields are required", 3000);
                snackbar.show();
            }
        }

    }

    private boolean checkvalidations() {
        boolean ret = true;

        if (!Validations.hasText(et_title)) ret = false;
        if (!Validations.hasText(et_name)) ret = false;
//        if (!Validations.isPhone(et_mobile, true)) ret = false;
        if (!Validations.hasText(et_address)) ret = false;

        return ret;

    }

    private void calldataformServer() {
        if (!isInternet) {
            Snackbar snackbar = Snackbar.make(make, "No Internet Connection", 4000).setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        } else {
            mprogressDialog.show();
            mprogressDialog.setCancelable(false);
            mprogressDialog.setMessage("Please Wait..");
            forCategoriesWS();

            formandalsWS();
        }
    }

    private void forCategoriesWS() {
        apiInterface.getCategories().enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                mprogressDialog.dismiss();
                Log.i("CATEGORIES", response.body().toString());
                categoriesList.add(new Categories(1, "Select Categories"));
                categoriesList = response.body();
                setspinnerOnitemClickListners();
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                mprogressDialog.dismiss();
                Log.i("Fail", "Categories" + t.toString());
            }
        });
    }

    private void forSubcategoriesWS() {
        apiInterface.getSubcategories().enqueue(new Callback<List<SubCategoriesModel>>() {
            @Override
            public void onResponse(Call<List<SubCategoriesModel>> call, Response<List<SubCategoriesModel>> response) {
                mprogressDialog.dismiss();
                Log.i("SUBCATEGORIES", response.body().toString());
                subCategoriesList = response.body();
                subcatAdapter subcatAdapter = new subcatAdapter(RegistrationActivity.this, R.layout.spinner_value_layout, subCategoriesList);
                s_subcategorie.setAdapter(subcatAdapter);
                s_subcategorie.setOnItemSelectedListener(RegistrationActivity.this);
            }

            @Override
            public void onFailure(Call<List<SubCategoriesModel>> call, Throwable t) {
                mprogressDialog.dismiss();
                Log.i("Fail", "subCategories" + t.toString());
            }
        });
    }

    private void formandalsWS() {
        apiInterface.getMandal().enqueue(new Callback<List<Mandals>>() {
            @Override
            public void onResponse(Call<List<Mandals>> call, Response<List<Mandals>> response) {
                mprogressDialog.dismiss();
                Log.i("MANDALS", response.body().toString());
                mandalsList.add(new Mandals(0, "select mandals"));
                mandalsList = response.body();
                mandalAdapter adaptermandal = new mandalAdapter(RegistrationActivity.this, R.layout.spinner_value_layout, mandalsList);
                spinner_mandal.setAdapter(adaptermandal);

                spinner_mandal.setOnItemSelectedListener(RegistrationActivity.this);
            }

            @Override
            public void onFailure(Call<List<Mandals>> call, Throwable t) {
                mprogressDialog.dismiss();
                Log.i("Fail", "subCategories" + t.toString());
            }
        });

    }

}
