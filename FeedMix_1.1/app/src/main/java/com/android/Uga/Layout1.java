package com.android.Uga;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.android.Uga.Pojo.Choose_diet_pojo;
import com.android.Uga.Pojo.Nutrient_pojo;

import java.util.ArrayList;

import Adapter.Ingradlib_adapter;
import Adapter.Mydiet_adapter;

public class Layout1 extends Fragment {
    LinearLayout nutrient;
    FrameLayout frameLayout;
    ImageView diet_listbtn,nutrient_btn,menu_btn;
    RelativeLayout layout1,layout2,layout3,ingradlib_Layout;
    LinearLayout addingrad,pulletLL,menuLL,createLL,nutrientLL;
    Typeface typeface1,typeface2;
    TextView ingrad_libTV,create_mydietTV,nutrient_classTV,pullet_tabTV,laying_tabTV,
            boiler_tabTV,maintitle,subtitle,starter_listTV;
    TextView calculatorTV,farmer_guideTV;

    TextView my_dietTV,whet_branTV,add_ingredientTV1,add_ingredientTV2,are_you,want_to;

    public static int screenWidth;
    static int screenHeight;
    ViewGroup.LayoutParams params;
    Button calculate,reset_btn;
    int level;
    boolean isChecked=true;
    boolean ispullet=true;
    boolean islaying=false;
    boolean isbroiler=false;
    static Mydiet_adapter mydietAdapter;

    //pullet
    ArrayList<Nutrient_pojo>pullet_data=Nutrient_pojo.pulletdata();

    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,left_arw,right_arw,right_txt,left_text,text_lb_p;
    LinearLayout left_moveLL,rightmove_LL;
    //laying hen
    ArrayList<Nutrient_pojo>laying_data=Nutrient_pojo.layerdata();

    LinearLayout layingLL,left_moveLL_ly,rightmove_LL_ly;
    TextView maintitle_laying,subtitle_ly,text11,text12,text13,text14,text15,text16,text17,text18,text19,left_arw_ly,
            right_arw_ly,right_txt_ly,left_text_ly,text_lb_l;

    //broilerLL
    ArrayList<Nutrient_pojo>broiler_data=Nutrient_pojo.broilerdata();

    LinearLayout broilerLL,left_moveLL_br,rightmove_LL_br;
    TextView maintitle_broiler,subtitle_br,text21,text22,text23,text24,text25,text26,text27,text28,text29,left_arw_br,
            right_arw_br,right_txt_br,left_text_br,text_lb_b;

    Typeface typeface;

     int currentposition=0;

    Dialog dialog,dialog1,dialog2;
    LayoutInflater inflater,inflater1,inflater2,inflater3;
    View alert,alert3;
    static ListView lv;

    static ArrayList<Integer>displayIndexes=new ArrayList<Integer>();
    private View alert1,alert2;

    public static Ingradlib_adapter ingradlib_adapter;
    public static ArrayList<Choose_diet_pojo>list_pojo = Choose_diet_pojo.feedData();
    ListView ingradlib_list;
    //static int currentindex=0;
    // static int currentid=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_layout1, null);
        //AppConstants.Chickenselection=0;
        Point size= new Point();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        level= (int) getActivity().getResources().getDisplayMetrics().density;
        display.getRealSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        Log.d("screen", size.x + ",,," + size.y);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else {

        }


        if(level>1.5 && level<2){
            screenWidth= (int) (screenWidth);
            //screenHeight= (int) (screenWidth/1.3f);
        }  if(level<1.5){
            screenWidth= (int) (screenWidth/1.6f);
            //screenHeight= (int) (screenWidth/1.6f);
        }


         lv= (ListView)view.findViewById(R.id.addingra_list);
        ingradlib_list=(ListView)view.findViewById(R.id.ingradlib_list);

        mydietAdapter = new Mydiet_adapter(getActivity(), IngredientSession.selected);
        lv.setAdapter(mydietAdapter);
        frameLayout=(FrameLayout)view.findViewById(R.id.activity_main_content_fragment);

        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");
        typeface1=Typeface.createFromAsset(getActivity().getAssets(),"Uga_fonts/Montserrat-Regular.ttf");
        typeface2=Typeface.createFromAsset(getActivity().getAssets(),"Uga_fonts/Montserrat_Bold.ttf");

