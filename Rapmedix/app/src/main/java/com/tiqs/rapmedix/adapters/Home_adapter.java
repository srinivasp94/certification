package com.tiqs.rapmedix.adapters;


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
        import com.tiqs.rapmedix.ListData;
        import com.tiqs.rapmedix.R;

        import java.util.ArrayList;




public class Home_adapter extends BaseAdapter {
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
    ArrayList<String> deals_off;
    ArrayList<String> deals_img;
    ArrayList<String> deals_des;
    public Home_adapter(Context context,int res,ArrayList<String> deals_off,ArrayList<String> deals_img, ArrayList<String> deals_des)
    {
        this.deals_off = deals_off;
        this.deals_img =deals_img ;
        this.deals_des =deals_des ;
        this.res=res;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount()
    {
        // return image.size();
        return deals_off.size();

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
            vh.description = (TextView) convertView.findViewById(R.id.DESC);
            vh.imageView = (ImageView) convertView.findViewById(R.id.topic_grid);

            convertView.setTag(vh);

        } else
        {
            vh=(ViewHolder_grid)convertView.getTag();

        }

       //vh.imageView.setImageResource(image.get(position));
        vh.textView.setText(deals_off.get(position));
        vh.description.setText(deals_des.get(position));
        //vh.imageView.setImage(deals_img.get(position));
        Log.e("imgurl","95      "+deals_img);
        Glide.with(context).
                load(deals_img.get(position)).
                error(R.drawable.doctor_white).
                placeholder(R.drawable.doctor_white).
                into(vh.imageView);

        return convertView;
    }



    class ViewHolder_grid
    {
        TextView textView;
        TextView description;
        ImageView imageView;
    }


}

