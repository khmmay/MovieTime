package com.example.henrik.movietime;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henrik on 18.03.2018.
 */

public class JsonUtils {

        static protected Context context;

        public JsonUtils(Context context){
            this.context = context.getApplicationContext();
        }



    public static ArrayList<Movie> parseMovieJson(String json) throws JSONException {
        try {
            //Create JSONObject of whole json string
            JSONObject rootObject = new JSONObject(json);

            //Get results
            JSONArray resultArray = rootObject.optJSONArray("results");

            ArrayList<Movie> movies = new ArrayList<>();

            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject mov = resultArray.optJSONObject(i);
                String name = mov.optString("title");
                String id = mov.optString("id");
                String date = mov.optString("release_date");
                String imageResource = mov.optString("backdrop_path");
                Double vote = mov.optDouble("vote_average");
                String plotSynopsis = mov.optString("overview");

                //Get trailers
                String test=getResources().getString(R.string.Request_Suffix_opinions);
                ArrayList<String> trailers=null;
                ArrayList<String> trailernames=null;
                String trailerUrlString=Resources.getSystem().getString(R.string.Request_Basepath_trailop)+id+Resources.getSystem().getString(R.string.Request_Suffix_trailer)+Resources.getSystem().getString(R.string.APIkey);
                URL trailerurl = createUrl(trailerUrlString);
                String jsonResponse = null;
                try {
                    jsonResponse = makeHttpRequest(trailerurl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JSONObject rootTrailerObject = new JSONObject(jsonResponse);
                JSONArray trailerArray=rootTrailerObject.optJSONArray("results");
                for(int j=0; j<trailerArray.length();j++){
                    JSONObject trail = resultArray.optJSONObject(j);
                    String trailkey =trail.optString("key");
                    trailers.add(trailkey);
                    String trailname =trail.optString("name");
                    trailernames.add(trailname);
                }


                //Get opinions
                ArrayList<String> optinions=null;
                String opinionsUrlString=Resources.getSystem().getString(R.string.Request_Basepath_trailop)+id+Resources.getSystem().getString(R.string.Request_Suffix_opinions)+Resources.getSystem().getString(R.string.APIkey);
                URL opinionurl = createUrl(opinionsUrlString);
                jsonResponse = null;
                try {
                    jsonResponse = makeHttpRequest(opinionurl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JSONObject rootOpinionObject = new JSONObject(jsonResponse);
                JSONArray opinionArray=rootOpinionObject.optJSONArray("results");
                for(int j=0; j<opinionArray.length();j++){
                    JSONObject op = resultArray.optJSONObject(j);
                    String opcont =op.optString("content");
                    optinions.add(opcont);
                }

                movies.add(new Movie(name, date, imageResource, vote, plotSynopsis, trailers, optinions));
            }


            return movies;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Movie> fetchMovieData(String ReqUrl) {
        URL url = createUrl(ReqUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Movie> movies = null;
        try {
            movies = parseMovieJson(jsonResponse);
        } catch (JSONException e) {
            Log.e("tag", "Problem parsing the movie JSON results" + jsonResponse, e);
        }
        return movies;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("tag", "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("tag", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("tag", "Problem retrieving the JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