        pulletLL=(LinearLayout)view.findViewById(R.id.pulletLL);

        calculatorTV=(TextView)view.findViewById(R.id.calculatorTV);
        farmer_guideTV=(TextView)view.findViewById(R.id.farmer_guideTV);
        calculate=(Button)view.findViewById(R.id.calculate);
        reset_btn=(Button)view.findViewById(R.id.reset_btn);

        reset_btn.setTypeface(typeface1);
        calculate.setTypeface(typeface1);

        ingrad_libTV=(TextView)view.findViewById(R.id.ingrad_libTV);
        create_mydietTV=(TextView)view.findViewById(R.id.create_mydietTV);
        nutrient_classTV=(TextView)view.findViewById(R.id.nutrient_classTV);
        pullet_tabTV=(TextView)view.findViewById(R.id.pullet_tabTV);
        laying_tabTV=(TextView)view.findViewById(R.id.laying_tabTV);
        boiler_tabTV=(TextView)view.findViewById(R.id.boiler_tabTV);
        maintitle=(TextView)view.findViewById(R.id.maintitle);
        subtitle=(TextView)view.findViewById(R.id.subtitle);
        left_arw=(TextView)view.findViewById(R.id.left_arw);
        right_arw=(TextView)view.findViewById(R.id.right_arw);
        right_txt=(TextView)view.findViewById(R.id.right_txt);
        left_text=(TextView)view.findViewById(R.id.left_text);

        left_moveLL=(LinearLayout)view.findViewById(R.id.left_moveLL);
        rightmove_LL=(LinearLayout)view.findViewById(R.id.rightmove_LL);

        text1           =(TextView)view.findViewById(R.id.text1);
        text2           =(TextView)view.findViewById(R.id.text2);
        text3           =(TextView)view.findViewById(R.id.text3);
        text4           =(TextView)view.findViewById(R.id.text4);
        text5           =(TextView)view.findViewById(R.id.text5);
        text6           =(TextView)view.findViewById(R.id.text6);
        text7           =(TextView)view.findViewById(R.id.text7);
        text8           =(TextView)view.findViewById(R.id.text8);
        text9           =(TextView)view.findViewById(R.id.text9);
        text_lb_p           =(TextView)view.findViewById(R.id.text_lb_p);

        right_txt.setTypeface(typeface1);
        left_text.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text_lb_p.setTypeface(typeface1);


//laying hen
        layingLL=(LinearLayout)view.findViewById(R.id.layingLL);
        left_moveLL_ly =(LinearLayout)view.findViewById(R.id.left_moveLL_ly);
        rightmove_LL_ly      =(LinearLayout)view.findViewById(R.id.rightmove_LL_ly);

        maintitle_laying =(TextView)view.findViewById(R.id.maintitle_laying);
        subtitle_ly      =(TextView)view.findViewById(R.id.subtitle_ly);


        text11           =(TextView)view.findViewById(R.id.text11);
        text12           =(TextView)view.findViewById(R.id.text12);
        text13           =(TextView)view.findViewById(R.id.text13);
        text14           =(TextView)view.findViewById(R.id.text14);
        text15           =(TextView)view.findViewById(R.id.text15);
        text16           =(TextView)view.findViewById(R.id.text16);
        text17           =(TextView)view.findViewById(R.id.text17);
        text18           =(TextView)view.findViewById(R.id.text18);
        text19           =(TextView)view.findViewById(R.id.text19);
        text_lb_l           =(TextView)view.findViewById(R.id.text_lb_l);

        left_arw_ly           =(TextView)view.findViewById(R.id.left_arw_ly);
        right_arw_ly           =(TextView)view.findViewById(R.id.right_arw_ly);
        right_txt_ly           =(TextView)view.findViewById(R.id.right_txt_ly);
        left_text_ly           =(TextView)view.findViewById(R.id.left_text_ly);

