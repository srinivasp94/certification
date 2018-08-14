package Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.Uga.AppConstants;
import com.android.Uga.IngredientSession;
import com.android.Uga.Pojo.Choose_diet_pojo;
import com.android.Uga.R;
import com.android.Uga.Reset_main;

import java.util.ArrayList;

/**
 * Created by New android on 13-07-2016.
 */
public class Mydiet_adapter extends BaseAdapter {
    LayoutInflater inflater,inflater1;
    Activity activity;
    View alert;
    Typeface typeface1,typeface,typeface2;
    ArrayList<Choose_diet_pojo> data;
    public Mydiet_adapter(Activity context, ArrayList<Choose_diet_pojo> items){
        this.data=items;
        this.activity=context;
        inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    public void setItems(ArrayList<Choose_diet_pojo> items){
        this.data=items;
        inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View view=convertView;
        if (convertView==null) {
            view =inflater.inflate(R.layout.mydiet_row,null);
        }
        typeface1= Typeface.createFromAsset(this.activity.getAssets(), "Uga_fonts/Montserrat-Regular.ttf");
        typeface2=Typeface.createFromAsset(this.activity.getAssets(), "Uga_fonts/Montserrat_Bold.ttf");
        TextView titleTV=(TextView)view.findViewById(R.id.titleTV);
        TextView energyTV=(TextView)view.findViewById(R.id.energyTV);
        TextView fatTV=(TextView)view.findViewById(R.id.fatTV);
        TextView fiberTV=(TextView)view.findViewById(R.id.fiberTV);
        TextView protienTV=(TextView)view.findViewById(R.id.protienTV);

        final String title = data.get(position).getTitle();
        final String sub_title = data.get(position).getMintitle();
         String energy = String.valueOf(data.get(position).getEnergy_weight());
        final String fat_text= String.valueOf(data.get(position).getFat_weight());
        final String fiber_text= String.valueOf(data.get(position).getFiber_wieght());
        final String protien_text= String.valueOf(data.get(position).getProtien_weight());
        final String calsium=String.valueOf(data.get(position).getCa_wt());
        final String avp= String.valueOf(data.get(position).getAvP_wt());
        final String sodium=String.valueOf(data.get(position).getSodium_wt());
        final String methionine= String.valueOf(data.get(position).getMethionine_wt());
        final String lysine= String.valueOf(data.get(position).getLysine());
        final String quantity=String.valueOf(data.get(position).getQuntity());

        if(IngredientSession.isLbs==true){
            energy = String.format("%.2f",data.get(position).getEnergy_lbs());
        }
            final int index=AppConstants.currentIndex;
        Log.d("index", index + ","+quantity);

                titleTV.setText(sub_title);
                energyTV.setText(energy+"kcal");
                fatTV.setText(fat_text+"%");
                fiberTV.setText(fiber_text+"%");
                protienTV.setText(protien_text+"%");

        final View finalView = view;
        final String Energy = energy;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(title,Energy,fat_text,fiber_text,protien_text,calsium,avp,sodium,methionine,lysine,quantity);
            }
        });
        finalView.invalidate();

        return view;
    }

    private void popUp(String title,String energy,String fat_text,String fiber_text,String protien_text,
                       String calsium,String avp,String sodium,String methionine,String lysine,String quantity) {
         inflater1 = this.activity.getLayoutInflater();
        alert= inflater1.inflate(R.layout.choosediet_popup, null);
        Log.d("quantity123",quantity);

        TextView title_TV=(TextView)alert.findViewById(R.id.title_TV);
        TextView   protien_TV=(TextView)alert.findViewById(R.id.protien_TV);
        TextView fat_TV=(TextView)alert.findViewById(R.id.fat_TV);
        TextView fiber_TV=(TextView)alert.findViewById(R.id.fiber_TV);
        TextView  energy_TV=(TextView)alert.findViewById(R.id.energy_TV);
        TextView calsium_TV=(TextView)alert.findViewById(R.id.calsium_TV);
        TextView  avp_TV=(TextView)alert.findViewById(R.id.avp_TV);
        TextView sodium_TV=(TextView)alert.findViewById(R.id.sodium_TV);
        TextView  methion_TV=(TextView)alert.findViewById(R.id.methion_TV);
        TextView lysinTV=(TextView)alert.findViewById(R.id.lysinTV);
        TextView quant=(TextView)alert.findViewById(R.id.quant);



        TextView   protien_tv=(TextView)alert.findViewById(R.id.protien_tv);
        TextView fat_tv=(TextView)alert.findViewById(R.id.fat_tv);
        TextView fiber_tv=(TextView)alert.findViewById(R.id.fiber_tv);
        TextView  energy_tv=(TextView)alert.findViewById(R.id.energy_tv);
        TextView calsium_tv=(TextView)alert.findViewById(R.id.calsium_tv);
        TextView  avp_tv=(TextView)alert.findViewById(R.id.avp_tv);
        TextView sodium_tv=(TextView)alert.findViewById(R.id.sodium_tv);
        TextView  methion_tv=(TextView)alert.findViewById(R.id.methion_tv);
        TextView lysin_tv=(TextView)alert.findViewById(R.id.lysin_tv);
        TextView quant_txt=(TextView)alert.findViewById(R.id.quant_txt);

        quant_txt.setText("Quantity");
        title_TV.setText(title);
        protien_TV.setText(protien_text+" %");
        fat_TV.setText(fat_text+" %");
        fiber_TV.setText(fiber_text+" %");
        energy_TV.setText(energy+" kcal");
        calsium_TV.setText(calsium+" %");
        avp_TV.setText(avp+" %");
        sodium_TV.setText(sodium+" %");
        methion_TV.setText(methionine+" %");
        lysinTV.setText(lysine + " %");
        quant.setText(quantity + " kg");
        if(IngredientSession.isLbs==true){
            quant.setText(quantity + " lbs");
        }

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
        quant.setTypeface(typeface1);

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


        Dialog dialog = new Dialog(this.activity);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(alert);
        dialog.show();
    }
}
