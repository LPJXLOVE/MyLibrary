package com.technology.lpjxlove.horizontalcircleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by LPJXLOVE on 2016/10/6.
 */

public class HorizontalView extends LinearLayout implements View.OnClickListener {

    private HorizontalAdapter adapter;
    private int totalWidth;
    private int gap;
    private ItemClickListener itemClickListener;
    private LayoutParams layoutParams;
    private int margin;
    /* private Action1<String> endClick;
     private Action1<String> itemClick;*/
    public HorizontalView(Context context) {
        super(context);
        init();
    }

    public HorizontalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.setOrientation(HORIZONTAL);

    }

    public HorizontalAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(final HorizontalAdapter adapter) {
        totalWidth = WindowsUtils.getScreenWidthPixels(this.getContext()) - (getPaddingRight()+getPaddingLeft());
        this.adapter = adapter;
        int size = adapter.getCount();
        removeAllViews();
        int width = (int) WindowsUtils.dp2px(getContext(), 32);
        gap = (int) WindowsUtils.dp2px(getContext(), 2);
        for (int i = 0; i < size; i++) {
            final int position = i;
            ImageView iv = (ImageView) adapter.getView(i, null);
            final LayoutParams params = new LayoutParams(width, width);
            params.setMargins(gap, gap, 0, gap);
            iv.setLayoutParams(params);
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                   itemClickListener.OnItemClick(position);
                }
            });
            addView(iv);
        }
     /*   layoutParams=new LayoutParams(width,width);
        layoutParams.setMargins(gap, gap, 0, gap);
        layoutParams.weight=1.0f;*/
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int size = getChildCount();
        int width;
        for (int i = 0; i < size; i++) {
            View v = getChildAt(i);
            width = (v.getWidth() + gap) * (i + 1)+margin;
            Log.i("test", width + "屏幕宽度" + totalWidth);
            if (width > totalWidth) {
                ImageView view = (ImageView) getChildAt(i - 1);
                view.setImageResource(R.drawable.ic_more);
                view.setOnClickListener(this);
                for (int j = i; j < size; j++) {
                    removeViewAt(i);
                    Log.i("test", "我在执行" + j);
                    Log.i("test", "子View" + size);
                }
                break;
            }
        }



        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onClick(View v) {
       itemClickListener.OnLastItemClick();
    }


    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    public interface ItemClickListener {
        void OnItemClick(int position);

        void OnLastItemClick();
    }




}
