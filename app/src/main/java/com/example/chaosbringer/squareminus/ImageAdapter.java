package com.example.chaosbringer.squareminus;


import com.example.chaosbringer.squareminus.R;
import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter {
    private Integer[] images;

    private Context myContext;

    private Bitmap[] cache;

    public ImageAdapter(Context c) {

        myContext = c;


        Field[] list = R.drawable.class.getFields();


        int count = 0, index = 0, j = list.length;


        for(int i=0; i < j; i++)
            if(list[i].getName().startsWith("img_")) count++;

        images = new Integer[count];
        cache = new Bitmap[count];

        try {
            for(int i=0; i < j; i++)
                if(list[i].getName().startsWith("img_"))
                    images[index++] = list[i].getInt(null);
        } catch(Exception e) {}

    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return images[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ImageView imgView;

        int widthscreen = Resources.getSystem().getDisplayMetrics().widthPixels;

        int layoutWidth = widthscreen/4;

        if(convertView == null) {

            imgView = new ImageView(myContext);
            imgView.setLayoutParams(new GridView.LayoutParams(layoutWidth , layoutWidth));

        } else {

            imgView = (ImageView) convertView;

        }

        if(cache[position] == null) {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 20;
            Bitmap thumb = BitmapFactory.decodeResource(myContext.getResources(), images[position], options);

            cache[position] = thumb;
        }

        imgView.setImageBitmap(cache[position]);



        return imgView;
    }


}