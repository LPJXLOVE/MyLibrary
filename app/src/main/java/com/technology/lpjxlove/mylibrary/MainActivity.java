package com.technology.lpjxlove.mylibrary;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.technology.lpjxlove.horizontalcircleview.CircleImageView;
import com.technology.lpjxlove.horizontalcircleview.HorizontalAdapter;
import com.technology.lpjxlove.horizontalcircleview.HorizontalView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HorizontalView.ItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HorizontalView horizontalView = (HorizontalView) findViewById(R.id.horizontal_view);
        MyAdapter adapter=new MyAdapter(this,null);
        horizontalView.setAdapter(adapter);
        horizontalView.setItemClickListener(this);
    }

    @Override
    public void OnItemClick(int position) {
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnLastItemClick() {
        Toast.makeText(this, "end", Toast.LENGTH_SHORT).show();
    }


    class MyAdapter extends HorizontalAdapter{


        public MyAdapter(Context context, List list) {
            super(context, list);
        }

        @Override
        public int getCount() {
            return 20;
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view) {
            ImageView imageView=new ImageView(context);
            imageView.setImageResource(R.color.colorPrimary);
            return imageView;
        }
    }



}
