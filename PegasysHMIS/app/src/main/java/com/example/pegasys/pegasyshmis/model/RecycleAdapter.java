package com.example.pegasys.pegasyshmis.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pegasys.pegasyshmis.R;

import java.util.ArrayList;

/**
 * Created by pegasys on 3/22/2018.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<specDoclist> list;
    private String special;

    public RecycleAdapter(Context mContext, ArrayList<specDoclist> list,String special) {
        this.mContext = mContext;
        this.list = list;
        this.special = special;
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardlayout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
        specDoclist doclist = list.get(position);
        holder.distance.setText(doclist.distance + "K.M");
        holder.txt_docName.setText(doclist.Doc_name);
        holder.txt_exp.setText(doclist.experience);
        holder.txt_phone.setText(doclist.phone);
        holder.txt_specialization.setText(special);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_docName, txt_specialization, txt_exp, distance,txt_phone;
        ImageView img_profile;
        LinearLayout call_button;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_docName = (TextView) itemView.findViewById(R.id.txt_docname);
            txt_specialization = (TextView) itemView.findViewById(R.id.txt_doc_Specialname);
            txt_exp = (TextView) itemView.findViewById(R.id.txt_docexperiance);
            distance= (TextView) itemView.findViewById(R.id.txt_diastance);
            txt_phone = (TextView)itemView.findViewById(R.id.txt_phone);
            call_button = (LinearLayout) itemView.findViewById(R.id.call);
            img_profile = (ImageView) itemView.findViewById(R.id.img_prifile);
            cardView = (CardView)itemView.findViewById(R.id.card);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view != null) {
                mListener.onItemClick(view,getPosition());
            }
        }
    }
}
