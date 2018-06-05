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
import com.srinivas.com.distrct.models.Categories;

import java.util.List;

/**
 * Created by pegasys on 6/5/2018.
 */

public class categoriesAdapter extends ArrayAdapter<String> {

    private Context ctx;
    private List<Categories> categoriesList;

    public categoriesAdapter(@NonNull Context context, @LayoutRes int resource, List<Categories> categoriesList) {
        super(context, resource);
        this.categoriesList = categoriesList;
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
        Categories categories = categoriesList.get(position);
        mViewHolder.textView.setText(categories.categoryName);

        return convertView;
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    private class ViewHolder {
        TextView textView;
    }
}
