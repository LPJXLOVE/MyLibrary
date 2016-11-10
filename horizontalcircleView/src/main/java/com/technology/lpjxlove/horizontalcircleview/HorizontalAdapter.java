package com.technology.lpjxlove.horizontalcircleview;

/**
 * Created by LPJXLOVE on 2016/4/4.
 */

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * 适配器
 */
public abstract class HorizontalAdapter {
    protected Context context;
    protected List list;

    public HorizontalAdapter(Context context,List list) {
        this.context = context;
        this.list=list;
    }

    /*public HorizontalAdapter(List list) {
        this.list = list;
    }*/

    public abstract int getCount();


    public abstract Object getItem(int position);

    public abstract long getItemId(int position);

    public abstract View getView(int i, View view);
}
