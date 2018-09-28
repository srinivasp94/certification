package com.tiqs.rapmedix;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Sohail on 5/1/2017.
 */
public class Font_style
{
    public static  void deepChangeTextColor(Context context, ViewGroup parentLayout){
        Typeface face= Typeface.createFromAsset(context.getAssets(),"fonts/monster.ttf");
        for (int count=0; count < parentLayout.getChildCount(); count++){
            View view = parentLayout.getChildAt(count);
            if(view instanceof TextView){
                ((TextView)view).setTypeface(face);
            } else if(view instanceof Button){
                ((Button)view).setTypeface(face);
            }else if(view instanceof EditText){
                ((EditText)view).setTypeface(face);
            }
            else if(view instanceof ViewGroup){
                deepChangeTextColor(context,(ViewGroup)view);
            }
        }
    }

}
