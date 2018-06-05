package com.example.henrik.movietime;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Henrik on 17.03.2018.
 */

public class ImageAdapter extends ArrayAdapter<Movie> {
    private Context mContext;

    public ImageAdapter(Context c, ArrayList<Movie> movies) {
        super(c,0,movies);
        mContext = c;
    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        final Movie currMov = getItem(position);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(120*3, 68*3));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(4, 4, 4, 4);
        } else {
            imageView = (ImageView) convertView;
        }

        String path=mContext.getString(R.string.image_basepath)+currMov.getImageResource();
        Picasso.with(mContext).load(path).into(imageView);
        return imageView;
    }

}
