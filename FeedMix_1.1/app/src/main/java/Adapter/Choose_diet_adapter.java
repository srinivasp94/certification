package Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.Uga.AppConstants;
import com.android.Uga.Choose_diet_activity;
import com.android.Uga.IngredientSession;
import com.android.Uga.MainActivity;
import com.android.Uga.Mydata;
import com.android.Uga.Pojo.Choose_diet_pojo;
import com.android.Uga.R;

import java.util.ArrayList;

/**
 * Created by New android on 30-06-2016.
 */
public class Choose_diet_adapter extends BaseAdapter {
    ArrayList<Choose_diet_pojo>data;
    Choose_diet_activity activity;
    LayoutInflater inflater;
    TextView list_rowtitleTV,energyTV,energy_wgt,fatTV,fat_wgt,fiberTV,fiber_wgt,protienTV,protein_wgt,moreTV,klTV,lbsTV;
    Typeface typeface1,typeface;
    public static int screenWidth;
    static int screenHeight;
    ViewGroup.LayoutParams params;
    int level;
    TextView tick_mark;
    LinearLayout more_btn;
    boolean isClicked =false;
    LayoutInflater inflater1;
    View alert1;
    Double quantity=1.0;
    boolean isclicked=true;
    private ArrayAdapter list;
    LinearLayout imageLL;
    private InputMethodManager imm;

    //   EditText enter_wt;

    //TextView protien_TV,fat_TV,fiber_TV,energy_TV,calsium_TV,avp_TV,sodium_TV,methion_TV,lysinTV,title_TV;

