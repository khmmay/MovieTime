package com.example.henrik.movietime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henrik on 18.03.2018.
 */

public class Movie implements java.io.Serializable {

    private String name;
    private String releaseDate;
    private String imageResource;
    private Double voteAverage;
    private String plotSynopsis;
    private ArrayList<String> trailers;
    private ArrayList<String> opinions;


    public Movie(String mName, String releaseDate, String imageResource, Double voteAverage, String plotSynopsis, ArrayList<String> trailers, ArrayList<String> opinions){
        name=mName;
        this.releaseDate=releaseDate;
        this.imageResource=imageResource;
        this.voteAverage=voteAverage;
        this.plotSynopsis=plotSynopsis;
        this.trailers=trailers;
        this.opinions=opinions;
    }

    public String getName(){return name;}
    public void setName(String name) {name=name;}

    public String getReleaseDate(){return releaseDate;}
    public void setReleaseDate(String releaseDate) {this.releaseDate=releaseDate;}

    public String getImageResource(){return imageResource;}
    public void setImageResource(String imageResource) {this.imageResource=imageResource;}

    public Double getVoteAverage(){return voteAverage;}
    public void setVoteAverage(Double voteAverage) {this.voteAverage=voteAverage;}

    public String getPlotSynopsis(){return plotSynopsis;}
    public void setPlotSynopsis(String plotSynopsis) {this.plotSynopsis=plotSynopsis;}

    public ArrayList<String> getTrailers() {return trailers;}

    public ArrayList<String> getOpinions() {return opinions;}
}
