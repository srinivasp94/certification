package com.android.Uga;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.Uga.Pojo.Choose_diet_pojo;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import Adapter.Mydiet_adapter;

import static android.graphics.Color.BLACK;

/**
 * Created by Chapal on 10-Jun-16.
 */

public class MPpiechart extends Activity {


    private RelativeLayout mainLayout;
    private PieChart mChart;
    TextView back_btn;
    TextView creat_dietTV, toggleTV, title_TV, klTV;
    Button addtodiet_btn2;
    Typeface typeface, typeface1, typeface2;
    ToggleButton toggle;
    TextView leftarw, rightarw, leftTV, rightTV,ingrad_libTV,create_mydietTV,nutrient_classTV,are_you,want_to;
    public static int screenWidth;
    static int screenHeight;
    int level;
    ImageView menu_btn, diet_list_btn, nutrient_btn;
    LinearLayout right_moveLL, left_moveLL;
   LinearLayout menuLL,creatdietLL,nutrientLL;
    Button reset_btn;
    // we're going to display pie chart for smartphones martket shares
    public float[] yData ;
    public String[] xData = {"Fat", "Fiber", "Protein","Other"};

    static int current_position = 0;
    LayoutInflater inflater1;
    Dialog dialog1;
    View alert1;
    ArrayList<Choose_diet_pojo> data = AppConstants.list;
    Mydiet_adapter mydietAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_mydiet);

        Point size = new Point();
        Display display = this.getWindowManager().getDefaultDisplay();
        level = (int) this.getResources().getDisplayMetrics().density;
        display.getRealSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        Log.d("screen", size.x + ",,," + size.y);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else {

        }

        if (level > 1.5 && level < 2) {
            screenWidth = (int) (screenWidth / 1.3f);
        }
        if (level < 1.5) {
            screenWidth = (int) (screenWidth / 1.6f);
        }


        typeface = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        typeface1 = Typeface.createFromAsset(getAssets(), "Uga_fonts/Montserrat-Regular.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(), "Uga_fonts/Montserrat_Bold.ttf");

        right_moveLL = (LinearLayout) findViewById(R.id.right_moveLL);
        left_moveLL = (LinearLayout) findViewById(R.id.left_moveLL);

        menuLL = (LinearLayout) findViewById(R.id.menuLL);
        creatdietLL = (LinearLayout) findViewById(R.id.creatdietLL);
        nutrientLL  = (LinearLayout) findViewById(R.id.nutrientLL);

        diet_list_btn = (ImageView) findViewById(R.id.diet_list_btn);
        nutrient_btn = (ImageView) findViewById(R.id.nutrient_btn);

        reset_btn=(Button)findViewById(R.id.reset_btn);
        reset_btn.setTypeface(typeface1);

        back_btn = (TextView) findViewById(R.id.back_btn);
        toggle = (ToggleButton) findViewById(R.id.toggle);
        leftarw = (TextView) findViewById(R.id.leftarw);
        rightarw = (TextView) findViewById(R.id.rightarw);

        leftarw.setTypeface(typeface);
        rightarw.setTypeface(typeface);

        addtodiet_btn2 = (Button) findViewById(R.id.addtodiet_btn2);
        addtodiet_btn2.setTypeface(typeface1);

        creat_dietTV = (TextView) findViewById(R.id.creat_dietTV);
        toggleTV = (TextView) findViewById(R.id.toggleTV);
        title_TV = (TextView) findViewById(R.id.title_TV);
        klTV = (TextView) findViewById(R.id.klTV);
        leftTV = (TextView) findViewById(R.id.leftTV);
        rightTV = (TextView) findViewById(R.id.rightTV);


        ingrad_libTV = (TextView) findViewById(R.id.ingrad_libTV);
        create_mydietTV = (TextView) findViewById(R.id.create_mydietTV);
        nutrient_classTV = (TextView) findViewById(R.id.nutrient_classTV);


        creat_dietTV.setTypeface(typeface1);
        toggleTV.setTypeface(typeface2);
        title_TV.setTypeface(typeface1);
        klTV.setTypeface(typeface1);
        leftTV.setTypeface(typeface1);
        rightTV.setTypeface(typeface1);
        nutrient_classTV.setTypeface(typeface1);
        create_mydietTV.setTypeface(typeface1);
        ingrad_libTV.setTypeface(typeface1);

        leftTV.setTextSize(screenWidth / level / 30);
        rightTV.setTextSize(screenWidth / level / 30);
        leftarw.setTextSize(screenWidth / level / 30);
        rightarw.setTextSize(screenWidth / level / 30);


        back_btn.setTypeface(typeface);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AppConstants.TWO=true;
                Intent intent = new Intent(MPpiechart.this, Choose_diet_activity.class);
                startActivity(intent);
                finish();
            }
        });
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle.isChecked()) {
                    Intent intent = new Intent(MPpiechart.this, Choose_diet_activity.class);
                    startActivity(intent);
                    finish();
                } else {

                }

            }
        });

        addtodiet_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (this) {
                    IngredientSession.addIndex(current_position, 1.0);
                    Layout1.addIngrad();
                    AppConstants.TWO = true;
                    Intent intent = new Intent(MPpiechart.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        menuLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.Four=true;
                Intent intent =new Intent(MPpiechart.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        creatdietLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.TWO=true;
                Intent intent =new Intent(MPpiechart.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nutrientLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.THREE=true;
                Intent intent =new Intent(MPpiechart.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        right_moveLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (current_position >= data.size() - 1) {
                    return;
                }
                current_position += 1;
                screenData();

            }
        });

        left_moveLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_position <= 0) {
                return;
                }
                current_position -= 1;
                screenData();

            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();


            }
        });


        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        mChart = new PieChart(this);
        mChart.setBackgroundColor(

                getResources().getColor(R.color.blue));
        // add pie chart to main layout

        mainLayout.addView(mChart);
        mainLayout.setBackgroundColor(Color.parseColor("#00000000"));
        //mainLayout.setBackgroundResource(R.drawable.circle);
        // configure pie chart
        // mChart.setUsePercentValues(true);
        mChart.setDescription("");

        mChart.setDescriptionColor(R.color.desc);
        mChart.setNoDataTextDescription("");
        mChart.setDrawSliceText(false);


        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        //mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setBackgroundResource(R.drawable.piechart_background);
        // mChart.setBackgroundResource(R.drawable.circle);
        mChart.setDrawHoleEnabled(false);


        // set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(MPpiechart.this,
                        xData[e.getXIndex()] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        // add data
        screenData();
        addData(yData, xData);

        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setXEntrySpace(2);
        l.setYEntrySpace(2);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setTypeface(typeface1);

    }

    private void alertDialog() {
        inflater1=this.getLayoutInflater();
        alert1= inflater1.inflate(R.layout.alert_popup, null);

        Button yes_btn=(Button)alert1.findViewById(R.id.yes_btn);
        Button no_btn =(Button)alert1.findViewById(R.id.no_btn);
        are_you=(TextView)alert1.findViewById(R.id.are_you);
        want_to=(TextView)alert1.findViewById(R.id.want_to);

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
                    Reset_main.resetdata();
                    dialog1.cancel();
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

    // piechart using add data

    private void addData(float[] yData, String[] xData) {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < this.yData.length; i++)
            yVals1.add(new Entry(this.yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

       for (int i = 0; i < this.xData.length; i++)
           xVals.add(this.xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(4);
        dataSet.setValueTypeface(typeface1);

        // add many colors
        final int[] color = {getResources().getColor(R.color.fat),
                getResources().getColor(R.color.fiber), getResources().getColor(R.color.protein), getResources().getColor(R.color.energy)};
        ArrayList<Integer> colors = new ArrayList<Integer>();
        final int[] text_color = {getResources().getColor(R.color.energy), getResources().getColor(R.color.fat), getResources().getColor(R.color.energy), getResources().getColor(R.color.fat)};
        ArrayList<Integer> text_colors = new ArrayList<Integer>();
        /*for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)*/
        //colors.add(c);
        for (int c : color)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        Log.d("xvalues", xVals + "" + dataSet);
        for (int ct : text_color)
            text_colors.add(ct);

        //data.setValueTextColor(Color.GRAY);
        data.setValueTextColors(text_colors);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();

    }


    private void screenData() {

        Log.d("screendata",data+"");
        if (current_position >= data.size() - 1) {
            right_moveLL.setVisibility(View.INVISIBLE);

        }
        else{
            right_moveLL.setVisibility(View.VISIBLE);
            String next= data.get(current_position+1).getMintitle();
            rightTV.setText(next);
        }

        if(current_position<=0){
            left_moveLL.setVisibility(View.INVISIBLE);

        }
        else{
            left_moveLL.setVisibility(View.VISIBLE);
            String next= data.get(current_position-1).getMintitle();
            leftTV.setText(next);
        }



                String title = data.get(current_position).getTitle();
                String energy = String.valueOf(data.get(current_position).getEnergy_weight());
                if(IngredientSession.isLbs==true){
                    energy = String.format("%.2f",data.get(current_position).getEnergy_lbs());
                }
                String fat_text= String.valueOf(data.get(current_position).getFat_weight());
                String fiber_text= String.valueOf(data.get(current_position).getFiber_wieght());
                String protien_text= String.valueOf(data.get(current_position).getProtien_weight());


                 klTV.setText(energy+" kcal");
                title_TV.setText(title);

                float fat=(float)data.get(current_position).getFat_weight();
                float fiber=(float)data.get(current_position).getFiber_wieght();
                float protien=(float)data.get(current_position).getProtien_weight();

        float avp=(float)data.get(current_position).getAvP_wt();
        float ca=(float)data.get(current_position).getCa_wt();
        float lysin=(float)data.get(current_position).getLysine();
        float sodium=(float)data.get(current_position).getSodium_wt();
        float methonine=(float)data.get(current_position).getMethionine_wt();

        float other =avp+ca+lysin+sodium+methonine;

        Log.d("otherdata",avp+","+ca+","+lysin+","+sodium+","+methonine+","+other);

        if(fat==0 && fiber==0 && protien==0 && other==0){
            yData=new float[]{(float) 0.001, (float) 0.001, (float) 0.001,(float) 0.001};
        }else {
            yData = new float[]{fat, fiber, protien,other};
        }
                addData(yData, xData);

    }

    /*@Override
    public void onBackPressed(){
        Intent intent =new Intent(MPpiechart.this,MainActivity.class);
        startActivity(intent);

    }*/

}
