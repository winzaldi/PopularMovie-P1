package com.learn.android.udacity.udacity_popularmovie.service;

import com.learn.android.udacity.udacity_popularmovie.data.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by winzaldi on 6/27/17.
 */


public interface MovieDbServices {

    String PATH_POPULAR = "popular";
    String PATH_TOP_RATED = "top_rated";

    @GET("/3/movie/{sort}")
    Call<MovieResult> getMovies(@Path("sort") String order);


}