    public Choose_diet_adapter(Choose_diet_activity context,ArrayList<Choose_diet_pojo> items){
        this.data       =items;
        this.activity   =context;
        inflater        =(LayoutInflater)activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=convertView;

        if(convertView==null)
        {
            view=inflater.inflate(R.layout.choose_diet_row,null);
        }

        Log.d("position123",position+"");
        typeface1=Typeface.createFromAsset(this.activity.getAssets(), "Uga_fonts/Montserrat-Regular.ttf");
        typeface=Typeface.createFromAsset(this.activity.getAssets(),"fonts/fontawesome-webfont.ttf");
        Point size= new Point();
        Display display = this.activity.getWindowManager().getDefaultDisplay();


        screenWidth = size.x;
        screenHeight = size.y;
        Log.d("screen", size.x + ",,," + size.y);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else {

        }
       data.get(position).setEt("1.0");
        energyTV=(TextView)view.findViewById(R.id.energyTV);
        // fatTV=(TextView)view.findViewById(R.id.fatTV);
        fiberTV=(TextView)view.findViewById(R.id.fiberTV);
        protienTV=(TextView)view.findViewById(R.id.protienTV);
        moreTV=(TextView)view.findViewById(R.id.moreTV);
        klTV=(TextView)view.findViewById(R.id.klTV);
        lbsTV=(TextView)view.findViewById(R.id.lbsTV);

        imageLL =(LinearLayout)view.findViewById(R.id.imageLL);

        final TextView list_rowtitleTV=(TextView)view.findViewById(R.id.list_rowtitleTV);
        final TextView tv1=(TextView)view.findViewById(R.id.energy_wgt);
        // TextView tv2=(TextView)view.findViewById(R.id.fat_wgt);
        final TextView tv3=(TextView)view.findViewById(R.id.fiber_wgt);
        final TextView tv4=(TextView)view.findViewById(R.id.protein_wgt);
        final LinearLayout tickmarkll=(LinearLayout)view.findViewById(R.id.tickmarkll);
        final ImageView tick_mark =(ImageView)view.findViewById(R.id.tick_mark);

        list_rowtitleTV.setTypeface(typeface1);
        energyTV.setTypeface(typeface1);
        tv1.setTypeface(typeface1);
//        fatTV.setTypeface(typeface1);
        //tv2.setTypeface(typeface1);
        fiberTV.setTypeface(typeface1);
        tv3.setTypeface(typeface1);
        protienTV.setTypeface(typeface1);
        tv4.setTypeface(typeface1);
        moreTV.setTypeface(typeface1);
        klTV.setTypeface(typeface1);
        lbsTV.setTypeface(typeface1);


        final EditText    enter_wt =(EditText)view.findViewById(R.id.enter_wt);
        /*enter_wt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("testing",enter_wt.getText().toString()+"");
                if(data.get(position).isTextclear()){
                    enter_wt.setText("");
                }
            }
        });*/
        enter_wt.setText(data.get(position).getEt());
        enter_wt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("testing", enter_wt.getText().toString() + "");
                if(enter_wt.getText().toString().equals("1.0")){
                    enter_wt.setText("");

                }

                return false;
            }
        });
        enter_wt.setText(String.valueOf(data.get(position).getQuntity()));

        final View finalView = view;
        tickmarkll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("position789", position + "");
                if(!enter_wt.getText().toString().equals("")) {

                   quantity = Double.parseDouble(enter_wt.getText().toString());
                }

                if (IngredientSession.selectedIndexes.contains(position)) {
                    IngredientSession.removeIndex(position,quantity);
                    tick_mark.setImageResource(R.drawable.gray_tick);
                    Log.d("contain_remove",position+"");
                } else {
                    tick_mark.setImageResource(R.drawable.green_tick);

                    IngredientSession.addIndex(position,quantity);

                    Log.d("add_index", position+"");
                }

                Log.d("Indexes", IngredientSession.selectedIndexes + "");

                finalView.invalidate();

            }
        });

        if (IngredientSession.selectedIndexes.contains(position)) {
            tick_mark.setImageResource(R.drawable.green_tick);
            Log.d("yes", "yes1");
        } else {
            tick_mark.setImageResource(R.drawable.gray_tick);
            Log.d("yes", "no1");
        }

        list_rowtitleTV.setText(data.get(position).getMintitle());
       /* tv1.setText( data.get(position).getEnergy_weight());
        tv2.setText( data.get(position).getFat_weight());
        tv3.setText(data.get(position).getFiber_wieght());
        tv4.setText( data.get(position).getProtien_weight());



*/    final String title=String.valueOf(data.get(position).getTitle());
       // String eng=String.format("%.2f", data.get(position).getEnergy_weight());
      //  Log.d("eng",eng);
        String energy=  String.valueOf(data.get(position).getEnergy_weight());
        final String fat= String.valueOf(data.get(position).getFat_weight());
        final String fiber= String.valueOf(data.get(position).getFiber_wieght());
        final String protien= String.valueOf(data.get(position).getProtien_weight());
        final String calsium=String.valueOf(data.get(position).getCa_wt());
        final String avp= String.valueOf(data.get(position).getAvP_wt());
        final String sodium=String.valueOf(data.get(position).getSodium_wt());
        final String methionine= String.valueOf(data.get(position).getMethionine_wt());
        final String lysine= String.valueOf(data.get(position).getLysine());

        String  energydata=String.valueOf(data.get(position).getEnergy_weight());
        if(IngredientSession.isLbs==true)
        {
             energydata = String.format("%.2f",data.get(position).getEnergy_lbs());
            //energydata =String.valueOf(data.get(position).getEnergy_lbs());
            energy= String.format("%.2f",data.get(position).getEnergy_lbs());
        }
       // String energy_lbs=String.valueOf(data.get(position).getEnergy_lbs());
        //Log.d("energy_lbs",energy_lbs);

        //String  fatdata= String.valueOf(data.get(position).getFat_weight());
        String  fiberdata= String.valueOf(data.get(position).getFiber_wieght());
        String  protiendata= String.valueOf(data.get(position).getProtien_weight());



        /*Double  quantity = Double.parseDouble(enter_wt.getText().toString());
        data.get(position).setQuntity(quantity);*/



        tv1.setText(energydata+" kcal");
        // tv2.setText(fatdata);
        tv3.setText(fiberdata + " %");
        tv4.setText(protiendata + " %");

        imageLL.setBackgroundResource(data.get(position).getImage());

        more_btn=(LinearLayout)view.findViewById(R.id.more_btn);

        final String Energy = energy;
        more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popUP(title,Energy,fat,fiber,protien,calsium,avp,sodium,
                        methionine,lysine);
            }
        });

        level= (int) this.activity.getResources().getDisplayMetrics().density;
        display.getRealSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        Log.d("screenWidth",screenWidth+",,,,,"+level);
        if(level>1.5 && level<2){
            screenWidth= (int) (screenWidth/1.3f);
        }  if(level<1.5){

            screenWidth= (int) (screenWidth/1.6f);

        }

        energyTV.setTextSize(screenWidth / level / 30);
