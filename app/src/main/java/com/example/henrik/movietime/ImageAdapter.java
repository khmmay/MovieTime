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

    public int getCount() {
        return mThumbIds.length;
    }

    public Movie getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        final Movie currMov = (Movie) getItem(position);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(120*3, 175*3));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(4, 4, 4, 4);
        } else {
            imageView = (ImageView) convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);
        Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png").into(imageView);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,
            R.drawable.interst, R.drawable.interst,

    };
}
