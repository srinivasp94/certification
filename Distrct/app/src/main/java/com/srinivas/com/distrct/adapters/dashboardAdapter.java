package com.srinivas.com.distrct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.srinivas.com.distrct.R;
import com.srinivas.com.distrct.models.Categories;

import java.util.List;
import java.util.Random;

/**
 * Created by pegasys on 5/1/2018.
 */

public class dashboardAdapter extends BaseAdapter {
    private Context context;
    private List<Categories> categoriesList;
    private LayoutInflater inflater;

    public dashboardAdapter(Context context, List<Categories> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categoriesList.size();
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
        ViewHolder holder = null;

        if (view == null) {
            view = inflater.inflate(R.layout.item_dashboard_grid_layout, null);
            holder = new ViewHolder();
            holder.mTitle = (TextView) view.findViewById(R.id.tv_title);
            holder.layout = (LinearLayout) view.findViewById(R.id.linear_background);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Categories categories = categoriesList.get(position);
        holder.mTitle.setText(categories.categoryName);
        int color_arr[] = {R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark};


        int rnd = new Random().nextInt(color_arr.length);

//        linearLayout.setBackgroundResource(color_arr[rnd]);
        holder.layout.setBackgroundColor(color_arr[rnd]);
        return view;
    }

    private class ViewHolder {
        TextView mTitle;
        LinearLayout layout;
    }
}
