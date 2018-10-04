package com.example.nehajain.myapplication1;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface
{
    @GET("movies.json")
    Call<List<Movies>>  getMoviesData();
}