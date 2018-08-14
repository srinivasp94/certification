package Adapter;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.Uga.Layout1;
import com.android.Uga.Pojo.Choose_diet_pojo;
import com.android.Uga.R;

import java.util.ArrayList;

/**
 * Created by New android on 09-08-2016.
 */
public class Ingradlib_adapter extends BaseAdapter {
    ArrayList<Choose_diet_pojo> data;
    FragmentActivity activity;
    LayoutInflater inflater;
    TextView list_rowtitleTV,energyTV,energy_wgt,fatTV,fat_wgt,fiberTV,fiber_wgt,protienTV,protein_wgt,moreTV,klTV,lbsTV;
    Typeface typeface1,typeface;
    public static int screenWidth;
    static int screenHeight;
    int level;
    TextView tick_mark;
    LinearLayout more_btn;
    boolean isClicked =false;
    LayoutInflater inflater1;
    View alert1;
    LinearLayout imageLL;
    public Ingradlib_adapter(FragmentActivity activity,ArrayList<Choose_diet_pojo>items){
        this.data=items;
        this.activity=activity;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.ingradientslib_row, null);
        }
        typeface1=Typeface.createFromAsset(activity.getAssets(), "Uga_fonts/Montserrat-Regular.ttf");
        typeface=Typeface.createFromAsset(activity.getAssets(),"fonts/fontawesome-webfont.ttf");
        Point size= new Point();
        Display display = activity.getWindowManager().getDefaultDisplay();


        screenWidth = size.x;
        screenHeight = size.y;
        Log.d("screen", size.x + ",,," + size.y);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else {

        }

        energyTV=(TextView)convertView.findViewById(R.id.energyTV);
        // fatTV=(TextView)view.findViewById(R.id.fatTV);
        fiberTV=(TextView)convertView.findViewById(R.id.fiberTV);
        protienTV=(TextView)convertView.findViewById(R.id.protienTV);
        moreTV=(TextView)convertView.findViewById(R.id.moreTV);
        imageLL=(LinearLayout)convertView.findViewById(R.id.imageLL);

        final TextView list_rowtitleTV=(TextView)convertView.findViewById(R.id.list_rowtitleTV);
        final TextView tv1=(TextView)convertView.findViewById(R.id.energy_wgt);
        // TextView tv2=(TextView)view.findViewById(R.id.fat_wgt);
        final TextView tv3=(TextView)convertView.findViewById(R.id.fiber_wgt);
        final TextView tv4=(TextView)convertView.findViewById(R.id.protein_wgt);

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


        more_btn=(LinearLayout)convertView.findViewById(R.id.more_btn);


        final String title=String.valueOf(data.get(position).getTitle());
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
        String  fiberdata= String.valueOf(data.get(position).getFiber_wieght());
        String  protiendata= String.valueOf(data.get(position).getProtien_weight());

        tv1.setText(energydata+" kcal");
        // tv2.setText(fatdata);
        tv3.setText(fiberdata + " %");
        tv4.setText(protiendata + " %");
        list_rowtitleTV.setText(data.get(position).getTitle());
        imageLL.setBackgroundResource(data.get(position).getImage());

        final String Energy = energy;
        more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popUP(title,Energy,fat,fiber,protien,calsium,avp,sodium,
                        methionine,lysine);
            }
        });

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


        return convertView;
    }

    private void popUP(String title, String energy, String fat, String fiber, String protien, String calsium, String avp, String sodium, String methionine, String lysine) {
        inflater1 = activity.getLayoutInflater();
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

        Dialog dialog=new Dialog(activity);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(alert1);
        dialog.show();
    }
}
