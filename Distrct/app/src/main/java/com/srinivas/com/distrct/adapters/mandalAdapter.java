package com.srinivas.com.distrct.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.srinivas.com.distrct.R;
import com.srinivas.com.distrct.models.Mandals;

import java.util.List;

/**
 * Created by pegasys on 6/5/2018.
 */

public class mandalAdapter extends ArrayAdapter<String> {

    private Context ctx;
    private List<Mandals> mandalList;

    public mandalAdapter(@NonNull Context context, @LayoutRes int resource, List<Mandals> mandalList) {
        super(context, resource);
        this.mandalList = mandalList;
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
        Mandals mandalsModel= mandalList.get(position);
        mViewHolder.textView.setText(mandalsModel.mandalName);

        return convertView;
    }

    @Override
    public int getCount() {
        return mandalList.size();
    }

    private class ViewHolder {
        TextView textView;
    }
}
