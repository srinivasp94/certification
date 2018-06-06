package com.example.pegasys.pegasyshmis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pegasys.pegasyshmis.adapters.PdfListAdapter;
import com.example.pegasys.pegasyshmis.model.PdfModel;
import com.example.pegasys.pegasyshmis.model.Records;
import com.example.pegasys.pegasyshmis.model.uploadDocModel;
import com.example.pegasys.pegasyshmis.network.APIInterface;
import com.example.pegasys.pegasyshmis.network.APIUtils;
import com.example.pegasys.pegasyshmis.network.Common;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllPdfsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView_pdffiles;
    private TextView txt_noItems;
    private ImageView backbutton;
    private FloatingActionButton buttonAdd;
    private RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
    private PdfListAdapter pdfListAdapter;
    private ArrayList<Records> recordsArrayList = new ArrayList<>();
    private APIInterface mInterface;
    private String uId, file_name;
    private SharedPreferences preferences;
    private static final String PREF_NAME = "mypreferences";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_pdfs);
        progressDialog = new ProgressDialog(this);
        initComponents();
    }

    private void initComponents() {
        setreferences();
        setonclick();
        callPdffilesWS();
    }

    private void callPdffilesWS() {
        progressDialog.show();
        progressDialog.setCancelable(false);
        mInterface = APIUtils.getAPIService();
        preferences = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        uId = preferences.getString("USER_ID", null);

        if (Common.haveInternet(this) == true) {
            mInterface.callviewlistpdfs(uId).enqueue(new Callback<PdfModel>() {
                @Override
                public void onResponse(Call<PdfModel> call, Response<PdfModel> response) {
                    progressDialog.dismiss();
                    PdfModel pdfModel = response.body();
                    if (pdfModel.status == 200) {
                        recordsArrayList = (ArrayList<Records>) pdfModel.records;
                        if (recordsArrayList != null && recordsArrayList.size() > 0) {
                            pdfListAdapter = new PdfListAdapter(ViewAllPdfsActivity.this, recordsArrayList);
                            recyclerView_pdffiles.setAdapter(pdfListAdapter);
                            pdfListAdapter.setOnItemClickListener(new PdfListAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.btn_parse:
                                            file_name = recordsArrayList.get(position).filename;
                                            callPArserWS(file_name);
                                            break;
                                    }
                                }
                            });
//                            setclcikonAdapter();
                        } else {
                            txt_noItems.setVisibility(View.VISIBLE);
                            txt_noItems.setText("NO Items Available");
                        }
                    }
                }

                @Override
                public void onFailure(Call<PdfModel> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ViewAllPdfsActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void callPArserWS(String file_name) {
        progressDialog.show();
        progressDialog.setCancelable(false);
        mInterface.callParserPdfs(uId, file_name).enqueue(new Callback<uploadDocModel>() {
            @Override
            public void onResponse(Call<uploadDocModel> call, Response<uploadDocModel> response) {
                progressDialog.dismiss();
                uploadDocModel uploadDocModel = response.body();
                if (uploadDocModel.status == true) {
                    Toast.makeText(ViewAllPdfsActivity.this, "" + uploadDocModel.pdfcontent, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ViewAllPdfsActivity.this, "" + uploadDocModel.message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<uploadDocModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewAllPdfsActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setreferences() {
        backbutton = (ImageView)findViewById(R.id.img_bak);
        txt_noItems = (TextView) findViewById(R.id.tv_noitems);
        buttonAdd = (FloatingActionButton) findViewById(R.id.floatbtn_add);
        recyclerView_pdffiles = (RecyclerView) findViewById(R.id.rv_listpdfs);
        recyclerView_pdffiles.setLayoutManager(manager);
    }

    private void setonclick() {
        buttonAdd.setOnClickListener(this);
        backbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.floatbtn_add:
                Intent intent = new Intent(ViewAllPdfsActivity.this, UploadPDFActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.img_bak:
                Toast.makeText(this, "Press Again", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Press Again", Toast.LENGTH_SHORT).show();
        finish();
    }
}
