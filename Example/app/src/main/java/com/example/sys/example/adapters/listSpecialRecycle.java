package com.example.sys.example.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.sys.example.R;
import com.example.sys.example.pojo.QualificationList;

import java.util.ArrayList;

/**
 * Created by pegasys on 1/25/2018.
 */

public class listSpecialRecycle extends RecyclerView.Adapter<listSpecialRecycle.ViewHolder> {

    Context context;
    ArrayList<QualificationList> qualificationLists = new ArrayList<>();
    private Boolean[] booleenarray = new Boolean[qualificationLists.size()];

    public listSpecialRecycle(Context context, ArrayList<QualificationList> qualificationLists, Boolean[] booleenarray) {
        this.context = context;
        this.qualificationLists = qualificationLists;
        this.booleenarray = booleenarray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.listitem, viewGroup, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.listitem, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QualificationList qualificationList = qualificationLists.get(position);
        holder.checkBox_item.setText(qualificationList.specialisationName);
        holder.textView_item_special.setText(qualificationList.specialisationName);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox_item;
        TextView textView_item_special;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox_item = (CheckBox) itemView.findViewById(R.id.cb_listitem);
            textView_item_special = (TextView) itemView.findViewById(R.id.tv_special_name);

        }
    }
}
