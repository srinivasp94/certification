package com.srinivas.com.distrct.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.srinivas.com.distrct.R;
import com.srinivas.com.distrct.models.Villages;

import java.util.List;

/**
 * Created by pegasys on 6/26/2018.
 */

public class VillageAdapter extends ArrayAdapter<Villages> {

    private Context ctx;
    private List<Villages> villagesList;

    public VillageAdapter(@NonNull Context context, @LayoutRes int resource, List<Villages> villagesList) {
        super(context, resource);
        this.villagesList = villagesList;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_value_layout, parent, false);
            mViewHolder.textView = (TextView) convertView.findViewById(R.id.spinner_text);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        Villages villages = villagesList.get(position);
        mViewHolder.textView.setText(villages.villageName);

        return convertView;
    }

    @Override
    public int getCount() {
        return villagesList.size();
    }

    @Nullable
    @Override
    public Villages getItem(int position) {
//        return super.getItem(position);
        return villagesList.get(position);
    }

    private class ViewHolder {
        TextView textView;
    }
}
