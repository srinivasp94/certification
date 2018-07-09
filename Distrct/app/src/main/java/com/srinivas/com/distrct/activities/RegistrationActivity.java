package com.srinivas.com.distrct.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.srinivas.com.distrct.R;
import com.srinivas.com.distrct.adapters.VillageAdapter;
import com.srinivas.com.distrct.adapters.categoriesAdapter;
import com.srinivas.com.distrct.adapters.mandalAdapter;
import com.srinivas.com.distrct.adapters.subcatAdapter;
import com.srinivas.com.distrct.models.Categories;
import com.srinivas.com.distrct.models.Mandals;
import com.srinivas.com.distrct.models.SubCategoriesModel;
import com.srinivas.com.distrct.models.Villages;
import com.srinivas.com.distrct.models.simpleResponse;
import com.srinivas.com.distrct.network.APIUtils;
import com.srinivas.com.distrct.network.ApiInterface;
import com.srinivas.com.distrct.utils.ConnectionDetector;
import com.srinivas.com.distrct.utils.Validations;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_title, et_name, et_mobile, et_address;
    private Spinner spinner_mandal, s_categorie, s_subcategorie, s_village;
    private Button btn_register;
    private ImageView image_back, profile;
    private LinearLayout make;
    private boolean isInternet = false;
    private Validations validations;
    private ConnectionDetector detector;
    private ApiInterface apiInterface;
    private mandalAdapter adaptermandal;
    private subcatAdapter subcatAdapter;
    private categoriesAdapter adapter;

    private int mandalId, cateId, subcatId, villageId;
    private ProgressDialog mprogressDialog;
    private Dialog dialog;

    private List<Categories> categoriesList = new ArrayList<>();
    private List<SubCategoriesModel> subCategoriesList = new ArrayList<>();
    private List<Mandals> mandalsList = new ArrayList<>();
    private List<Villages> villagesList = new ArrayList<>();

    private static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 100;
    private Uri uri;
    InputStream imageStream;
    private String encodedImage;
    private ProgressDialog progressDialog, mprogressDialog1;
    String apiurl = "http://13.59.168.219/api/Master/GetVillages/";
    String url_subcat = "http://13.59.168.219/api/Master/GetSubCategory/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        validations = new Validations();
        detector = new ConnectionDetector(this);
        isInternet = detector.isConnectingToInternet();
        apiInterface = APIUtils.getAPIService();
        mprogressDialog = new ProgressDialog(this);
        dialog = new Dialog(this);

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
        profile.setOnClickListener(this);
        image_back.setOnClickListener(this);
    }

    private void setReferences() {
        et_title = (EditText) findViewById(R.id.edt_titlename);
        et_name = (EditText) findViewById(R.id.edt_name);
        et_mobile = (EditText) findViewById(R.id.edt_phone);
        et_address = (EditText) findViewById(R.id.edt_address);
        btn_register = (Button) findViewById(R.id.btn_register);
        spinner_mandal = (Spinner) findViewById(R.id.spinner_mandal);
        s_village = (Spinner) findViewById(R.id.spinner_village);
        s_categorie = (Spinner) findViewById(R.id.spinnr_categorie);
        s_subcategorie = (Spinner) findViewById(R.id.spinnr_subcategorie);
        make = (LinearLayout) findViewById(R.id.snackabr);
        image_back = (ImageView) findViewById(R.id.imageView);
        profile = (ImageView) findViewById(R.id.img_profile);
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

//        forSubcategoriesWS();
//        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                sendRegisterdatatoWS();
                break;
            case R.id.img_profile:
                selectImage();
                break;
            case R.id.imageView:
                finish();
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
            if (checkvalidations() && profile.getDrawable() != null) {

                String title = et_title.getText().toString();
                String personName = et_name.getText().toString();
                String mobile = et_mobile.getText().toString();
                String mAddress = et_address.getText().toString();
                apiInterface.registerCall(title, personName, mobile, mAddress, cateId, subcatId, mandalId, villageId, "http://13.59.168.219 " + encodedImage, 1, 1, "address")
                        .enqueue(new Callback<simpleResponse>() {
                            @Override
                            public void onResponse(Call<simpleResponse> call, Response<simpleResponse> response) {
                                if (response.body().equals("") && response.body() == null) {
                                    Toast.makeText(RegistrationActivity.this, "Null", Toast.LENGTH_SHORT).show();
                                }
                                mprogressDialog.dismiss();
//                                Log.i("Success", String.valueOf(response.body()));
                                simpleResponse simpleResponse = response.body();
                                if (simpleResponse.status == 1) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
                                    builder.setTitle("Message");
                                    builder.setMessage(simpleResponse.message);
                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialog.dismiss();
                                            finish();
                                        }
                                    });
                                    dialog = builder.create();
                                    dialog.show();

                                } else {
                                    Toast.makeText(RegistrationActivity.this, "" + simpleResponse.message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<simpleResponse> call, Throwable t) {
                                mprogressDialog.dismiss();
                                Log.i("failure", t.toString());
                            }
                        });

            } else {
                Snackbar snackbar = Snackbar.make(make, "All Fields are required or image not selected", 3000);
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
            formandalsWS();
            forCategoriesWS();
//            forSubcategoriesWS();

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
//                setspinnerOnitemClickListners();
                adapter = new categoriesAdapter(RegistrationActivity.this, R.layout.spinner_value_layout, categoriesList);
                s_categorie.setAdapter(adapter);
                s_categorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Categories categories = adapter.getItem(i);
                        cateId = categories.categoryID;
                        Log.i("##Mandals", "" + cateId);
                        new callserviceforsubCats().execute(String.valueOf(cateId));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

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

    private void setspinnerOnitemClickListners() {


        adapter = new categoriesAdapter(RegistrationActivity.this, R.layout.spinner_value_layout, categoriesList);

        s_categorie.setAdapter(adapter);

        s_categorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Categories categories = adapter.getItem(i);
                cateId = categories.categoryID;
                Log.i("##Mandals", "" + cateId);
                new callserviceforsubCats().execute(String.valueOf(cateId));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void forSubcategoriesWS() {
        mprogressDialog.show();
        mprogressDialog.setCancelable(false);
        mprogressDialog.setMessage("Loading");
        apiInterface.getSubcategories().enqueue(new Callback<List<SubCategoriesModel>>() {
            @Override
            public void onResponse(Call<List<SubCategoriesModel>> call, Response<List<SubCategoriesModel>> response) {
                mprogressDialog.dismiss();
                Log.i("SUBCATEGORIES", response.body().toString());
                subCategoriesList = response.body();
                subcatAdapter = new subcatAdapter(RegistrationActivity.this, R.layout.spinner_value_layout, subCategoriesList);
                s_subcategorie.setAdapter(subcatAdapter);
                s_subcategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        SubCategoriesModel model = subcatAdapter.getItem(i);
                        subcatId = model.subCategoryID;
                        Log.i("##Mandals", "" + subcatId);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
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
                adaptermandal = new mandalAdapter(RegistrationActivity.this, R.layout.spinner_value_layout, mandalsList);
                spinner_mandal.setAdapter(adaptermandal);

                spinner_mandal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Mandals mandals = adaptermandal.getItem(i);
                        mandalId = mandals.mandalId;
                        Log.i("##Mandals", "" + mandalId);
                       /* if (mprogressDialog.isShowing()) {
                            mprogressDialog.dismiss();*/
                        new callserviceforVillages().execute(String.valueOf(mandalId));
//                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Mandals>> call, Throwable t) {
                mprogressDialog.dismiss();
                Log.i("Fail", "subCategories" + t.toString());
            }
        });

    }

    private void selectImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
            } else {
                selectfilefromLocal();
            }
        } else {
            selectfilefromLocal();
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = activity.getContentResolver().query(contentURI, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void selectfilefromLocal() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE) {
            selectfilefromLocal();
        } else {
            selectImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            uri = data.getData();

            Uri imageUri = data.getData();

            try {
                imageStream = getContentResolver().openInputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            profile.setImageBitmap(selectedImage);
            encodedImage = encodeImage(selectedImage);


//            String path = FilePath.getPath(this, uri);
//            Toast.makeText(this, "" + uri.getPath().toString(), Toast.LENGTH_SHORT).show();
//            String filePath = getRealPathFromURIPath(uri, this);
//            File file = new File(path);
//            uploadtoServerWS(file);
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        Log.i("Encoded image", encImage);
        return encImage;
    }

    private class callserviceforVillages extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            progressDialog = new ProgressDialog(RegistrationActivity.this);
            progressDialog.setMessage("Loading..Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... strings) {
            // implement API in background and store the response in current variable
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(apiurl + mandalId);

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.print(current);

                    }
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            Log.d("#data", s.toString());
            // dismiss the progress dialog after receiving data from API
            progressDialog.dismiss();
            villagesList.clear();
            try {
                JSONArray jsonArray = new JSONArray(s);
                if (jsonArray.length() > 0 && jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject oneObject = jsonArray.getJSONObject(i);
                        Villages villages = new Villages();
                        villages.villageID = Integer.valueOf(oneObject.getString("VillageID"));
                        villages.villageName = oneObject.getString("VillageName");
                        villagesList.add(villages);
                    }
                    final VillageAdapter villageAdapter = new VillageAdapter(RegistrationActivity.this, R.layout.spinner_value_layout, villagesList);
                    s_village.setAdapter(villageAdapter);
                    s_village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Villages villages = villageAdapter.getItem(i);
                            villageId = villages.villageID;
                            Log.i("##", villages.villageName);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//            super.onPostExecute(s);
        }
    }

    private class callserviceforsubCats extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            mprogressDialog1 = new ProgressDialog(RegistrationActivity.this);
            mprogressDialog1.setMessage("Please Wait");
            mprogressDialog1.setCancelable(false);
            mprogressDialog1.show();


        }

        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(url_subcat + cateId);

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.print(current);

                    }
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            Log.d("data", s.toString());
            // dismiss the progress dialog after receiving data from API
            mprogressDialog1.dismiss();
            subCategoriesList.clear();
            try {
                JSONArray jsonArray = new JSONArray(s);
                if (jsonArray.length() > 0 && jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject oneObject = jsonArray.getJSONObject(i);
                        SubCategoriesModel subcateclass = new SubCategoriesModel();
                        subcateclass.subCategoryID = Integer.valueOf(oneObject.getString("SubCategoryID"));
                        subcateclass.subCategoryName = oneObject.getString("SubCategoryName");
                        subCategoriesList.add(subcateclass);
                    }
                    subcatAdapter = new subcatAdapter(RegistrationActivity.this, R.layout.spinner_value_layout, subCategoriesList);
                    s_subcategorie.setAdapter(subcatAdapter);
                    s_subcategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            SubCategoriesModel model = subcatAdapter.getItem(i);
                            subcatId = model.subCategoryID;
                            Log.i("##Mandals", "" + subcatId);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
