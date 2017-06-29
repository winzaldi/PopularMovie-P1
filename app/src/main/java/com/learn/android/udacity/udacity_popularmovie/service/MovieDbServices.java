package com.learn.android.udacity.udacity_popularmovie.service;

import com.learn.android.udacity.udacity_popularmovie.data.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by winzaldi on 6/27/17.
 */


public interface MovieDbServices {


    @GET("/3/movie/popular")
    Call<MovieResult> getPopularMovies();

    @GET("/3/movie/top_rated")
    Call<MovieResult> getTopRatedMovies();


}
