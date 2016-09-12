package com.example.jamie.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MovieDetailView extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_view);
        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra(String.valueOf(MovieDetails.MOVIE_KEY));

        Toast.makeText(this, movie.getOriginalTitle(), Toast.LENGTH_LONG).show();

    }
}
