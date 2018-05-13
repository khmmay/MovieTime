package com.example.henrik.movietime;

import android.app.LoaderManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int Movie_Loader_ID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageAdapter adapter = new ImageAdapter(this, new ArrayList<Movie>());

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter( adapter);









        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                launchDetailActivity(position);
            }
        });

        try {
            String test= getResources().getString((R.string.JSONreply));
            ArrayList<Movie> mList = JsonUtils.parseMovieJson(test);
            adapter.addAll(mList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        LoaderManager loaderManager=getLoaderManager();
//        loaderManager.initLoader(Movie_Loader_ID, null, this);
    }

    private void launchDetailActivity(int position){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION,position);
        startActivity(intent);
    }
}
