package com.android.Uga;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class PieGraph extends View{


    private Paint piePaint;
    private RectF rectF;
    private float[] data;



    public PieGraph(Context context, AttributeSet attrs){
        super(context,attrs);

        piePaint = new Paint();
        piePaint.setAntiAlias(true);
        piePaint.setDither(true);
        piePaint.setStyle(Paint.Style.FILL);
       /* mShadowPaint = new Paint(0);
        mShadowPaint.setColor(0xff101010);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));*/

    }

    public float[] pieSegment(){

        float[] segValues = new float[this.data.length];
        float Total = getTotal();

        for (int i = 0; i < this.data.length; i++){

            segValues[i] = (this.data[i]/Total) * 360;
        }
      //  x = ( radius of pie chart /2)*cos(angle in RADIANS) [angle in radians =    Math.toRadians(half the sweep angle in degrees)
        return segValues;
    }


    public float getTotal(){

        float total = 0;

        for (float val : this.data){
            total +=val;
        }

        return total;
    }

    @Override
    protected void onDraw(Canvas canvas){

        if (data != null){

            int top = 0;
            int left = 0;
            int endBottom = getHeight();
            int endRight = endBottom;

            rectF = new RectF(left, top, endRight, endBottom);

            float[] segment = pieSegment();

            float segStartPoint = 0;


            for (int i = 0; i < segment.length; i++){

                Random rnd = new Random();

                int[] color = {getResources().getColor(R.color.blue),getResources().getColor(R.color.yellow),
                        getResources().getColor(R.color.red),getResources().getColor(R.color.gray)};

              //  int color = Color.argb(255, (int)segment[i], rnd.nextInt(256), rnd.nextInt(256));
                String[] name ={(String) getResources().getText(R.string.Energy), (String) getResources().getText(R.string.Fat),
                        (String) getResources().getText(R.string.Fiber), (String) getResources().getText(R.string.Methi)};
                piePaint.setColor(color[i]);
                canvas.drawText(name[i],segStartPoint,segment[i], piePaint);
                canvas.drawArc(rectF, segStartPoint, segment[i], true, piePaint);
                segStartPoint += segment[i];

            }
        }
    }

    public void setData(float[] data){

        this.data = data;
        invalidate();
    }
}
