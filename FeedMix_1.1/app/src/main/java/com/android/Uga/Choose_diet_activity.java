package com.android.Uga;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import Adapter.Choose_diet_adapter;
import Adapter.Mydiet_adapter;

import com.android.Uga.Pojo.Choose_diet_pojo;

import java.util.ArrayList;

/**
 * Created by New android on 29-06-2016.
 */
public class Choose_diet_activity extends Activity{

   Button addtodiet_btn;
    TextView back_btn,choosediet_titleTV;
    ImageView diet_list_btn;
    public static Choose_diet_adapter adapter;
    Typeface typeface,typeface1,typeface2;
    TextView list_rowtitleTV,tick_mark,ingrad_libTV,create_mydietTV,nutrient_classTV,toggleTV,are_you,want_to;
    LinearLayout menuLL,createLL,nutrientLL;
    public static ListView lv;
    Button reset_btn;
    LayoutInflater inflater1;
    View alert1;
    Dialog dialog1;
    ToggleButton toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_diet_list);
        addtodiet_btn=(Button)findViewById(R.id.addtodiet_btn);
        typeface=Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");
        typeface1= Typeface.createFromAsset(getAssets(), "Uga_fonts/Montserrat-Regular.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(), "Uga_fonts/Montserrat_Bold.ttf");

        back_btn=(TextView)findViewById(R.id.back_btn);
        toggleTV=(TextView)findViewById(R.id.toggleTV);
        choosediet_titleTV=(TextView)findViewById(R.id.choosediet_titleTV);
        menuLL =(LinearLayout)findViewById(R.id.menuLL);
        createLL =(LinearLayout)findViewById(R.id.createLL);
        nutrientLL =(LinearLayout)findViewById(R.id.nutrientLL);
        reset_btn=(Button)findViewById(R.id.reset_btn);


        ingrad_libTV=(TextView)findViewById(R.id.ingrad_libTV);
        create_mydietTV=(TextView)findViewById(R.id.create_mydietTV);
        nutrient_classTV=(TextView)findViewById(R.id.nutrient_classTV);

        toggle = (ToggleButton) findViewById(R.id.toggle);


        ingrad_libTV.setTypeface(typeface1);
        create_mydietTV.setTypeface(typeface1);
        nutrient_classTV.setTypeface(typeface1);
        reset_btn.setTypeface(typeface1);
        back_btn.setTypeface(typeface);
        choosediet_titleTV.setTypeface(typeface1);
        addtodiet_btn.setTypeface(typeface1);
        toggleTV.setTypeface(typeface2);

         lv =(ListView)findViewById(R.id.diet_list);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.TWO=true;
                Intent intent = new Intent(Choose_diet_activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /*toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle.isChecked()) {
                    Intent intent = new Intent(Choose_diet_activity.this, MPpiechart.class);
                    startActivity(intent);
                    finish();
                } else {

                }

            }
        });*/
        addtodiet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (this) {
                    AppConstants.TWO=true;
                    Layout1.addIngrad();
                    Intent intent = new Intent(Choose_diet_activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        menuLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.Four=true;
                Intent intent = new Intent(Choose_diet_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        createLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.TWO=true;
                Intent intent = new Intent(Choose_diet_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        nutrientLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.THREE=true;
                Intent intent = new Intent(Choose_diet_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
                adapter.notifyDataSetChanged();
            }
        });

        loadInitials();
    }

    private void alertDialog() {
        inflater1=this.getLayoutInflater();
        alert1= inflater1.inflate(R.layout.alert_popup, null);

        Button yes_btn=(Button)alert1.findViewById(R.id.yes_btn);
        Button no_btn =(Button)alert1.findViewById(R.id.no_btn);
        are_you =(TextView)alert1.findViewById(R.id.are_you);
        want_to =(TextView)alert1.findViewById(R.id.want_to);

        yes_btn.setTypeface(typeface1);
        no_btn.setTypeface(typeface1);
        are_you.setTypeface(typeface1);
        want_to.setTypeface(typeface1);

        dialog1 = new Dialog(this);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(false);
        dialog1.setContentView(alert1);
        dialog1.show();
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog1.isShowing()) {
                synchronized (this) {
                    Reset_main.resetdata1(Choose_diet_activity.this);
                    dialog1.cancel();
                    Log.d("applist2","yes");
                }
                }
            }
        });
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog1.isShowing()) {
                    dialog1.cancel();
                }
            }
        });
    }

    public void loadInitials() {


        Log.d("list",AppConstants.list+"");
        adapter=new Choose_diet_adapter(this,AppConstants.list);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

    }

}
