package com.srinivas.com.distrct.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.srinivas.com.distrct.R;
import com.srinivas.com.distrct.models.educationModels;

import java.util.ArrayList;

/**
 * Created by pegasys on 5/4/2018.
 */

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<educationModels> list = new ArrayList<>();

    public EducationAdapter(Context context, ArrayList<educationModels> list) {
        this.context = context;
        this.list = list;
    }

    private OnItemClickListener mListner;

    public interface OnItemClickListener {
        void onitemClick(View view, int position);
    }
    public void setonitemClicklistner(OnItemClickListener itemClickListener) {
        mListner = itemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_education_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        educationModels models = list.get(position);
        holder.name.setText(models.getScl_name());
        holder.address.setText(models.getScl_address());
        holder.phone.setText(models.getScl_phonenumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, address, phone;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txt_sclname);
            address = (TextView) itemView.findViewById(R.id.txt_scl_address);
            phone = (TextView) itemView.findViewById(R.id.txt_scl_phone);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl_rootview);
            relativeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListner != null) {
                mListner.onitemClick(view,getPosition());
            }
        }
    }


}
