package com.android.Uga;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.android.Uga.Pojo.Choose_diet_pojo;

import Adapter.Choose_diet_adapter;
import Adapter.Mydiet_adapter;

/**
 * Created by New android on 16-07-2016.
 */
public class Reset_main extends Activity {
    static Mydiet_adapter adapter;

    public static void resetdata1(Choose_diet_activity activity){
        //adapter= new Mydiet_adapter(Reset_main.this,IngredientSession.selected);
        IngredientSession.selectedIndexes.clear();
        IngredientSession.selected.clear();
        IngredientSession.ids.clear();
        IngredientSession.selectedIndexes1.clear();
        IngredientSession.selectedIndexes123.clear();
        AppConstants.Chickenselection=0;
        AppConstants.list=Choose_diet_pojo.feedData();
        activity.loadInitials();
        Log.d("applist1234","yes");
    }

    public static void resetdata(){
        //adapter= new Mydiet_adapter(Reset_main.this,IngredientSession.selected);
        IngredientSession.selectedIndexes.clear();
        IngredientSession.selected.clear();
        IngredientSession.ids.clear();
        IngredientSession.selectedIndexes1.clear();
        IngredientSession.selectedIndexes123.clear();
        AppConstants.Chickenselection=0;
        AppConstants.list=Choose_diet_pojo.feedData();
        Log.d("applist", AppConstants.list.size() + ",,,");
        if(Choose_diet_activity.adapter!=null) {
            Choose_diet_activity.adapter.notifyDataSetChanged();
            Choose_diet_activity.lv.setAdapter(Choose_diet_activity.adapter);
        }
        Log.d("applist","yes");
    }


    public static void showAlertDialog(final String msg,final Context context){

    final Dialog dialog = new Dialog(context);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.alert_popup);
    dialog.setCancelable(false);
    dialog.show();
    Button yes_btn = (Button)dialog.findViewById(R.id.yes_btn);
    Button no_btn =(Button)dialog.findViewById(R.id.no_btn);

    yes_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(dialog.isShowing()){
                IngredientSession.selectedIndexes.clear();
                IngredientSession.selected.clear();
                IngredientSession.ids.clear();
                IngredientSession.selectedIndexes1.clear();

                dialog.dismiss();
            }
        }
    });
    if(!(dialog.isShowing())) {dialog.dismiss();}

    no_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (dialog.isShowing()){
                dialog.dismiss();
            }
        }
    });

    if(!(dialog.isShowing())) {dialog.dismiss();}
}

}
