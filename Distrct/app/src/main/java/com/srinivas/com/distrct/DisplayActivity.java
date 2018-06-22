package com.srinivas.com.distrct;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.srinivas.com.distrct.activities.SingleSchoolActivity;
import com.srinivas.com.distrct.adapters.EducationAdapter;
import com.srinivas.com.distrct.models.educationModels;
import com.srinivas.com.distrct.network.APIUtils;
import com.srinivas.com.distrct.network.ApiInterface;
import com.srinivas.com.distrct.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler_education;
    private EducationAdapter adapter;
    private LinearLayoutManager manager;
    private ArrayList<educationModels> list = new ArrayList<>();
    private ImageView backButon;
    private TextView tv_noItems, tv_title;
    private Intent intent;
    private String categoryName;
    private int id;
    private ApiInterface apiInterface;
    Boolean isInternetAvailable;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ConnectionDetector detector = new ConnectionDetector(this);
        isInternetAvailable = detector.isConnectingToInternet();
        Log.i("Internet Connected ", isInternetAvailable.toString());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..please Wait");
        progressDialog.show();
        progressDialog.setCancelable(false);
        if (isInternetAvailable) {
            apiInterface = APIUtils.getAPIService();
        } else {
            Toast.makeText(this, "No Internet Connections", Toast.LENGTH_LONG).show();
        }

        initComponents();
    }

    private void initComponents() {
        setReferences();
        setOnclicklistners();
        preparedataToview();
    }

    private void setReferences() {
        recycler_education = (RecyclerView) findViewById(R.id.rv_education);
        backButon = (ImageView) findViewById(R.id.imageView);
        tv_noItems = (TextView) findViewById(R.id.txt_noitems);
        tv_title = (TextView) findViewById(R.id.txt_title);


        manager = new LinearLayoutManager(getApplicationContext());
        recycler_education.setLayoutManager(manager);

    }

    private void setOnclicklistners() {
        backButon.setOnClickListener(this);
    }

    private void preparedataToview() {
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            id = bundle.getInt("POSITION");
            categoryName = bundle.getString("Category");
            tv_title.setText(categoryName);
        } else {

        }

        switch (id) {

            case 0:
                apiInterface.getedu().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                            adapter.setonitemClicklistner(new EducationAdapter.OnItemClickListener() {
                                @Override
                                public void onitemClick(View view, int position) {
                                    Intent intent = new Intent(getApplicationContext(), SingleSchoolActivity.class);
                                    intent.putExtra("SchoolName", list.get(position).name);
                                    intent.putExtra("SchoolAddress", list.get(position).address);
                                    intent.putExtra("SchoolPhone", list.get(position).phonenumber);
                                    startActivity(intent);
                                }

                            });

                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 1:
                apiInterface.getentertainment().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 2:
                apiInterface.gettravel().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 3:
                apiInterface.getconstruction().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 4:
                apiInterface.getshopping().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 5:
                apiInterface.gethospitals().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 6:
                apiInterface.getfooddining().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 7:
                apiInterface.getautomobiles().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 8:
                apiInterface.geteletrical().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 9:
                apiInterface.gethomegarden().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 10:
                apiInterface.gethelpline().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 11:
                apiInterface.getservice().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;
            case 12:
                apiInterface.getbeautySpa().enqueue(new Callback<List<educationModels>>() {
                    @Override
                    public void onResponse(Call<List<educationModels>> call, Response<List<educationModels>> response) {
                        progressDialog.dismiss();
                        list.clear();
                        list = (ArrayList<educationModels>) response.body();
                        if (list.size() > 0 && list != null) {
                            adapter = new EducationAdapter(DisplayActivity.this, list);
                            recycler_education.setAdapter(adapter);
                        } else {
                            tv_noItems.setVisibility(View.VISIBLE);
                            recycler_education.setVisibility(View.GONE);
                            tv_noItems.setText("No Items Available for " + categoryName);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<educationModels>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.i("Failure", categoryName + "  and  " + t.toString());
                    }
                });
                break;

        }
        /*adapter = new EducationAdapter(this, list);
        recycler_education.setAdapter(adapter);*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                finish();
                break;
        }
    }
}
