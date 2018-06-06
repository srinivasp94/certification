package com.example.pegasys.pegasyshmis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pegasys.pegasyshmis.R;
import com.example.pegasys.pegasyshmis.model.Records;

import java.util.ArrayList;

/**
 * Created by pegasys on 5/28/2018.
 */

public class PdfListAdapter extends RecyclerView.Adapter<PdfListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Records> listPdfs;
    private View itemview;

    public PdfListAdapter(Context context, ArrayList<Records> listPdfs) {
        this.context = context;
        this.listPdfs = listPdfs;
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.files_item, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Records records = listPdfs.get(position);
        holder.textView_title.setText(records.filename);
    }

    @Override
    public int getItemCount() {
        return listPdfs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_title,textView_path;
        Button btn_parse;
        public ViewHolder(View itemView) {
            super(itemView);
            textView_title = (TextView)itemView.findViewById(R.id.txt_pdftitle);
            textView_path= (TextView)itemView.findViewById(R.id.txt_pdfpath);
            btn_parse= (Button)itemView.findViewById(R.id.btn_parse);
            btn_parse.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view,getPosition());
            }
        }
    }
}
