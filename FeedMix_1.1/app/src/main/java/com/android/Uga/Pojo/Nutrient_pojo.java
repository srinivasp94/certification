package com.android.Uga.Pojo;

import java.util.ArrayList;

/**
 * Created by New android on 13-07-2016.
 */
public class Nutrient_pojo {
    String title;
    String str1;
    String str2;
    String str3;
    String str4;
    String str5;
    String str6;
    String str7;
    String str8;
    String str9;
    String str_lbs;

    public String getStr_lbs() {
        return str_lbs;
    }

    public void setStr_lbs(String str_lbs) {
        this.str_lbs = str_lbs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public String getStr4() {
        return str4;
    }

    public void setStr4(String str4) {
        this.str4 = str4;
    }

    public String getStr5() {
        return str5;
    }

    public void setStr5(String str5) {
        this.str5 = str5;
    }

    public String getStr6() {
        return str6;
    }

    public void setStr6(String str6) {
        this.str6 = str6;
    }

    public String getStr7() {
        return str7;
    }

    public void setStr7(String str7) {
        this.str7 = str7;
    }

    public String getStr8() {
        return str8;
    }

    public void setStr8(String str8) {
        this.str8 = str8;
    }

    public String getStr9() {
        return str9;
    }

    public void setStr9(String str9) {
        this.str9 = str9;
    }
    //Pullet
    public static ArrayList<Nutrient_pojo>pulletdata(){
        ArrayList<Nutrient_pojo>pullet_list=new ArrayList<Nutrient_pojo>();

        //starter
        Nutrient_pojo starterp= new Nutrient_pojo();

        starterp.setTitle("Starter");
        starterp.setStr1("ø18-21% protein");
        starterp.setStr2("ø2800-3000 kcal/kg");
        //starterp.setStr_lbs("ø6283 kcal/lbs");
        starterp.setStr3("ø0.90-1.10% Ca");
        starterp.setStr4("ø0.40-0.55% AvP");
        starterp.setStr5("øCa:P Ratio = 1.8-2.3");
        starterp.setStr6("øMet = 0.40% minimum");
        starterp.setStr7("øLys = 1% minimum");
        starterp.setStr8("øFiber = 8% maximum");
        starterp.setStr9("øSodium = 0.01-0.3%");
        pullet_list.add(starterp);
        //Grower

        Nutrient_pojo growerp= new Nutrient_pojo();
        growerp.setTitle("Grower");
        growerp.setStr1("ø16-19% protein");
        growerp.setStr2("ø2700-3000 kcal/kg");
       // growerp.setStr_lbs("ø6283 kcal/lbs");
        growerp.setStr3("ø0.85-1.05% Ca");
        growerp.setStr4("ø0.35-0.5% AvP");
        growerp.setStr5("øCa:P Ratio = 1.8-2.3");
        growerp.setStr6("øMet = 0.35% minimum");
        growerp.setStr7("øLys = 0.75% minimum");
        growerp.setStr8("øFiber = 10% maximum");
        growerp.setStr9("øSodium = 0.01-0.3%");
        pullet_list.add(growerp);

        return pullet_list;
    }
    //Layer
    public static ArrayList<Nutrient_pojo>layerdata(){
        ArrayList<Nutrient_pojo>layer_list=new ArrayList<Nutrient_pojo>();

        Nutrient_pojo inproduction= new Nutrient_pojo();
        inproduction.setTitle("In Production");
        inproduction.setStr1("ø15-19% protein");
        inproduction.setStr2("ø2700-3000 kcal/kg");
       // inproduction.setStr_lbs("ø5952-6393 kcal/lbs");
        inproduction.setStr3("ø3.3-4.8% Ca");
        inproduction.setStr4("ø0.30-0.50% AvP");
        inproduction.setStr5("øCa:P Ratio = 9-16");
        inproduction.setStr6("øMet = 0.40% minimum");
        inproduction.setStr7("øLys = 0.70% minimum");
        inproduction.setStr8("øFiber = 10% maximum");
        inproduction.setStr9("øSodium = 0.01-0.3%");
        layer_list.add(inproduction);
        return layer_list;
    }
    //Broiler
    public  static ArrayList<Nutrient_pojo>broilerdata(){
        ArrayList<Nutrient_pojo>broiler_list=new ArrayList<Nutrient_pojo>();
        Nutrient_pojo staterb= new Nutrient_pojo();
        staterb.setTitle("Starter");
        staterb.setStr1("ø21-24% protein");
        staterb.setStr2("ø2900-3100 kcal/kg");
        //staterb.setStr_lbs("ø6724 kcal/lbs");
        staterb.setStr3("ø0.85-1.05% Ca");
        staterb.setStr4("ø0.3-0.6% AvP");
        staterb.setStr5("øCa:P Ratio = 1.8-2.3");
        staterb.setStr6("øMet = 0.37% minimum");
        staterb.setStr7("øLys = 1.10% minimum");
        staterb.setStr8("øFiber = 5% maximum");
        staterb.setStr9("øSodium = 0.01-0.3%");
        broiler_list.add(staterb);

        Nutrient_pojo growerb= new Nutrient_pojo();
        growerb.setTitle("Grower");
        growerb.setStr1("ø18-23% protein");
        growerb.setStr2("ø3000-3300 kcal/kg");
     //   growerb.setStr_lbs("ø6834 kcal/lbs");
        growerb.setStr3("ø0.80-1.00% Ca");
        growerb.setStr4("ø0.3-0.55% AvP");
        growerb.setStr5("øCa:P Ratio = 1.8-2.3");
        growerb.setStr6("øMet = 0.33% minimum");
        growerb.setStr7("øLys = 0.85% minimum");
        growerb.setStr8("øFiber = 8% maximum");
        growerb.setStr9("øSodium = 0.01-0.3%");
        broiler_list.add(growerb);

        Nutrient_pojo finisher= new Nutrient_pojo();
        finisher.setTitle("Finisher");
        finisher.setStr1("ø17-22% protein");
        finisher.setStr2("ø3100-3300 kcal/kg");
      //  finisher.setStr_lbs("ø7054 kcal/lbs");
        finisher.setStr3("ø0.3-0.5% Ca");
        finisher.setStr4("ø0.35% AvP");
        finisher.setStr5("øCa:P Ratio = 1.8-2.3");
        finisher.setStr6("øMet = 0.3% minimum");
        finisher.setStr7("øLys = 0.9% minimum");
        finisher.setStr8("øFiber = 8% maximum");
        finisher.setStr9("øSodium = 0.01-0.3%");
        broiler_list.add(finisher);

        return broiler_list;
    }
}
