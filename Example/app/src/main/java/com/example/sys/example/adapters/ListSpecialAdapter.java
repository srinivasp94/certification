package com.example.sys.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sys.example.R;
import com.example.sys.example.pojo.QualificationList;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by pegasys on 1/25/2018.
 */

public class ListSpecialAdapter extends BaseAdapter {
    Context context;
    ArrayList<QualificationList> qualificationLists = new ArrayList<>();
    private Boolean[] booleenarray= new Boolean[qualificationLists.size()];
    private LayoutInflater inflater;

    public ListSpecialAdapter(Context context, ArrayList<QualificationList> qualificationLists,Boolean[] booleenarray) {
        this.context = context;
        this.qualificationLists = qualificationLists;
        this.booleenarray = booleenarray;
    }

    @Override
    public int getCount() {
        return qualificationLists.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem, viewGroup, false);
            holder = new ViewHolder();
            holder.checkBox_item = (CheckBox) view.findViewById(R.id.cb_listitem);
            holder.textView_item_special = (TextView)view.findViewById(R.id.tv_special_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        QualificationList qualificationList = qualificationLists.get(i);
        holder.checkBox_item.setText(qualificationList.specialisationName);
        holder.textView_item_special.setText(qualificationList.specialisationName);
        holder.checkBox_item.setTag(i);

        if (holder.checkBox_item.isChecked() == true) {
            Toast.makeText(context,""+booleenarray[i],Toast.LENGTH_SHORT).show();
            booleenarray[i]= true;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i + ",");
        }
        holder.checkBox_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(context,"" + compoundButton.getText().toString() ,Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }

    private class ViewHolder {
        CheckBox checkBox_item;
        TextView textView_item_special;
    }
}
