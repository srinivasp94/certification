package com.tiqs.rapmedix;


import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class Service_grid extends BaseAdapter {
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
	public Service_grid(Context context, ArrayList<String> Image, ArrayList<String> title) {
		this.context = context;
		this.image = Image;
		this.Desc = title;
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

		return image.size();
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
		ViewHolder_service_grid vh;

		if (convertView==null) {
			convertView = inflater.inflate(R.layout.service_item, parent, false);
			vh=new ViewHolder_service_grid();
			vh.textView = (TextView) convertView.findViewById(R.id.service_text);
			vh.imageView = (ImageView) convertView.findViewById(R.id.service_image);
			//vh.textView.setTypeface(face);

			convertView.setTag(vh);

		}else
		{
			vh=(ViewHolder_service_grid)convertView.getTag();

		}

		//vh.imageView.setImageResource(image.get(position));
		vh.textView.setText(image.get(position));
		Glide
				.with(context)
				.load(Desc.get(position))
				.centerCrop()
				.placeholder(R.drawable.doctor_black)
				.crossFade()
				.into(vh.imageView);




		return convertView;
	}





}
class ViewHolder_service_grid
{
	TextView textView;
	ImageView imageView;
}