//        fatTV.setTextSize(screenWidth / level / 30);
        fiberTV.setTextSize(screenWidth / level / 30);
        protienTV.setTextSize(screenWidth / level / 30);
        //lbsTV.setTextSize(screenWidth / level / 30);

        tv1.setTextSize(screenWidth / level /30);
        //tv2.setTextSize(screenWidth/level /30);
        tv3.setTextSize(screenWidth/level /30);
        tv4.setTextSize(screenWidth / level / 30);
        moreTV.setTextSize(screenWidth / level / 23);
        lbsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IngredientSession.isLbs = true;
                lbsTV.setTextColor(Color.parseColor("#ce2521"));
                klTV.setTextColor(Color.GRAY);
               // notifyDataSetInvalidated();
                activity.loadInitials();
            }
        });
        klTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IngredientSession.isLbs = false;
                lbsTV.setTextColor(Color.GRAY);
                klTV.setTextColor(Color.parseColor("#ce2521"));
                //notifyDataSetInvalidated();
                activity.loadInitials();
            }
        });

        if(IngredientSession.isLbs){
            lbsTV.setTextColor(Color.parseColor("#ce2521"));
            klTV.setTextColor(Color.GRAY);
        }
        else
        {
            lbsTV.setTextColor(Color.GRAY);
            klTV.setTextColor(Color.parseColor("#ce2521"));
        }


        return view;
    }


    private void popUP(String title, String energy, String fat, String fiber, String protien, String calsium, String avp,
                       String sodium, String methionine, String lysine) {

        inflater1 = this.activity.getLayoutInflater();
        alert1 = inflater1.inflate(R.layout.choosediet_popup, null);

        TextView title_TV=(TextView)alert1.findViewById(R.id.title_TV);
        TextView   protien_TV=(TextView)alert1.findViewById(R.id.protien_TV);
        TextView fat_TV=(TextView)alert1.findViewById(R.id.fat_TV);
        TextView fiber_TV=(TextView)alert1.findViewById(R.id.fiber_TV);
        TextView  energy_TV=(TextView)alert1.findViewById(R.id.energy_TV);
        TextView calsium_TV=(TextView)alert1.findViewById(R.id.calsium_TV);
        TextView  avp_TV=(TextView)alert1.findViewById(R.id.avp_TV);
        TextView sodium_TV=(TextView)alert1.findViewById(R.id.sodium_TV);
        TextView  methion_TV=(TextView)alert1.findViewById(R.id.methion_TV);
        TextView lysinTV=(TextView)alert1.findViewById(R.id.lysinTV);

        TextView   protien_tv=(TextView)alert1.findViewById(R.id.protien_tv);
        TextView fat_tv=(TextView)alert1.findViewById(R.id.fat_tv);
        TextView fiber_tv=(TextView)alert1.findViewById(R.id.fiber_tv);
        TextView  energy_tv=(TextView)alert1.findViewById(R.id.energy_tv);
        TextView calsium_tv=(TextView)alert1.findViewById(R.id.calsium_tv);
        TextView  avp_tv=(TextView)alert1.findViewById(R.id.avp_tv);
        TextView sodium_tv=(TextView)alert1.findViewById(R.id.sodium_tv);
        TextView  methion_tv=(TextView)alert1.findViewById(R.id.methion_tv);
        TextView lysin_tv=(TextView)alert1.findViewById(R.id.lysin_tv);
        TextView quant_txt=(TextView)alert1.findViewById(R.id.quant_txt);

        title_TV.setText(title);
        protien_TV.setText(protien+" %");
        fat_TV.setText(fat+" %");
        fiber_TV.setText(fiber+" %");
        energy_TV.setText(energy+" kcal");
        calsium_TV.setText(calsium+" %");
        avp_TV.setText(avp+" %");
        sodium_TV.setText(sodium+" %");
        methion_TV.setText(methionine+" %");
        lysinTV.setText(lysine+" %");

        title_TV.setTypeface(typeface1);
        protien_TV.setTypeface(typeface1);
        fat_TV.setTypeface(typeface1);
        fiber_TV.setTypeface(typeface1);
        energy_TV.setTypeface(typeface1);
        calsium_TV.setTypeface(typeface1);
        avp_TV.setTypeface(typeface1);
        sodium_TV.setTypeface(typeface1);
        methion_TV.setTypeface(typeface1);
        lysinTV.setTypeface(typeface1);


        protien_tv.setTypeface(typeface1);
        fat_tv.setTypeface(typeface1);
        fiber_tv.setTypeface(typeface1);
        energy_tv.setTypeface(typeface1);
        calsium_tv.setTypeface(typeface1);
        avp_tv.setTypeface(typeface1);
        sodium_tv.setTypeface(typeface1);
        methion_tv.setTypeface(typeface1);
        lysin_tv.setTypeface(typeface1);
        quant_txt.setTypeface(typeface1);

        Dialog dialog=new Dialog(this.activity);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(alert1);
        dialog.show();
    }
}
