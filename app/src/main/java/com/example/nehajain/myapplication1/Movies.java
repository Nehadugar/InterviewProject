package com.example.nehajain.myapplication1;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movies {

    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("rating")
    private String rating;
    @SerializedName("releaseYear")
    private String releaseYear;
    @SerializedName("genre")
    private List<String> genre = new ArrayList<String>();

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public List<String> getGenre() {
        return genre;
    }
}




