package com.tiqs.rapmedix.adapters;


import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tiqs.rapmedix.Diagnostic_data_model;
import com.tiqs.rapmedix.ListData;
import com.tiqs.rapmedix.R;
import java.lang.String;

import java.util.ArrayList;


public class Health_adapater_checkup extends BaseAdapter {
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
    ArrayList<Diagnostic_data_model> diagnostic_data_models;

    public Health_adapater_checkup(Context context, int res,ArrayList<Diagnostic_data_model> diagnostic_data_models
    ) {
        this.context = context;
        this.diagnostic_data_models = diagnostic_data_models;
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

    @Override
    public int getCount() {

        return diagnostic_data_models.size();
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

        if (convertView==null)
        {
            convertView = inflater.inflate(res, parent, false);
            vh=new ViewHolder_grid();
            vh.textView = (TextView) convertView.findViewById(R.id.doctor_name);
            vh.specialization = (TextView) convertView.findViewById(R.id.specialization);
            vh.address = (TextView) convertView.findViewById(R.id.address);
            vh.distance = (TextView) convertView.findViewById(R.id.distance);
            vh.imageView = (ImageView) convertView.findViewById(R.id.doctor_image);

            convertView.setTag(vh);

                   }else
        {
            vh=(ViewHolder_grid)convertView.getTag();

        }

        Glide
                .with(context)
                .load(diagnostic_data_models.get(position).image)
                .centerCrop()
                .placeholder(R.drawable.doctor_black)
                .crossFade()
                .into(vh.imageView);

        vh.textView.setText(diagnostic_data_models.get(position).hname);
        vh.specialization.setText(diagnostic_data_models.get(position).category_name);
        vh.address.setText(diagnostic_data_models.get(position).address);


        vh.distance.setText(String.valueOf(Math.round(diagnostic_data_models.get(position).distance))+"km");

        return convertView;
    }



    class ViewHolder_grid
    {
        TextView textView,address,specialization,distance;
        ImageView imageView;
    }


}

