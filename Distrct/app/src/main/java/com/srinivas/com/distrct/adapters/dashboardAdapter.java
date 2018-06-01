package com.srinivas.com.distrct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.srinivas.com.distrct.R;
import com.srinivas.com.distrct.models.DashBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pegasys on 5/1/2018.
 */

public class dashboardAdapter extends BaseAdapter {
    private Context context;
    private List<DashBoard> boardList = new ArrayList<>();
    private LayoutInflater inflater;

    public dashboardAdapter(Context context, List<DashBoard> boardList) {
        this.context = context;
        this.boardList = boardList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return boardList.size();
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
        DashBoard dashBoardModel = boardList.get(position);
        holder.mTitle.setText(dashBoardModel.getmName());
        holder.layout.setBackgroundColor(dashBoardModel.getmColor());
        return view;
    }

    private class ViewHolder {
        TextView mTitle;
        LinearLayout layout;
    }
}
