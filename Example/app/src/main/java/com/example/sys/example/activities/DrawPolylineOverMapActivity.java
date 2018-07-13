package com.example.sys.example.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sys.example.R;
import com.example.sys.example.pojo.CrimeThematicList;
import com.example.sys.example.pojo.PolyRequest;
import com.example.sys.example.pojo.PolyResponce;
import com.example.sys.example.retrofitnetwork.Common;
import com.example.sys.example.retrofitnetwork.RetrofitRequester;
import com.example.sys.example.retrofitnetwork.RetrofitResponseListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DrawPolylineOverMapActivity extends AppCompatActivity implements RetrofitResponseListener {
    private Object obj;
    ProgressDialog dialog;
    private List<CrimeThematicList> thematicLists = new ArrayList<>();
    private ListView listView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_polyline_over_map);
        dialog = new ProgressDialog(this);
        listView = (ListView) findViewById(R.id.list_boundries);
        dialog.show();
        PolyRequest polyRequest = new PolyRequest();
        polyRequest.clasification = "5";
        polyRequest.percentage = "";
        polyRequest.fromDate = "";
        polyRequest.toDate = "";
        polyRequest.crimeTypeMasterId = "1";
        polyRequest.crimeSubTypeMasterId = "";
        polyRequest.psId = "";
        polyRequest.hierarchyID = "57";
        polyRequest.duraion = "";

        try {

            obj = Class.forName(PolyRequest.class.getName()).cast(polyRequest);
            Log.d("obj", obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "/TSIACAApi/api/DataEntry/CrimeThematicMap");
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null && objectResponse.equals("")) {
            dialog.dismiss();
            Toast.makeText(this, "NULL", Toast.LENGTH_SHORT).show();
        } else {
            dialog.dismiss();

            Log.d("objresponse", objectResponse.toString());
            Gson gson = new Gson();
            PolyResponce polyResponce = Common.getSpecificDataObject(objectResponse, PolyResponce.class);
            Log.d("objresponseMsg", polyResponce.message.toString());
            String msg = polyResponce.message;
//            if (msg.equalsIgnoreCase("OK")) {

            thematicLists = polyResponce.crimeThematicList;
            final ArrayList<String> listitems = new ArrayList<>();
            for (int i = 0; i < thematicLists.size(); i++) {
                listitems.add(thematicLists.get(i).boundary);
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, listitems);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(DrawPolylineOverMapActivity.this,MapsActivity.class);
                    intent.putExtra("Boundries",listitems.get(i));
                    startActivity(intent);
                }
            });
//            }


        }
    }
}
