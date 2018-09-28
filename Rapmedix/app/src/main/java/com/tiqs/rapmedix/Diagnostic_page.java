package com.tiqs.rapmedix;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.adapters.Diagnostic_list_adapter;
import com.tiqs.rapmedix.adapters.Health_adapater_checkup;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Diagnostic_page extends AppCompatActivity implements  Categories_list_adapter.setcheckedlistener{
    ListView diagnostic_list;
    LinearLayout submit_select;
    TextView check_count;
    View view;
    String id;
    boolean isNet;
    ArrayAdapter<String> adapter;
    Diagnostic_list_adapter diagnostic_list_adapter;
    private ArrayList<Diagnostic_list_helper> diag_List = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic_page);
        diagnostic_list=(ListView)findViewById(R.id.diagnostic_list);
        check_count = (TextView) findViewById(R.id.select_items);
        Categories_list_adapter categories_list_adapter=new Categories_list_adapter(this,R.layout.categorie_select_item1);
        categories_list_adapter.setOnChecked_listenr(this);
        diagnostic_list.setAdapter(categories_list_adapter);
        Animation bottom_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_up);
        diagnostic_list.setAnimation(bottom_up);
        view = findViewById(R.id.relative);
        ConnectionDetector cd = new ConnectionDetector(Diagnostic_page.this);
        isNet = cd.isConnectingToInternet();

        View view=findViewById(R.id.hdrawer_layout);
        ImageView back_menu = (ImageView) findViewById(R.id.mainmenu);
        back_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submit_select = (LinearLayout) findViewById(R.id.select_submit);
        /*if (isNet)
        {
            try
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("category_id","10");
                Log.e("rec",""+jsonObject.toString());

                Category_list(jsonObject, getResources().getString(R.string.webData) + Constants.getSubservicesByCategoryId_service);

            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.e("Exception","mnmn"+e.toString());
            }
        }else
        {
            Snackbar.make(view, "No Internent Connection!", Snackbar.LENGTH_LONG).show();

        }*/


        submit_select.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent_submit = new Intent(Diagnostic_page.this,Categories_submit_item.class);
                startActivity(intent_submit);
            }
        });

    }

    private  void Category_list(JSONObject jo, String url)
    {
        CustomAsync ca=new CustomAsync(Diagnostic_page.this, jo, url, new OnAsyncCompleteRequest() {

            @Override
            public void asyncResponse(String result) {
                // TODO Auto-generated method stub
                if(result==null||result.equals(""))
                {
                    Toast.makeText(Diagnostic_page.this, "Please Retry", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        JSONObject j = new JSONObject(result);
                        String status = j.getString("status");
                        Log.e("st", "st" + status);

                        if (status.equals("success"))
                        {
                            //doctor_names.clear();

                         //   JSONObject doctors = new JSONObject("0");

                            for (int i = 0; i < j.length()-1; i++) {
                                JSONObject c = j.getJSONObject(String.valueOf(i));

                                String name_diag = c.getString("subservice_name");
                                //String image_diag = c.getString("diagnostics_image");
                                //String spec = deDup(c.getString("description"));
                                //String degree = deDup(c.getString("homevisit_avaliable"));

                                //doctor_names.add(name_diag);
                                Diagnostic_list_helper diagnostic_list_helper = new Diagnostic_list_helper();

                                if (diagnostic_list_helper == null) {
                                    diagnostic_list_helper = new Diagnostic_list_helper();
                                    //diagnostic_list_helper.setImage_url(image_diag);
                                    diagnostic_list_helper.setName(name_diag);
                                    //doctor_list_helper.setDoctor_speciality(spec);
                                    //doctor_list_helper.setDoctor_degree(degree);

                                    //myDepartments.put(i, diagnostic_list_helper);
                                    diag_List.add((i), diagnostic_list_helper);
                                }
                                //    Collections.sort(deptList.get(i).getExperience(),));

                            }



                            diagnostic_list_adapter = new Diagnostic_list_adapter(Diagnostic_page.this, diag_List);
                            // doctor_list.setAdapter(new Doctor_list_adapter(Doctor_List_page.this,profile_pics,names,specialisation_names,experiences,degree_names,hospital_names,distances));
                            diagnostic_list.setAdapter(diagnostic_list_adapter);
                            Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.bottom_up);
                            diagnostic_list.setAnimation(slide_down);

                        }
                        else
                        {
                            if (status.equals("no data found")) {
                                Snackbar.make(view, "No doctors available in your location", Snackbar.LENGTH_LONG).show();
                                diag_List.clear();
                                diagnostic_list_adapter.notifyDataSetChanged();
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

    @Override
    public void onChecked_lsitener(int count)
    {
        check_count.setText(""+count);

    }
}
