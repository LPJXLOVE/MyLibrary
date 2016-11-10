package com.technology.lpjxlove.horizontalcircleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by LPJXLOVE on 2016/4/17.
 */
public class CircleImageView extends View {
    public CircleImageView(Context context) {
        this(context,null);
    }
    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    private Bitmap bitmap;
    private Rect bitmapRect=new Rect();
    private PaintFlagsDrawFilter pdf=new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
    private Paint paint = new Paint();
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
    }
    private Bitmap mDstB=null;
    private PorterDuffXfermode xfermode=new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);

    public void setImageBitmap(Bitmap bitmap)
    {
        this.bitmap=bitmap;
        try {
            if(android.os.Build.VERSION.SDK_INT>=11)
            {
                setLayerType(LAYER_TYPE_SOFTWARE, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Bitmap makeDst(int w, int h)
    {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.parseColor("#ffffffff"));
        c.drawOval(new RectF(0, 0, w, h), p);
        return bm;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(null==bitmap)
        {
            return;
        }

        if(null==mDstB)
        {
            mDstB=makeDst(getWidth(), getHeight());
        }


        bitmapRect.set(0, 0, getWidth(), getHeight());
        canvas.save();
        canvas.setDrawFilter(pdf);
        canvas.drawBitmap(mDstB, 0, 0, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap, null, bitmapRect, paint);
        paint.setXfermode(null);
        canvas.restore();
    }
}
