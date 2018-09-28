package com.tiqs.rapmedix.adapters;


import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiqs.rapmedix.Diagnostic_list_helper;
import com.tiqs.rapmedix.ListData;
import com.tiqs.rapmedix.Manifest;
import com.tiqs.rapmedix.R;

import java.util.ArrayList;


public class Diagnostic_list_adapter extends BaseAdapter {
//    private List<ListData> mylist = new ArrayList<ListData>();

    //ArrayList <ListData>myList = new ArrayList();
    LayoutInflater inflater;
    Context context;
    String s,iddd;
    // String[] Title, Desc, Title1,mobile;
    ArrayList<String>  Desc, Title1,mobile,id1;
    ArrayList<String> image;
    Typeface face;
    TextView tvTitle;
    ImageView imgview;
    AnimatorSet set;
    int res,id ;
    public Diagnostic_list_adapter(Context context, int res) {
        this.context = context;
        /*this.image = Image;
        this.Desc = title;*/
        this.res=res;
        this.id=id;
        /*this.Title1 = Title1;
        this.mobile = mobile;
        this.id1 = id;
        this.context = context;*/
        inflater = LayoutInflater.from(this.context);

    }
     ArrayList<Diagnostic_list_helper> diagnostic_list_helper;
    public Diagnostic_list_adapter(Context context, ArrayList<Diagnostic_list_helper> diagnostic_list_helper) {
        this.context = context;
        this.diagnostic_list_helper = diagnostic_list_helper;
        //this.doctor_name = doctor_name;

        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {

       // return image.size();
        return diagnostic_list_helper.size();
    }

    @Override
    public ListData getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder_grid vh;

        if (convertView==null) {
            convertView = inflater.inflate(R.layout.categorie_select_item1, parent, false);
            vh=new ViewHolder_grid();
            vh.textView = (TextView) convertView.findViewById(R.id.name_scan);
            //vh.imageView = (ImageView) convertView.findViewById(R.id.image_scan);

            convertView.setTag(vh);

                   }else
        {
            vh=(ViewHolder_grid)convertView.getTag();


        }
        vh.textView.setText(diagnostic_list_helper.get(position).getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(context, DoctorDescription.class);
                //intent.putExtra("doctorId", vh.doctorId);
                //context.startActivity(intent);
            }
        });


        return convertView;
    }



    class ViewHolder_grid
    {
        TextView textView;
        ImageView imageView;
    }


}

