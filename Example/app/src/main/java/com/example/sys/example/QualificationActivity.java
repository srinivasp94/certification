package com.example.sys.example;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sys.example.adapters.ListSpecialAdapter;
import com.example.sys.example.adapters.listSpecialRecycle;
import com.example.sys.example.pojo.DocID;
import com.example.sys.example.pojo.Qualification;
import com.example.sys.example.pojo.QualificationList;
import com.example.sys.example.retrofitnetwork.Common;
import com.example.sys.example.retrofitnetwork.RetrofitApi;
import com.example.sys.example.retrofitnetwork.RetrofitRequester;
import com.example.sys.example.retrofitnetwork.RetrofitResponseListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QualificationActivity extends AppCompatActivity implements RetrofitResponseListener {
    CustomAutoCompleteView c;
    TextView r;
    RecyclerView.LayoutManager manager;
    RelativeLayout layout_Qualification, layout_specialization;
    TextView textView_qualifications, textView_specialization;
    private Object obj;
    ArrayList<QualificationList> list = new ArrayList<>();
    ProgressDialog dialog;

    List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
    Boolean[] booleen;
    private DocID docID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification);
        dialog = new ProgressDialog(this);

        c = (CustomAutoCompleteView) findViewById(R.id.ct);
        r = (TextView) findViewById(R.id.res);

        layout_specialization = (RelativeLayout) findViewById(R.id.rl_specialisation);
        layout_Qualification = (RelativeLayout) findViewById(R.id.rl_qualifaction);

        textView_specialization = (TextView) findViewById(R.id.spinner_specialisation);
        textView_qualifications = (TextView) findViewById(R.id.spinner_qualification);

        manager = new LinearLayoutManager(this);

        layout_specialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(QualificationActivity.this);
                LayoutInflater inflater = QualificationActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alert_list_view, null);
                builder.setView(dialogView);

                RecyclerView mrecyclerView = (RecyclerView)dialogView.findViewById(R.id.rv_recycle);
                ListView mListView = (ListView) dialogView.findViewById(R.id.list_listfview);
                Button mOk = (Button) dialogView.findViewById(R.id.positive_ok);
                Button mCancel = (Button) dialogView.findViewById(R.id.negative_cancel);

                mrecyclerView.setLayoutManager(manager);
                listSpecialRecycle specialRecycle = new listSpecialRecycle(getApplicationContext(),list,booleen);

                mrecyclerView.setAdapter(specialRecycle);
                mListView.setAdapter(new ListSpecialAdapter(getApplicationContext(), list,booleen) );

                final Dialog dialog = builder.create();

                mOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                    }
                });

                builder.show();

            }
        });
        //--------------------------------------------
        //  get_allSpecializations();
        //getservice();
        docID = new DocID("104");
        docID.doctorId = "104";
        try {
//            obj = Class.forName(DocID.class.toString()).cast(docID);
            obj = new Object();
            Log.i("object", obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.show();
        new RetrofitRequester(this).callPostServices(obj, 1, "/webservices/getallspecialisations");
    }

    private void get_allSpecializations() {
        try {
            obj = Class.forName(DocID.class.toString()).cast(docID);
            Log.i("object", obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "/webservices/getallspecialisations");
    }

    private void getservice() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dev.rapmedix.com")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        RetrofitApi api = retrofit.create(RetrofitApi.class);
        Call<Qualification> call = api.getservice(docID);
        final ProgressDialog dialog = new ProgressDialog(QualificationActivity.this);
        dialog.show();
        call.enqueue(new Callback<Qualification>() {
            @Override
            public void onResponse(Call<Qualification> call, Response<Qualification> response) {

                dialog.dismiss();
                Qualification qualification = response.body();
                list = (ArrayList<QualificationList>) qualification.mQualificationList;

//                r.setText(qualification.toString());

                for (int j = 0; j < list.size(); j++) {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    String name = list.get(j).specialisationName;
                    String aId = list.get(j).id;
                    String img = list.get(j).specialisationImage;
                    hm.put("NAME", name);
                    hm.put("ID", aId);
                    hm.put("Img", img);
                    aList.add(hm);
                }
                String[] from = {"NAME", "ID"};

                // Ids of views in listview_layout
                int[] to = {R.id.tv_name, R.id.tv_id};
                SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.item, from, to);
                c.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Qualification> call, Throwable t) {
                Toast.makeText(QualificationActivity.this, "error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }


    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        dialog.dismiss();
        Qualification qualification = Common.getSpecificDataObject(objectResponse, Qualification.class);
        list = (ArrayList<QualificationList>) qualification.mQualificationList;
        booleen = new Boolean[aList.size()];
//        r.setText(qualification.toString());

        for (int j = 0; j < list.size(); j++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            String name = list.get(j).specialisationName;
            String aId = list.get(j).id;
            String img = list.get(j).specialisationImage;
            hm.put("NAME", name);
            hm.put("ID", aId);
            hm.put("Img", img);
            aList.add(hm);
        }
        String[] from = {"NAME", "ID"};

        // Ids of views in listview_layout
        int[] to = {R.id.tv_name, R.id.tv_id};
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.item, from, to);
        c.setAdapter(adapter);
        c.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }
}
