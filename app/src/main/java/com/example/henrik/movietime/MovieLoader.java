package com.example.henrik.movietime;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Henrik on 04.06.2018.
 */

class MovieLoader extends AsyncTaskLoader<ArrayList<Movie>> {
    String url;

    public MovieLoader(Context context, String baseUri) {
        super(context);
        url=baseUri;
    }

    protected void onStartLoading() {forceLoad();}

    @Override
    public ArrayList<Movie> loadInBackground() {
        if (url == null) {
            return null;
        }
        ArrayList<Movie> movies= JsonUtils.fetchMovieData(url);
        return movies;
    }
}
