package com.example.henrik.movietime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    public static final String currentMovie="currentMovie";
    private static final Movie DEFAULT_MOVIE=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView poster = findViewById(R.id.image_iv);
        TextView title = findViewById(R.id.title_tv);
        TextView releaseDate = findViewById(R.id.releaseDate_tv);
        TextView rating = findViewById(R.id.rating_tv);
        TextView plot = findViewById(R.id.plot_tv);

        Intent intent = getIntent();
        if (intent == null){
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION){
            closeOnError();
            return;
        }

        Movie curMovie = (Movie)intent.getSerializableExtra(currentMovie);
        if (curMovie==DEFAULT_MOVIE){
            closeOnError();
            return;
        }


        String path=getString(R.string.image_basepath)+curMovie.getImageResource();

        Picasso.with(this)
                .load(path)
                .into(poster);

        setTitle(curMovie.getName());
        title.setText(curMovie.getName());
        releaseDate.setText(curMovie.getReleaseDate());
        rating.setText(curMovie.getVoteAverage().toString());
        plot.setText(curMovie.getPlotSynopsis());

    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
}