        maintitle_laying.setTypeface(typeface1);
        subtitle_ly.setTypeface(typeface1);
        text11.setTypeface(typeface1);
        text12.setTypeface(typeface1);
        text13.setTypeface(typeface1);
        text14.setTypeface(typeface1);
        text15.setTypeface(typeface1);
        text16.setTypeface(typeface1);
        text17.setTypeface(typeface1);
        text18.setTypeface(typeface1);
        text19.setTypeface(typeface1);
        right_txt_ly.setTypeface(typeface1);
        left_text_ly.setTypeface(typeface1);
        text_lb_l.setTypeface(typeface1);

        //broiler hen
        broilerLL=(LinearLayout)view.findViewById(R.id.broilerLL);
        left_moveLL_br=(LinearLayout)view.findViewById(R.id.left_moveLL_br);
        rightmove_LL_br=(LinearLayout)view.findViewById(R.id.rightmove_LL_br);


        maintitle_broiler =(TextView)view.findViewById(R.id.maintitle_broiler);
        subtitle_br      =(TextView)view.findViewById(R.id.subtitle_br);

                text21           =(TextView)view.findViewById(R.id.text21);
                text22           =(TextView)view.findViewById(R.id.text22);
                text23           =(TextView)view.findViewById(R.id.text23);
                text24           =(TextView)view.findViewById(R.id.text24);
                text25           =(TextView)view.findViewById(R.id.text25);
                text26           =(TextView)view.findViewById(R.id.text26);
                text27           =(TextView)view.findViewById(R.id.text27);
                text28           =(TextView)view.findViewById(R.id.text28);
                text29           =(TextView)view.findViewById(R.id.text29);
                text_lb_b           =(TextView)view.findViewById(R.id.text_lb_b);
        left_arw_br              =(TextView)view.findViewById(R.id.left_arw_br);
        right_arw_br             =(TextView)view.findViewById(R.id.right_arw_br);
        right_txt_br             =(TextView)view.findViewById(R.id.right_txt_br);
        left_text_br             =(TextView)view.findViewById(R.id.left_text_br);

        left_arw_br.setTypeface(typeface);
        right_arw_br.setTypeface(typeface);


        maintitle_broiler.setTypeface(typeface1);
        subtitle_br.setTypeface(typeface1);
        text21.setTypeface(typeface1);
        text22.setTypeface(typeface1);
        text23.setTypeface(typeface1);
        text24.setTypeface(typeface1);
        text25.setTypeface(typeface1);
        text26.setTypeface(typeface1);
        text27.setTypeface(typeface1);
        text28.setTypeface(typeface1);
        text29.setTypeface(typeface1);
        right_txt_br.setTypeface(typeface1);
        left_text_br.setTypeface(typeface1);
        text_lb_b.setTypeface(typeface1);

        my_dietTV=(TextView)view.findViewById(R.id.my_dietTV);
       // whet_branTV=(TextView)view.findViewById(R.id.whet_branTV);
        add_ingredientTV1=(TextView)view.findViewById(R.id.add_ingredientTV1);
       // add_ingredientTV2=(TextView)view.findViewById(R.id.add_ingredientTV2);

        ingrad_libTV.setTypeface(typeface1);
        create_mydietTV.setTypeface(typeface1);
        nutrient_classTV.setTypeface(typeface1);
        pullet_tabTV.setTypeface(typeface1);
        laying_tabTV.setTypeface(typeface1);
        boiler_tabTV.setTypeface(typeface1);
        maintitle.setTypeface(typeface1);
        subtitle.setTypeface(typeface1);
        text1.setTypeface(typeface1);
        text2.setTypeface(typeface1);
        text3.setTypeface(typeface1);
        text4.setTypeface(typeface1);
        text5.setTypeface(typeface1);
        text6.setTypeface(typeface1);
        text7.setTypeface(typeface1);
        text8.setTypeface(typeface1);

        left_arw.setTypeface(typeface);
        right_arw.setTypeface(typeface);
        /*text11.setTypeface(typeface1);
        text12.setTypeface(typeface1);
        text13.setTypeface(typeface1);
        text14.setTypeface(typeface1);
        text15.setTypeface(typeface1);
        text16.setTypeface(typeface1);
        text17.setTypeface(typeface1);
        text18.setTypeface(typeface1);*/


