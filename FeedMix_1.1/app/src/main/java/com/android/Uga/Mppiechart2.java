package com.android.Uga;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import Adapter.Choose_diet_adapter;
import Adapter.Mpchart2_adapter;
import Adapter.Mydiet_adapter;

/**
 * Created by Android on 04-07-2016.
 */
public class Mppiechart2 extends Activity {

    ArrayList<Choose_diet_pojo> list=new ArrayList<Choose_diet_pojo>();

        private RelativeLayout mainLayout;
        private PieChart mChart;
        TextView back_btn,summary_titleTV,dietsumaryTV,emailreportTV;
        ImageView toggle;
        Typeface typeface,typeface1,typeface2;
        float fat_final=0,fiber_final=0,protien_final=0,other_final =0;
        ImageView bad_alert;
        TextView alert_TV;
        public float[] yData;
        public String[] xData = {"Fat", "Fiber", "Protein","Other"};
        Mpchart2_adapter adapter;
    Dietresult result;
    ListView lv;
    Button emailreportBtn;
    Double total_quant =0.0,energy_tot=0.0;
    Double  protien_tot=0.0,fat_tot=0.0,fiber_tot=0.0,other_tot=0.0,avp_tot=0.0,lysin_tot=0.0,methonine_tot=0.0,sodium_tot=0.0,ca_tot=0.0;
    TextView protienTV,energyTV,calsiumTV,avpTV,capTV,metTV,lysTV,fiberTV,sodiumTV,titleTV;
    TextView protienQnt,energyQnt,calsiumQnt,avpQnt,capQnt,metQnt,lysQnt,fiberQnt,sodiumQnt;
    TextView energyLVL,calsiumLVL,avpLVL,capLVL,metLVL,lysLVL,fiberLVL,sodiumLVL,protienLVL;
    String chickenselection;
    String units="kg";
    String qunatity ="Quantity:";
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.summary);
            typeface = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
            typeface1= Typeface.createFromAsset(getAssets(), "Uga_fonts/Montserrat-Regular.ttf");
            typeface2=Typeface.createFromAsset(getAssets(),"Uga_fonts/Montserrat_Bold.ttf");

            back_btn = (TextView) findViewById(R.id.back_btn);
            summary_titleTV=(TextView)findViewById(R.id.summary_titleTV);
            dietsumaryTV=(TextView)findViewById(R.id.dietsumaryTV);

            alert_TV = (TextView)findViewById(R.id.alert_TV);
        emailreportBtn=(Button)findViewById(R.id.emailreportBtn);

             bad_alert =(ImageView)findViewById(R.id.bad_alert);
            lv=(ListView)findViewById(R.id.list);

            adapter = new Mpchart2_adapter(this, IngredientSession.selected);
            lv.setAdapter(adapter);

            //toggle=(ImageView)findViewById(R.id.toggle);
            back_btn.setTypeface(typeface);

            summary_titleTV.setTypeface(typeface1);
            dietsumaryTV.setTypeface(typeface1);
        emailreportBtn.setTypeface(typeface1);
        alert_TV.setTypeface(typeface1);

            back_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppConstants.TWO=true;
                    Intent intent = new Intent(Mppiechart2.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });



            mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

            mChart = new PieChart(this);
            // add pie chart to main layout

            mainLayout.addView(mChart);
            mainLayout.setBackgroundColor(Color.parseColor("#00000000"));
            //mainLayout.setBackgroundResource(R.drawable.circle);
            // configure pie chart
            // mChart.setUsePercentValues(true);
            mChart.setDescription("");
            mChart.setDescriptionColor(R.color.desc);
        mChart.setDrawSliceText(false);



            // enable hole and configure
            mChart.setDrawHoleEnabled(true);
            //mChart.setHoleColorTransparent(true);
            mChart.setHoleRadius(7);
            mChart.setTransparentCircleRadius(10);

            // enable rotation of the chart by touch
            mChart.setRotationAngle(0);
            mChart.setRotationEnabled(true);
            mChart.setBackgroundResource(R.drawable.piechart_background2);
            mChart.setDrawHoleEnabled(false);


            // set a chart value selected listener
            mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                @Override
                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                    // display msg when value selected
                    if (e == null)
                        return;

                    Toast.makeText(Mppiechart2.this,
                            xData[e.getXIndex()] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected() {

                }
            });
        emailreportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>–––––––––––––––––––</p>\n<P> SummaryReport </p>\n<p>–––––––––––––––––––</p>\n" + titleTV.getText().toString() + " For " + chickenselection + "<br/>" + protienTV.getText().toString() +
                        protienQnt.getText().toString() + protienLVL.getText().toString() + "<br/>" + energyTV.getText().toString() + energyQnt.getText().toString() + energyLVL.getText().toString()
                        + "<br/>" + calsiumTV.getText().toString() + calsiumQnt.getText().toString() + calsiumLVL.getText().toString() + "<br/>"
                        + avpTV.getText().toString() + avpQnt.getText().toString() + avpLVL.getText().toString() + "<br/>" +
                        capTV.getText().toString() + capQnt.getText().toString() + capLVL.getText().toString() + "<br/>" +
                        metTV.getText().toString() + metQnt.getText().toString() + metLVL.getText().toString() + "<br/>" +
                        lysTV.getText().toString() + lysQnt.getText().toString() + lysLVL.getText().toString() + "<br/>" +
                        fiberTV.getText().toString() + fiberQnt.getText().toString() + fiberLVL.getText().toString() + "<br/>" +
                        sodiumTV.getText().toString() + sodiumQnt.getText().toString() + sodiumLVL.getText().toString() + "<br/>"+qunatity + total_quant+units));

                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        screenData();
            // add data
            addData(yData,xData);

            // customize legends
            Legend l = mChart.getLegend();
            l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
            l.setXEntrySpace(2);
            l.setYEntrySpace(2);
            l.setForm(Legend.LegendForm.CIRCLE);
            loadInitials();
        }

    private void screenData() {

        for(int i : IngredientSession.selectedIndexes)
        {
            total_quant+= AppConstants.list.get(i).getQuntity();

        }
        for(int i: IngredientSession.selectedIndexes){
            Double energy  = AppConstants.list.get(i).getEnergy_weight();
            if(IngredientSession.isLbs==true){
                units ="lbs";
                energy  = AppConstants.list.get(i).getEnergy_lbs();
            }
            Double qunat   = AppConstants.list.get(i).getQuntity();
            Double protien = AppConstants.list.get(i).getProtien_weight();
            Double fat     = AppConstants.list.get(i).getFat_weight();
            Double fiber   = AppConstants.list.get(i).getFiber_wieght();
            Double ca      = AppConstants.list.get(i).getCa_wt();
            Double avp     = AppConstants.list.get(i).getAvP_wt();
            Double lysin   = AppConstants.list.get(i).getLysine();
            Double sodium  = AppConstants.list.get(i).getSodium_wt();
            Double methonine  = AppConstants.list.get(i).getMethionine_wt();

            Double quant_per = (qunat/total_quant)*100;
            energy_tot+= energy*quant_per/100;
            protien_tot+= protien*quant_per/100;
            fat_tot+= fat*quant_per/100;
            fiber_tot+= fiber*quant_per/100;
            avp_tot+=avp*quant_per/100;
            lysin_tot+=lysin*quant_per/100;
            sodium_tot+= sodium*quant_per/100;
            ca_tot+= ca*quant_per/100;
            methonine_tot+= methonine*quant_per/100;
            Log.d("energy_tot",energy_tot+"");

        }
        Log.d("energy_tot11",energy_tot+",protien-"+protien_tot+",fat_tot-"+fat_tot+",fiber_tot-"+fiber_tot+",avp_tot-"+avp_tot+",lysin_tot-"+lysin_tot+",sodium_tot-"+sodium_tot+",ca_tot-"+ca_tot+
        ",methonine_tot-"+methonine_tot+",ca_tot / avp_tot-"+(ca_tot / avp_tot));
        other_tot = avp_tot+methonine_tot+lysin_tot+ca_tot+sodium_tot;

            Float fat_final = Float.parseFloat(String.valueOf(fat_tot));
            Float fiber_final = Float.parseFloat(String.valueOf(fiber_tot));
            Float protien_final = Float.parseFloat(String.valueOf(protien_tot));
            Float other_final = Float.parseFloat(String.valueOf(other_tot));

          //  yData=new float[]{(float) 0.001, (float) 0.001, (float) 0.001,(float) 0.001};

        if(fat_final==0 && fiber_final==0 && protien_final==0 && other_final==0){
            yData=new float[]{(float) 0.001, (float) 0.001, (float) 0.001,(float) 0.001};
        }else {
            yData = new float[]{fat_final, fiber_final, protien_final,other_final};
        }

        addData(yData, xData);
        Log.d("chickenselect",AppConstants.Chickenselection+"");
        if(AppConstants.Chickenselection==1 || AppConstants.Chickenselection==0){
            chickenselection = "Pullet starter";
            result =chickenCalculation_one(protien_tot, fiber_tot, avp_tot, lysin_tot, sodium_tot, ca_tot, methonine_tot, energy_tot);
            Log.d("result11",result+"");
            Log.d("result12",result.protien+","+result.energy+","+result.ca+","+result.avp+","+result.meth+","+result.lys+","+result.sodium+","+result.fiber+","+result.cap);
            if(result.isGood()){
                alert_TV.setText("Good Diet");
                alert_TV.setTextColor(Color.GREEN);
                bad_alert.setImageResource(R.drawable.green_tick);
            }else{
                alert_TV.setText("Not recomended");
//                alert_TV.setTextColor(getColor(R.color.protein));
                bad_alert.setImageResource(R.drawable.bad_alert);
            }
        }else if(AppConstants.Chickenselection==2){
            chickenselection = "Pullet grower";
             result=chickenCalculation_two(protien_tot, fiber_tot, avp_tot, lysin_tot, sodium_tot, ca_tot, methonine_tot, energy_tot);
            if(result.isGood()){
                alert_TV.setText("Good Diet");
                alert_TV.setTextColor(Color.GREEN);
                bad_alert.setImageResource(R.drawable.green_tick);
            }else {
                alert_TV.setText("Not recomended");
                //          alert_TV.setTextColor(getColor(R.color.protein));
                bad_alert.setImageResource(R.drawable.bad_alert);
            }
        }
        else if(AppConstants.Chickenselection==3){
            chickenselection = "Layer";
            result=chickenCalculation_three(protien_tot, fiber_tot, avp_tot, lysin_tot, sodium_tot, ca_tot, methonine_tot, energy_tot);
            if(result.isGood()){
                alert_TV.setText("Good Diet");
                alert_TV.setTextColor(Color.GREEN);
                bad_alert.setImageResource(R.drawable.green_tick);
            }else{
                alert_TV.setText("Not recomended");
      //          alert_TV.setTextColor(getColor(R.color.protein));
                bad_alert.setImageResource(R.drawable.bad_alert);
            }
        }else if(AppConstants.Chickenselection==4){
            chickenselection = "Broiler starter";
             result= chickenCalculation_four(protien_tot, fiber_tot, avp_tot, lysin_tot, sodium_tot, ca_tot, methonine_tot, energy_tot);
            if(result.isGood()){
                alert_TV.setText("Good Diet");
                alert_TV.setTextColor(Color.GREEN);
                bad_alert.setImageResource(R.drawable.green_tick);
            }else{
                alert_TV.setText("Not recomended");
        //        alert_TV.setTextColor(getColor(R.color.protein));
                bad_alert.setImageResource(R.drawable.bad_alert);
            }
        }
        else if(AppConstants.Chickenselection==5){
            chickenselection = "Broiler grower";
            result= chickenCalculation_five(protien_tot, fiber_tot, avp_tot, lysin_tot, sodium_tot, ca_tot, methonine_tot, energy_tot);
            if(result.isGood()){
                alert_TV.setText("Good Diet");
                alert_TV.setTextColor(Color.GREEN);
                bad_alert.setImageResource(R.drawable.green_tick);
            }else{
                alert_TV.setText("Not recomended");
                //        alert_TV.setTextColor(getColor(R.color.protein));
                bad_alert.setImageResource(R.drawable.bad_alert);
            }
        }
        else if(AppConstants.Chickenselection==6){
            chickenselection = "Broiler finisher";
             result= chickenCalculation_six(protien_tot, fiber_tot, avp_tot, lysin_tot, sodium_tot, ca_tot, methonine_tot, energy_tot);
            if(result.isGood()){
                alert_TV.setText("Good Diet");
                alert_TV.setTextColor(Color.GREEN);
                bad_alert.setImageResource(R.drawable.green_tick);
            }else{
                alert_TV.setText("Not recomended");
                //        alert_TV.setTextColor(getColor(R.color.protein));
                bad_alert.setImageResource(R.drawable.bad_alert);
            }
        }
            AlertDialog(protien_tot, fiber_tot, avp_tot, lysin_tot, sodium_tot, ca_tot, methonine_tot, energy_tot,result);
    }

    private void AlertDialog(Double protien_tot, Double fiber_tot, Double avp_tot, Double lysin_tot, Double sodium_tot, Double ca_tot, Double methonine_tot, Double energy_tot, Dietresult d) {

       final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.recomended);
        dialog.setCancelable(true);
        dialog.show();

        protienTV = (TextView)dialog.findViewById(R.id.protienTV);
        protienQnt = (TextView)dialog.findViewById(R.id.protienQnt);
        protienLVL = (TextView)dialog.findViewById(R.id.protienLVL);
         energyTV= (TextView)dialog.findViewById(R.id.energyTV);
         energyQnt = (TextView)dialog.findViewById(R.id.energyQnt);
         energyLVL= (TextView)dialog.findViewById(R.id.energyLVL);
         calsiumTV= (TextView)dialog.findViewById(R.id.calsiumTV);
         calsiumQnt = (TextView)dialog.findViewById(R.id.calsiumQnt);
         calsiumLVL= (TextView)dialog.findViewById(R.id.calsiumLVL);
        avpTV = (TextView)dialog.findViewById(R.id.avpTV);
         avpQnt= (TextView)dialog.findViewById(R.id.avpQnt);
         avpLVL= (TextView)dialog.findViewById(R.id.avpLVL);
         capTV= (TextView)dialog.findViewById(R.id.capTV);
        capQnt= (TextView)dialog.findViewById(R.id.capQnt);
         capLVL= (TextView)dialog.findViewById(R.id.capLVL);
         metTV= (TextView)dialog.findViewById(R.id.metTV);
         metQnt= (TextView)dialog.findViewById(R.id.metQnt);
         metLVL= (TextView)dialog.findViewById(R.id.metLVL);
         lysTV= (TextView)dialog.findViewById(R.id.lysTV);
         lysQnt= (TextView)dialog.findViewById(R.id.lysQnt);
         lysLVL = (TextView)dialog.findViewById(R.id.lysLVL);
        fiberTV= (TextView)dialog.findViewById(R.id.fiberTV);
         fiberQnt= (TextView)dialog.findViewById(R.id.fiberQnt);
         fiberLVL= (TextView)dialog.findViewById(R.id.fiberLVL);
         sodiumTV = (TextView)dialog.findViewById(R.id.sodiumTV);
         sodiumQnt= (TextView)dialog.findViewById(R.id.sodiumQnt);
         sodiumLVL = (TextView)dialog.findViewById(R.id.sodiumLVL);
         titleTV= (TextView)dialog.findViewById(R.id.titleTV);
        TextView okBtn = (TextView)dialog.findViewById(R.id.okBtn);

        protienTV.setTypeface(typeface1);
        protienQnt.setTypeface(typeface1);
        protienLVL.setTypeface(typeface1);
        energyTV.setTypeface(typeface1);
        energyQnt.setTypeface(typeface1);
        energyLVL.setTypeface(typeface1);
        calsiumTV.setTypeface(typeface1);
        calsiumQnt.setTypeface(typeface1);
        calsiumLVL.setTypeface(typeface1);
        capTV.setTypeface(typeface1);
        capQnt.setTypeface(typeface1);
        capLVL.setTypeface(typeface1);
        metTV.setTypeface(typeface1);
        metQnt.setTypeface(typeface1);
        metLVL.setTypeface(typeface1);
        lysTV.setTypeface(typeface1);
        lysQnt.setTypeface(typeface1);
        lysLVL.setTypeface(typeface1);
        fiberTV.setTypeface(typeface1);
        fiberQnt.setTypeface(typeface1);
        fiberLVL.setTypeface(typeface1);
        sodiumTV.setTypeface(typeface1);
        sodiumQnt.setTypeface(typeface1);
        sodiumLVL.setTypeface(typeface1);
        avpTV.setTypeface(typeface1);
        avpQnt.setTypeface(typeface1);
        avpLVL.setTypeface(typeface1);
        titleTV.setTypeface(typeface1);
        okBtn.setTypeface(typeface1);

        Double cap_tot = ca_tot/avp_tot;
        Log.d("cap_tot",cap_tot+"");
        protienQnt.setText(String.format("%.2f", protien_tot)+" %");
        energyQnt.setText(String.format("%.2f", energy_tot)+" kcal");
        calsiumQnt.setText(String.format("%.2f", ca_tot)+" %");
        avpQnt.setText(String.format("%.2f", avp_tot)+" %");
        capQnt.setText(String.format("%.2f", cap_tot)+" %");
        metQnt.setText(String.format("%.2f", methonine_tot)+" %");
        lysQnt.setText(String.format("%.2f", lysin_tot)+" %");
        fiberQnt.setText(String.format("%.2f", fiber_tot)+" %");
        sodiumQnt.setText(String.format("%.2f", sodium_tot)+" %");


        protienLVL.setText(d.protienText());
        energyLVL.setText(d.energyText());
        calsiumLVL.setText(d.caText());
        avpLVL.setText(d.avpText());
        capLVL.setText(d.capText());
        metLVL.setText(d.methText());
        lysLVL.setText(d.lysText());
        fiberLVL.setText(d.fiberText());
        sodiumLVL.setText(d.sodiumText());

        if(d.isGood()){
            titleTV.setText("Recommended");

        }else{
            titleTV.setText("Not recommended");
        }
        if(d.protien==0){
            protienTV.setTextColor(Color.BLACK);
            protienQnt.setTextColor(Color.BLACK);
            protienLVL.setTextColor(Color.BLACK);
        } if(d.energy==0){
            energyTV.setTextColor(Color.BLACK);
            energyQnt.setTextColor(Color.BLACK);
            energyLVL.setTextColor(Color.BLACK);
        }if(d.ca==0){
            calsiumTV.setTextColor(Color.BLACK);
            calsiumQnt.setTextColor(Color.BLACK);
            calsiumLVL.setTextColor(Color.BLACK);
        } if(d.avp==0){
            avpTV.setTextColor(Color.BLACK);
            avpQnt.setTextColor(Color.BLACK);
            avpLVL.setTextColor(Color.BLACK);
        } if(d.cap==0){
            capTV.setTextColor(Color.BLACK);
            capQnt.setTextColor(Color.BLACK);
            capLVL.setTextColor(Color.BLACK);
        } if(d.sodium==0){
            sodiumTV.setTextColor(Color.BLACK);
            sodiumQnt.setTextColor(Color.BLACK);
            sodiumLVL.setTextColor(Color.BLACK);
        }if(d.fiber==0){
            fiberTV.setTextColor(Color.BLACK);
            fiberQnt.setTextColor(Color.BLACK);
            fiberLVL.setTextColor(Color.BLACK);
        } if(d.meth==0){
            metTV.setTextColor(Color.BLACK);
            metQnt.setTextColor(Color.BLACK);
            metLVL.setTextColor(Color.BLACK);
        } if(d.lys==0){
            lysTV.setTextColor(Color.BLACK);
            lysQnt.setTextColor(Color.BLACK);
            lysLVL.setTextColor(Color.BLACK);
        }

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });

    }

    public Dietresult chickenCalculation_one(Double protien_tot, Double fiber_tot, Double avp_tot, Double lysin_tot, Double sodium_tot, Double ca_tot, Double methonine_tot,Double energy_tot) {
        Dietresult diet= new Dietresult();
        if (protien_tot<17 ){
            diet.protien = -1;
            }else if(protien_tot>22){
            diet.protien =1;
        }else{
            diet.protien =0;
        }
        if(IngredientSession.isLbs==true) {
            if (energy_tot<5952) {
                diet.energy=-1;
            }else if( energy_tot>6834){
                diet.energy=1;
            }else{
                diet.energy=0;
            }

        }else {
            if (energy_tot<2700)  {
                diet.energy=-1;
            }else if(energy_tot>3100){
                diet.energy=1;
            }else{
                diet.energy=0;
            }
        }
        if (ca_tot< 0.80) {
            diet.ca=-1;
        }else if( ca_tot>1.20){
            diet.ca=1;
        }else{
            diet.ca=0;
        }
        if (avp_tot<0.35) {
           diet.avp=-1;
        }else if( avp_tot > 0.60){
           diet.avp=1;
        }else{
            diet.avp=0;
        }
        if ((ca_tot / avp_tot)<1.7) {
            diet.cap=-1;
        }else if( (ca_tot / avp_tot) > 2.4){
            diet.cap=1;
        }else{
            diet.cap=0;
        }
        if (methonine_tot <0.40) {
           diet.meth=-1;
        }else {
            diet.meth=0;
        }
        if (lysin_tot < 1) {
            diet.lys=-1;
        }else {
            diet.lys=0;
        }
        if (fiber_tot > 8) {
                diet.fiber=1;
            }else{
            diet.fiber=0;
        }
        if (sodium_tot<0.01) {
                diet.sodium=-1;
            }else if( sodium_tot > 0.4){
            diet.sodium=1;
        }else {
            diet.sodium=0;
        }
            return diet;
    }
    public Dietresult chickenCalculation_two(Double protien_tot, Double fiber_tot, Double avp_tot, Double lysin_tot, Double sodium_tot, Double ca_tot, Double methonine_tot, Double energy_tot) {

        Dietresult diet= new Dietresult();
        if (protien_tot<15 ){
            diet.protien = -1;
        }else if(protien_tot>19){
            diet.protien =1;
        }else{
            diet.protien =0;
        }
        if(IngredientSession.isLbs==true) {
            if (energy_tot<5732) {
                diet.energy=-1;
            }else if( energy_tot>6834){
                diet.energy=1;
            }else{
                diet.energy=0;
            }

        }else {
            if (energy_tot<2600)  {
                diet.energy=-1;
            }else if(energy_tot>3100){
                diet.energy=1;
            }else{
                diet.energy=0;
            }
        }
        if (ca_tot< 0.90) {
            diet.ca=-1;
        }else if( ca_tot>1.10){
            diet.ca=1;
        }else{
            diet.ca=0;
        }
        if (avp_tot<0.30) {
            diet.avp=-1;
        }else if( avp_tot > 0.55){
            diet.avp=1;
        }else{
            diet.avp=0;
        }
        if ((ca_tot / avp_tot)<1.7) {
            diet.cap=-1;
        }else if( (ca_tot / avp_tot) > 2.4){
            diet.cap=1;
        }else{
            diet.cap=0;
        }
        if (methonine_tot <0.35) {
            diet.meth=-1;
        }else {
            diet.meth=0;
        }
        if (lysin_tot < 0.75) {
            diet.lys=-1;
        }else {
            diet.lys=0;
        }
        if (fiber_tot > 10) {
            diet.fiber=1;
        }else{
            diet.fiber=0;
        }
        if (sodium_tot<0.01) {
            diet.sodium=-1;
        }else if( sodium_tot > 0.4){
            diet.sodium=1;
        }else {
            diet.sodium=0;
        }
        return diet;

    }

    public Dietresult chickenCalculation_three(Double energy_tot, Double protien_tot, Double fiber_tot, Double avp_tot, Double lysin_tot, Double sodium_tot, Double ca_tot, Double methonine_tot) {

        Dietresult diet= new Dietresult();
        if (protien_tot<14 ){
            diet.protien = -1;
        }else if(protien_tot>20){
            diet.protien =1;
        }else{
            diet.protien =0;
        }
        if(IngredientSession.isLbs==true) {
            if (energy_tot<5732) {
                diet.energy=-1;
            }else if( energy_tot>6834){
                diet.energy=1;
            }else{
                diet.energy=0;
            }

        }else {
            if (energy_tot<2600)  {
                diet.energy=-1;
            }else if(energy_tot>3100){
                diet.energy=1;
            }else{
                diet.energy=0;
            }
        }
        if (ca_tot< 3.2) {
            diet.ca=-1;
        }else if( ca_tot>4.9){
            diet.ca=1;
        }else{
            diet.ca=0;
        }
        if (avp_tot<0.25) {
            diet.avp=-1;
        }else if( avp_tot > 0.55){
            diet.avp=1;
        }else{
            diet.avp=0;
        }
        if ((ca_tot / avp_tot)<8) {
            diet.cap=-1;
        }else if( (ca_tot / avp_tot) > 17){
            diet.cap=1;
        }else{
            diet.cap=0;
        }
        if (methonine_tot <0.40) {
            diet.meth=-1;
        }else {
            diet.meth=0;
        }
        if (lysin_tot < 0.70) {
            diet.lys=-1;
        }else {
            diet.lys=0;
        }
        if (fiber_tot > 10) {
            diet.fiber=1;
        }else{
            diet.fiber=0;
        }
        if (sodium_tot<0.01) {
            diet.sodium=-1;
        }else if( sodium_tot > 0.4){
            diet.sodium=1;
        }else {
            diet.sodium=0;
        }
        return diet;
    }

    public Dietresult chickenCalculation_four(Double energy_tot, Double protien_tot, Double fiber_tot, Double avp_tot, Double lysin_tot, Double sodium_tot, Double ca_tot, Double methonine_tot) {

        Dietresult diet= new Dietresult();
        if (protien_tot<20 ){
            diet.protien = -1;
        }else if(protien_tot>25){
            diet.protien =1;
        }else{
            diet.protien =0;
        }
        if(IngredientSession.isLbs==true) {
            if (energy_tot<6172) {
                diet.energy=-1;
            }else if( energy_tot>7054){
                diet.energy=1;
            }else{
                diet.energy=0;
            }

        }else {
            if (energy_tot<2800)  {
                diet.energy=-1;
            }else if(energy_tot>3200){
                diet.energy=1;
            }else{
                diet.energy=0;
            }
        }
        if (ca_tot< 0.80) {
            diet.ca=-1;
        }else if( ca_tot>1.10){
            diet.ca=1;
        }else{
            diet.ca=0;
        }
        if (avp_tot<0.2) {
            diet.avp=-1;
        }else if( avp_tot > 0.7){
            diet.avp=1;
        }else{
            diet.avp=0;
        }
        if ((ca_tot / avp_tot)<1.7) {
            diet.cap=-1;
        }else if( (ca_tot / avp_tot) > 2.4){
            diet.cap=1;
        }else{
            diet.cap=0;
        }
        if (methonine_tot <0.37) {
            diet.meth=-1;
        }else {
            diet.meth=0;
        }
        if (lysin_tot < 0.10) {
            diet.lys=-1;
        }else {
            diet.lys=0;
        }
        if (fiber_tot > 5) {
            diet.fiber=1;
        }else{
            diet.fiber=0;
        }
        if (sodium_tot<0.01) {
            diet.sodium=-1;
        }else if( sodium_tot > 0.4){
            diet.sodium=1;
        }else {
            diet.sodium=0;
        }
        return diet;

    }

    public Dietresult chickenCalculation_five(Double protien_tot, Double fiber_tot, Double avp_tot, Double lysin_tot, Double sodium_tot, Double ca_tot, Double methonine_tot, Double energy_tot) {

        Dietresult diet= new Dietresult();
        if (protien_tot<17 ){
            diet.protien = -1;
        }else if(protien_tot>24){
            diet.protien =1;
        }else{
            diet.protien =0;
        }
        if(IngredientSession.isLbs==true) {
            if (energy_tot<6393) {
                diet.energy=-1;
            }else if( energy_tot>7495){
                diet.energy=1;
            }else{
                diet.energy=0;
            }

        }else {
            if (energy_tot<2900)  {
                diet.energy=-1;
            }else if(energy_tot>3400){
                diet.energy=1;
            }else{
                diet.energy=0;
            }
        }
        if (ca_tot< 0.75) {
            diet.ca=-1;
        }else if( ca_tot>1.05){
            diet.ca=1;
        }else{
            diet.ca=0;
        }
        if (avp_tot<0.25) {
            diet.avp=-1;
        }else if( avp_tot > 0.60){
            diet.avp=1;
        }else{
            diet.avp=0;
        }
        if ((ca_tot / avp_tot)<1.7) {
            diet.cap=-1;
        }else if( (ca_tot / avp_tot) > 2.4){
            diet.cap=1;
        }else{
            diet.cap=0;
        }
        if (methonine_tot <0.33) {
            diet.meth=-1;
        }else {
            diet.meth=0;
        }
        if (lysin_tot < 0.85) {
            diet.lys=-1;
        }else {
            diet.lys=0;
        }
        if (fiber_tot > 8) {
            diet.fiber=1;
        }else{
            diet.fiber=0;
        }
        if (sodium_tot<0.01) {
            diet.sodium=-1;
        }else if( sodium_tot > 0.4){
            diet.sodium=1;
        }else {
            diet.sodium=0;
        }
        return diet;
    }

    public Dietresult chickenCalculation_six(Double protien_tot, Double fiber_tot, Double avp_tot, Double lysin_tot, Double sodium_tot, Double ca_tot, Double methonine_tot, Double energy_tot) {

        Dietresult diet= new Dietresult();
        if (protien_tot<16 ){
            diet.protien = -1;
        }else if(protien_tot>23){
            diet.protien =1;
        }else{
            diet.protien =0;
        }
        if(IngredientSession.isLbs==true) {
            if (energy_tot<6613) {
                diet.energy=-1;
            }else if( energy_tot>6834){
                diet.energy=1;
            }else{
                diet.energy=0;
            }

        }else {
            if (energy_tot<3000)  {
                diet.energy=-1;
            }else if(energy_tot>3400){
                diet.energy=1;
            }else{
                diet.energy=0;
            }
        }
        if (ca_tot< 0.75) {
            diet.ca=-1;
        }else if( ca_tot>1.00){
            diet.ca=1;
        }else{
            diet.ca=0;
        }
        if (avp_tot<0.2) {
            diet.avp=-1;
        }else if( avp_tot > 0.6){
            diet.avp=1;
        }else{
            diet.avp=0;
        }
        if ((ca_tot / avp_tot)<1.7) {
            diet.cap=-1;
        }else if( (ca_tot / avp_tot) > 2.4){
            diet.cap=1;
        }else{
            diet.cap=0;
        }
        if (methonine_tot <0.3) {
            diet.meth=-1;
        }else {
            diet.meth=0;
        }
        if (lysin_tot < 0.9) {
            diet.lys=-1;
        }else {
            diet.lys=0;
        }
        if (fiber_tot > 8) {
            diet.fiber=1;
        }else{
            diet.fiber=0;
        }
        if (sodium_tot<0.01) {
            diet.sodium=-1;
        }else if( sodium_tot > 0.4){
            diet.sodium=1;
        }else {
            diet.sodium=0;
        }
        return diet;

    }

    private void addData(float[] yData, String[] xData) {
            ArrayList<Entry> yVals1 = new ArrayList<Entry>();

            for (int i = 0; i < yData.length; i++)
                yVals1.add(new Entry(yData[i], i));


            ArrayList<String> xVals = new ArrayList<String>();

            for (int i = 0; i < xData.length; i++)
                xVals.add(xData[i]);

            // create pie data set
            PieDataSet dataSet = new PieDataSet(yVals1, "");
            dataSet.setSliceSpace(3);
            dataSet.setSelectionShift(5);
            // add many colors
        final int[] color = {getResources().getColor(R.color.fat),
                getResources().getColor(R.color.fiber), getResources().getColor(R.color.protein), getResources().getColor(R.color.energy)};
        ArrayList<Integer> colors = new ArrayList<Integer>();
        final int[] text_color = {getResources().getColor(R.color.energy), getResources().getColor(R.color.fat), getResources().getColor(R.color.energy), getResources().getColor(R.color.fat)};
        ArrayList<Integer> text_colors = new ArrayList<Integer>();

            for (int c :color)
                colors.add(c);

            colors.add(ColorTemplate.getHoloBlue());
            dataSet.setColors(colors);

            // instantiate pie data object now
            PieData data = new PieData(xVals, dataSet);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(11f);
        for (int ct : text_color)
            text_colors.add(ct);
        data.setValueTextColors(text_colors);
            mChart.setData(data);

            // undo all highlights
            mChart.highlightValues(null);

            // update pie chart
            mChart.invalidate();

        }

    private void loadInitials() {

        for(int i : IngredientSession.selectedIndexes)
        {
            if(!IngredientSession.ids.contains(i)){
                IngredientSession.ids.add(i);
                IngredientSession.selected.add(AppConstants.list.get(i));
            }
        }

        Log.d("addingrad", "yes");
        Log.d("size", AppConstants.list.size() + "");
        Log.d("size123",IngredientSession.selected.size()+"");
        adapter.setItems(IngredientSession.selected);


    }

}

