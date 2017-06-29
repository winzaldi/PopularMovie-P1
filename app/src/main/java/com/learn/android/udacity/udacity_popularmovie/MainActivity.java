package com.learn.android.udacity.udacity_popularmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.learn.android.udacity.udacity_popularmovie.adapter.MoviesAdapter;
import com.learn.android.udacity.udacity_popularmovie.data.MovieResult;
import com.learn.android.udacity.udacity_popularmovie.service.MovieDbServiceFactory;
import com.learn.android.udacity.udacity_popularmovie.service.MovieDbServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final int POPULAR = 0;
    public static final int TOP_RATED = 1;
    private int selectedSort = 0;

    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_movie);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
//        List<Movie> movies = new ArrayList<>();
//
//        for (int i = 0; i < 25; i++) {
//            movies.add(new Movie());
//        }
//        mAdapter.setMovieList(movies);
        getMovies(selectedSort);

    }

    //       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    private void getMovies(int selectedSort) {

        MovieDbServices  services = new MovieDbServiceFactory().create();
        Call<MovieResult> call= null;

        switch (selectedSort){
            case POPULAR :
                call = services.getPopularMovies();
                break;
            case TOP_RATED :
                call = services.getTopRatedMovies();
                break;
            default:
                call = services.getPopularMovies();
        }

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                    mAdapter.setMovieList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                    Log.e("TAG",t.getLocalizedMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.mn_sort_by_popularity:
                selectedSort = POPULAR;
                getMovies(selectedSort);
                break;
            case R.id.mn_sort_by_top_rate:
                selectedSort = TOP_RATED;
                getMovies(selectedSort);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