        farmer_guideTV.setTypeface(typeface1);
        calculatorTV.setTypeface(typeface2);

        my_dietTV.setTypeface(typeface1);
//        whet_branTV.setTypeface(typeface1);
        add_ingredientTV1.setTypeface(typeface1);
//        add_ingredientTV2.setTypeface(typeface1);


     /*   my_dietTV.setTextSize(screenWidth / level / 23);
        whet_branTV.setTextSize(screenWidth / level / 23);
        add_ingredientTV1.setTextSize(screenWidth / level / 23);
        add_ingredientTV2.setTextSize(screenWidth / level / 23);

        starter_listTV.setTextSize(screenWidth/level/30);
        grower_listTV.setTextSize(screenWidth/level/30);*/

        layout1=(RelativeLayout)view.findViewById(R.id.fragment1_1);
        layout2=(RelativeLayout)view.findViewById(R.id.fragment1_2);
        layout3=(RelativeLayout)view.findViewById(R.id.fragment1_3);
        ingradlib_Layout=(RelativeLayout)view.findViewById(R.id.ingradlib_Layout);

      if(AppConstants.TWO==true){
          MainActivity.imagetilte.setVisibility(View.GONE);
          MainActivity.text_title2.setVisibility(View.VISIBLE);
          MainActivity.text_title2.setText("Create My Diet");
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.GONE);
          ingradlib_Layout.setVisibility(View.GONE);
           AppConstants.TWO=false;
        }else if (AppConstants.THREE==true){
          MainActivity.imagetilte.setVisibility(View.GONE);
          MainActivity.text_title2.setVisibility(View.VISIBLE);
          MainActivity.text_title2.setText("Nutrient Class");
          MainActivity.imagetilte.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.VISIBLE);
          ingradlib_Layout.setVisibility(View.GONE);
            AppConstants.THREE=false;
        }else if(AppConstants.Four==true){
          MainActivity.imagetilte.setVisibility(View.GONE);
          MainActivity.text_title2.setVisibility(View.VISIBLE);
          MainActivity.text_title2.setText("Ingredients Library");
            ingradlib_Layout.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            AppConstants.Four=false;
        }
      else {
          MainActivity.text_title2.setVisibility(View.GONE);
          MainActivity.imagetilte.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            ingradlib_Layout.setVisibility(View.GONE);
            //AppConstants.ONE=false;
        }

       addingrad=(LinearLayout)view.findViewById(R.id.addingrad);


        addingrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1= new Intent(getActivity(),Choose_diet_activity.class);
                startActivity(intent1);
                getActivity().finish();

            }
        });

        diet_listbtn=(ImageView)view.findViewById(R.id.diet_list_btn);
        nutrient_btn=(ImageView)view.findViewById(R.id.nutrient_btn);
        menu_btn=(ImageView)view.findViewById(R.id.menu_btn);
        menuLL=(LinearLayout)view.findViewById(R.id.menuLL);
        createLL=(LinearLayout)view.findViewById(R.id.createLL);
        nutrientLL=(LinearLayout)view.findViewById(R.id.nutrientLL);



    menuLL.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
              //  AppConstants.Four=true;
            MainActivity.imagetilte.setVisibility(View.GONE);
            MainActivity.text_title2.setVisibility(View.VISIBLE);
            MainActivity.text_title2.setText("Ingredients Library");
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.GONE);
            ingradlib_Layout.setVisibility(View.VISIBLE);

        }
    });


    createLL.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //AppConstants.TWO=true;
            MainActivity.imagetilte.setVisibility(View.GONE);
            MainActivity.text_title2.setVisibility(View.VISIBLE);
            MainActivity.text_title2.setText("Create My Diet");
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.GONE);
                ingradlib_Layout.setVisibility(View.GONE);
            Log.d("createLL11",AppConstants.Chickenselection+"");
                if(AppConstants.Chickenselection==0){
                    showDialog();
                }
        }
    });

    nutrientLL.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //AppConstants.THREE=true;
            MainActivity.imagetilte.setVisibility(View.GONE);
            MainActivity.text_title2.setVisibility(View.VISIBLE);
            MainActivity.text_title2.setText("Nutrient Class");
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.VISIBLE);
            ingradlib_Layout.setVisibility(View.GONE);
        }
    });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               calculate();
            }
        });

        pullet_tabTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentposition=0;

                pulletLL.setVisibility(View.VISIBLE);
                layingLL.setVisibility(View.GONE);
                broilerLL.setVisibility(View.GONE);

                pullet_tabTV.setBackgroundResource(R.drawable.pullet_button);
                laying_tabTV.setBackgroundResource(R.drawable.pulletbutton_nostyle);
                boiler_tabTV.setBackgroundResource(R.drawable.pulletbutton_nostyle);
                pullet_tabTV.setText("Pullet");
                pullet_tabTV.setTextColor(getResources().getColor(R.color.textcolor));
                laying_tabTV.setTextColor(Color.WHITE);
                boiler_tabTV.setTextColor(Color.WHITE);


            }
        });

        laying_tabTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentposition=0;
                upDateGuide_laying();
                layingLL.setVisibility(View.VISIBLE);
                pulletLL.setVisibility(View.GONE);
                broilerLL.setVisibility(View.GONE);

                laying_tabTV.setBackgroundResource(R.drawable.pullet_button);
                pullet_tabTV.setBackgroundResource(R.drawable.pulletbutton_nostyle);
                boiler_tabTV.setBackgroundResource(R.drawable.pulletbutton_nostyle);
                pullet_tabTV.setText("Pullet");
                laying_tabTV.setTextColor(getResources().getColor(R.color.textcolor));
                pullet_tabTV.setTextColor(Color.WHITE);
                boiler_tabTV.setTextColor(Color.WHITE);


            }
        });
        boiler_tabTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentposition=0;
                upDateGuide_broiler();
                broilerLL.setVisibility(View.VISIBLE);
                layingLL.setVisibility(View.GONE);
                pulletLL.setVisibility(View.GONE);


                boiler_tabTV.setBackgroundResource(R.drawable.pullet_button);
                pullet_tabTV.setBackgroundResource(R.drawable.pulletbutton_nostyle);
                laying_tabTV.setBackgroundResource(R.drawable.pulletbutton_nostyle);
                pullet_tabTV.setText("Pullet");
                boiler_tabTV.setTextColor(getResources().getColor(R.color.textcolor));
                pullet_tabTV.setTextColor(Color.WHITE);
                laying_tabTV.setTextColor(Color.WHITE);
            }
        });

      //pullet
        left_moveLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentposition<=0){
                    return;
                }
                currentposition-=1;
                upDateGuide_pullet();
            }
        });

        rightmove_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentposition>=pullet_data.size()-1){
                    return;
                }
                currentposition+=1;
                upDateGuide_pullet();
            }
        });

        //broiler

        left_moveLL_br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentposition <= 0) {
                    return;
                }
                currentposition-=1;
                upDateGuide_broiler();
            }
        });

        rightmove_LL_br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentposition >= broiler_data.size()-1) {
                    return;
                }
                currentposition+=1;
                upDateGuide_broiler();
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alert();

               // Reset_main.showAlertDialog("reset data",getActivity());

                mydietAdapter = new Mydiet_adapter(getActivity(), IngredientSession.selected);
                lv.setAdapter(mydietAdapter);


            }
        });
        //upDateGuide();
        ingradlib_adapter= new Ingradlib_adapter(getActivity(),list_pojo);
        ingradlib_list.setAdapter(ingradlib_adapter);
        upDateGuide_pullet();
        return view;
    }

    private void calculate() {
        if(IngredientSession.selectedIndexes.size()!=0) {
            Intent intent = new Intent(getActivity(), Mppiechart2.class);
            startActivity(intent);
            getActivity().finish();
        }else{

            inflater2=getActivity().getLayoutInflater();
            alert2= inflater2.inflate(R.layout.alert, null);

            TextView alert = (TextView)alert2.findViewById(R.id.alert);
            TextView message = (TextView)alert2.findViewById(R.id.message);
            Button ok = (Button)alert2.findViewById(R.id.ok);
            alert.setTypeface(typeface1);
            message.setTypeface(typeface1);
            ok.setTypeface(typeface1);

            final Dialog  dialog2 = new Dialog(getActivity());
            dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog2.setCancelable(false);
            dialog2.setContentView(alert2);
            dialog2.show();
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dialog2.isShowing()){
                        dialog2.cancel();
                    }

                }
            });

        }
    }

    private void alert() {

        inflater1=getActivity().getLayoutInflater();
        alert1= inflater1.inflate(R.layout.alert_popup, null);

        Button yes_btn=(Button)alert1.findViewById(R.id.yes_btn);
        Button no_btn =(Button)alert1.findViewById(R.id.no_btn);
        are_you=(TextView)alert1.findViewById(R.id.are_you);
        want_to=(TextView)alert1.findViewById(R.id.want_to);

        yes_btn.setTypeface(typeface1);
        no_btn.setTypeface(typeface1);
        are_you.setTypeface(typeface1);
        want_to.setTypeface(typeface1);

        dialog1 = new Dialog(getActivity());
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
                   /* IngredientSession.selectedIndexes.clear();
                    IngredientSession.selected.clear();
                    IngredientSession.selectedIndexes1.clear();*/
                    mydietAdapter = new Mydiet_adapter(getActivity(), IngredientSession.selected);
                    lv.setAdapter(mydietAdapter);
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
      /*  Reset_main.resetdata();
        Reset_main.showAlertDialog("reset data",getActivity());

        mydietAdapter = new Mydiet_adapter(getActivity(), IngredientSession.selected);
        lv.setAdapter(mydietAdapter);*/
    }

    private void showDialog() {

        inflater = getActivity().getLayoutInflater();
        alert = inflater.inflate(R.layout.selectchicken_popup, null);

        TextView heding_popup = (TextView) alert.findViewById(R.id.heding_popup);
        final TextView pullet_item = (TextView) alert.findViewById(R.id.pullet_item);
        final TextView laying_item = (TextView) alert.findViewById(R.id.laying_item);
        final TextView broiler_item = (TextView) alert.findViewById(R.id.broiler_item);
        final LinearLayout pullet_border = (LinearLayout) alert.findViewById(R.id.pullet_border);
        final LinearLayout laying_border = (LinearLayout) alert.findViewById(R.id.laying_border);
        final LinearLayout broiler_border = (LinearLayout) alert.findViewById(R.id.broiler_border);

        heding_popup.setTypeface(typeface1);
        pullet_item.setTypeface(typeface1);
        laying_item.setTypeface(typeface1);
        broiler_item.setTypeface(typeface1);


        dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(alert);
        dialog.show();


        pullet_border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    AppConstants.pullet=true;
                    showDialog2();

                    /*if(p>=1){
                        pullet_border.setBackgroundResource(R.drawable.chicken_popup_style);
                        *//*pullet_item.setTextColor(Color.parseColor("#c12502"));*//*

                        laying_border.setBackgroundResource(R.drawable.chicken_popup_nostyle);
                       *//* laying_item.setTextColor(Color.parseColor("#a3a3a3"));*//*

                        broiler_border.setBackgroundResource(R.drawable.chicken_popup_nostyle);
                        *//*broiler_item.setTextColor(Color.parseColor("#a3a3a3"));*//*
                    }else{
                        pullet_border.setBackgroundResource(R.drawable.chicken_popup_nostyle);
                       *//* pullet_item.setTextColor(Color.parseColor("#a3a3a3"));*//*
                    }*/
                   /* pullet_border.setBackgroundResource(R.drawable.chicken_popup_style);
                    pullet_item.setTextColor(Color.parseColor("#c12502"));*/
                    dialog.cancel();


                }


            }
        });
        laying_border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (dialog.isShowing()) {
                    AppConstants.Chickenselection=3;
                   /* if(l>=1){
                        laying_border.setBackgroundResource(R.drawable.chicken_popup_style);
                       *//* laying_item.setTextColor(Color.parseColor("#c12502"));*//*

                        pullet_border.setBackgroundResource(R.drawable.chicken_popup_nostyle);
                       *//* pullet_item.setTextColor(Color.parseColor("#a3a3a3"));*//*

                        broiler_border.setBackgroundResource(R.drawable.chicken_popup_nostyle);
                       *//* broiler_item.setTextColor(Color.parseColor("#a3a3a3"));*//*
                    }else{
                        laying_border.setBackgroundResource(R.drawable.chicken_popup_nostyle);
                       *//* laying_item.setTextColor(Color.parseColor("#a3a3a3"));*//*
                    }*/
                    dialog.cancel();
                }


            }
        });

        broiler_border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    AppConstants.broiler=true;
                    showDialog2();
                    /*if(b>=1){
                        broiler_border.setBackgroundResource(R.drawable.chicken_popup_style);
                       *//* broiler_item.setTextColor(Color.parseColor("#c12502"));*//*

                        pullet_border.setBackgroundResource(R.drawable.chicken_popup_nostyle);
                       *//* pullet_item.setTextColor(Color.parseColor("#a3a3a3"));*//*

                        laying_border.setBackgroundResource(R.drawable.chicken_popup_nostyle);
                       *//* laying_item.setTextColor(Color.parseColor("#a3a3a3"));*//*
                    }else{
                        broiler_border.setBackgroundResource(R.drawable.chicken_popup_nostyle);
                        *//*broiler_item.setTextColor(Color.parseColor("#a3a3a3"));*//*
                    }*/
                    dialog.cancel();
                 }
            }


        });
    }

    private void showDialog2() {
        inflater3 = getActivity().getLayoutInflater();
        alert3 = inflater.inflate(R.layout.sub_chickenpopup, null);

        dialog2 = new Dialog(getActivity());
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setCancelable(true);
        dialog2.setContentView(alert3);
        dialog2.show();

        TextView starter = (TextView)alert3.findViewById(R.id.starter);
        TextView grower = (TextView)alert3.findViewById(R.id.grower);
        TextView finisher = (TextView)alert3.findViewById(R.id.finisher);
        TextView cancel = (TextView)alert3.findViewById(R.id.cancel);
        final LinearLayout starterLL = (LinearLayout)alert3.findViewById(R.id.starterLL);
        final LinearLayout growerLL = (LinearLayout)alert3.findViewById(R.id.growerLL);
        final LinearLayout finisherLL = (LinearLayout)alert3.findViewById(R.id.finisherLL);
        LinearLayout cancelLL = (LinearLayout)alert3.findViewById(R.id.cancelLL);

        if(AppConstants.pullet) {
            starterLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    starterLL.setBackgroundColor(Color.DKGRAY);
                    AppConstants.Chickenselection = 1;
                    Log.d("starterLL_pullet",AppConstants.Chickenselection+"");
                    dialog2.dismiss();
                }
            });
            growerLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    growerLL.setBackgroundColor(Color.DKGRAY);
                    AppConstants.Chickenselection = 2;
                    dialog2.dismiss();
                }
            });
        }

        if(AppConstants.broiler){
            finisherLL.setVisibility(View.VISIBLE);
            starterLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    starterLL.setBackgroundColor(Color.DKGRAY);
                    AppConstants.Chickenselection = 4;
                    dialog2.dismiss();
                }
            });
            growerLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    growerLL.setBackgroundColor(Color.DKGRAY);
                    AppConstants.Chickenselection = 5;
                    dialog2.dismiss();
                }
            });
            finisherLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finisherLL.setBackgroundColor(Color.DKGRAY);
                    AppConstants.Chickenselection =6;
                    dialog2.dismiss();
                }
            });
        }

        cancelLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
    }


    private void upDateGuide() {

    }

    private void upDateGuide_broiler() {
        if(currentposition<=0){
            left_moveLL_br.setVisibility(View.INVISIBLE);
        }else{
            left_moveLL_br.setVisibility(View.VISIBLE);
            String backwrd =broiler_data.get(currentposition-1).getTitle();
            left_text_br.setText(backwrd);
        }
        if(currentposition>=broiler_data.size()-1){
            rightmove_LL_br.setVisibility(View.INVISIBLE);
        }else{
            rightmove_LL_br.setVisibility(View.VISIBLE);
            String frwd =broiler_data.get(currentposition+1).getTitle();
            right_txt_br.setText(frwd);
        }
        String title=broiler_data.get(currentposition).getTitle();
        String text_21=broiler_data.get(currentposition).getStr1();
        String text_22=broiler_data.get(currentposition).getStr2();
        String text_23=broiler_data.get(currentposition).getStr3();
        String text_24=broiler_data.get(currentposition).getStr4();
        String text_25=broiler_data.get(currentposition).getStr5();
        String text_26=broiler_data.get(currentposition).getStr6();
        String text_27=broiler_data.get(currentposition).getStr7();
        String text_28=broiler_data.get(currentposition).getStr8();
        String text_29=broiler_data.get(currentposition).getStr9();
        String text_b=broiler_data.get(currentposition).getStr_lbs();

        subtitle_br.setText(title);
        text21.setText(text_21);
        text22.setText(text_22);
        text23.setText(text_23);
        text24.setText(text_24);
        text25.setText(text_25);
        text26.setText(text_26);
        text27.setText(text_27);
        text28.setText(text_28);
        text29.setText(text_29);
        text_lb_b.setText(text_b);

    }

    private void upDateGuide_laying() {

        String title=laying_data.get(currentposition).getTitle();
        String text_11=laying_data.get(currentposition).getStr1();
        String text_12=laying_data.get(currentposition).getStr2();
        String text_13=laying_data.get(currentposition).getStr3();
        String text_14=laying_data.get(currentposition).getStr4();
        String text_15=laying_data.get(currentposition).getStr5();
        String text_16=laying_data.get(currentposition).getStr6();
        String text_17=laying_data.get(currentposition).getStr7();
        String text_18=laying_data.get(currentposition).getStr8();
        String text_19=laying_data.get(currentposition).getStr9();
        String text_l=laying_data.get(currentposition).getStr_lbs();

        subtitle_ly.setText(title);
        text11.setText(text_11);
        text12.setText(text_12);
        text13.setText(text_13);
        text14.setText(text_14);
        text15.setText(text_15);
        text16.setText(text_16);
        text17.setText(text_17);
        text18.setText(text_18);
        text19.setText(text_19);
        text_lb_l.setText(text_l);
    }

    private void upDateGuide_pullet() {

        if(currentposition>=pullet_data.size()-1){

            rightmove_LL.setVisibility(View.INVISIBLE);
        }else {
            rightmove_LL.setVisibility(View.VISIBLE);
            String next=pullet_data.get(currentposition+1).getTitle();
            right_txt.setText(next);
        }
        if(currentposition<=0){
            left_moveLL.setVisibility(View.INVISIBLE);
        }else {
            left_moveLL.setVisibility(View.VISIBLE);
            String back=pullet_data.get(currentposition-1).getTitle();
            left_text.setText(back);
        }
        String title=pullet_data.get(currentposition).getTitle();
        String text_1=pullet_data.get(currentposition).getStr1();
        String text_2=pullet_data.get(currentposition).getStr2();
        String text_3=pullet_data.get(currentposition).getStr3();
        String text_4=pullet_data.get(currentposition).getStr4();
        String text_5=pullet_data.get(currentposition).getStr5();
        String text_6=pullet_data.get(currentposition).getStr6();
        String text_7=pullet_data.get(currentposition).getStr7();
        String text_8=pullet_data.get(currentposition).getStr8();
        String text_9=pullet_data.get(currentposition).getStr9();
        String text_p=pullet_data.get(currentposition).getStr_lbs();
        subtitle.setText(title);
        text1.setText(text_1);
        text2.setText(text_2);
        text3.setText(text_3);
        text4.setText(text_4);
        text5.setText(text_5);
        text6.setText(text_6);
        text7.setText(text_7);
        text8.setText(text_8);
        text9.setText(text_9);
        text_lb_p.setText(text_p);

    }


        public static void addIngrad() {
            for(int i : IngredientSession.selectedIndexes) {
                if (!IngredientSession.ids.contains(i)) {
                    IngredientSession.ids.add(i);
                    IngredientSession.selected.add(AppConstants.list.get(i));
                }
            }


            mydietAdapter.setItems(IngredientSession.selected);

        Log.d("addingrad", "yes");
        Log.d("size", AppConstants.list.size() + "");
        Log.d("size123", IngredientSession.selected.size() + "");

    }
}