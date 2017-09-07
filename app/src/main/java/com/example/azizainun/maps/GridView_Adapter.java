package com.example.azizainun.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by aziza on 8/27/2017.
 */

public class GridView_Adapter extends BaseAdapter {
    private Context context;
    private List<Bitmap> imageUrls;
    private SparseBooleanArray mSparseBooleanArray;//Variable to store selected Images

    public GridView_Adapter(Context context, List<Bitmap> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }
    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return imageUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(5, 5, 5, 5);
        }  else {
            imageView = (ImageView) convertView;
        }

        try {
            imageView.setImageBitmap(imageUrls.get(position));
        }catch (NullPointerException e) {}

        memoryCache clear = new memoryCache();
        clear.clear();
        return imageView;
    }
}
