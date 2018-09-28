package com.tiqs.rapmedix.adapters;


import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiqs.rapmedix.ListData;
import com.tiqs.rapmedix.R;

import java.util.ArrayList;


public class Member_list_adpater extends BaseAdapter {
//    private List<ListData> mylist = new ArrayList<ListData>();

    //ArrayList <ListData>myList = new ArrayList();
    LayoutInflater inflater;
    Context context;
    String s,iddd;
    // String[] Title, Desc, Title1,mobile;
    ArrayList<String>  Desc, Title1,mobile,id1;
    ArrayList<String> image;
    Typeface face;
    TextView tvTitle;
    ImageView imgview;
    AnimatorSet set;
    int res,id ;
    public Member_list_adpater(Context context, int res) {
        this.context = context;
        /*this.image = Image;
        this.Desc = title;*/
        this.res=res;
        this.id=id;
        /*this.Title1 = Title1;
        this.mobile = mobile;
        this.id1 = id;
        this.context = context;*/
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {

       // return image.size();
        return 6;
    }

    @Override
    public ListData getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder_grid vh;

        if (convertView==null) {
            convertView = inflater.inflate(res, parent, false);
            vh=new ViewHolder_grid();
            vh.textView = (TextView) convertView.findViewById(R.id.reviews);
            vh.imageView = (ImageView) convertView.findViewById(R.id.topic_grid);

            convertView.setTag(vh);

                   }else
        {
            vh=(ViewHolder_grid)convertView.getTag();

        }

       /*//vh.imageView.setImageResource(image.get(position));
        Glide
                .with(context)
                .load(image.get(position))
                .centerCrop()
                .placeholder(R.drawable.doctor_black)
                .crossFade()
                .into(vh.imageView);

        vh.textView.setText(Desc.get(position));
*/

        return convertView;
    }



    class ViewHolder_grid
    {
        TextView textView;
        ImageView imageView;
    }


}

