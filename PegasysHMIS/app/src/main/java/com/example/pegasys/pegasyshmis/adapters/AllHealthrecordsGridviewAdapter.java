package com.example.pegasys.pegasyshmis.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pegasys.pegasyshmis.R;
import com.example.pegasys.pegasyshmis.model.AllHealthRecords;

import java.util.ArrayList;

/**
 * Created by pegasys on 2/8/2018.
 */

public class AllHealthrecordsGridviewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AllHealthRecords> mAllHealthRecordses;
    private LayoutInflater layoutInflater;

    public AllHealthrecordsGridviewAdapter(Context context, ArrayList<AllHealthRecords> mAllHealthRecordses) {
        this.context = context;
        this.mAllHealthRecordses = mAllHealthRecordses;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mAllHealthRecordses.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.doc_categorislayout, viewGroup, false);
            holder = new Holder();
            holder.txt_record_name = view.findViewById(R.id.tv_txt);
            holder.iv_recordProfile = view.findViewById(R.id.iv_img);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        AllHealthRecords healthRecords = mAllHealthRecordses.get(position);
        Log.i("classname", healthRecords.toString());

        holder.txt_record_name.setText(mAllHealthRecordses.get(position).getName());
//        holder.iv_recordProfile.setImageResource(mAllHealthRecordses.get(position).setImage());

        return view;
    }

    private class Holder {
        TextView txt_record_name, txt_recordAge;
        ImageView iv_recordProfile;
    }
}
