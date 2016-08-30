package com.example.jamie.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class PopularMovieFragment extends Fragment {
    public List<Movie> mMovies;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_popular_movie,container, false);
        GridView gridview = (GridView) v.findViewById(R.id.gridview);
        gridview.setAdapter(new ArrayAdapter(getActivity(), R.layout.fragment_popular_movie, getImageUrl()));
        return v;
    }

    private String[] getImageUrl() {
        String[] imageUrls = new String[mMovies.size()];
        for(int i = 0; i < mMovies.size(); i++){
            imageUrls[i] = "http://image.tmdb.org/t/p/w185/"+mMovies.get(i).getPosterPath();
        }
        return imageUrls;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        // this is where you would set up the adapter

        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        super.onStart();
        updateRawData();
    }

    private void updateRawData() {
        String baseURI = "http://api.themoviedb.org/3/discover/movie?primary_release_date.gte=2015-08-15&primary_release_date.lte=2016-08-22&api_key=02a6d79992ed3e3da1f638dec4c74770";
        FetchPopularMoviesTask moviesTask = new FetchPopularMoviesTask();
        moviesTask.execute(baseURI);
    }


    private void addMoviesToArrayAdapter() {
        for (Movie movie: mMovies) {
            System.out.println(movie.toString());
        }

    }
    public class FetchPopularMoviesTask extends AsyncTask<String, Void, List<Movie>>{

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            mMovies = movies;
            addMoviesToArrayAdapter();
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if(params == null)
                return null;

            try {
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if(inputStream == null) {
                    return null;
                }

                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                return createMovieObjects(buffer.toString());

            } catch(IOException e) {
                return null;

                /**
                 * This finally cleans up by disconnecting the url connection and closing the BuferedReader
                 */
            } finally {
                if(urlConnection != null) {
                    urlConnection.disconnect();
                }
                if(reader != null) {
                    try {
                        reader.close();
                    } catch(final IOException e) {
                    }
                }
            }
        }

        private List<Movie> createMovieObjects(String jsonString) {


            final String MOVIE_RESULTS = "results";
            final String MOVIE_POSTER_PATH = "poster_path";
            final String MOVIE_ADULT = "adult";
            final String MOVIE_OVERVIEW = "overview";
            final String MOVIE_RELEASE_DATE = "release_date";
            final String MOVIE_GENERE_IDS = "genre_ids";
            final String MOVIE_ID = "id";
            final String MOVIE_ORIGINAL__TITLE = "original_title";
            final String MOVIE_ORIGINAL_LANGUAGE = "original_language";
            final String MOVIE_TITLE = "title";
            final String MOVIE_BACKDROP_PATH = "backdrop_path";
            final String MOVIE_POPULARITY = "popularity";
            final String MOVIE_VOTE_COUNT = "vote_count";
            final String MOVIE_VIDEO = "video";
            final String MOVIE_VOTE_AVERAGE= "vote_average";

            List<Movie> movies = new ArrayList<>();

            try {
                JSONObject jsonData = new JSONObject(jsonString);
                JSONArray itemsArray = jsonData.getJSONArray(MOVIE_RESULTS);
                for(int i = 0; i < itemsArray.length(); i++){
                    Movie movie = new Movie();
                    JSONObject jObj = itemsArray.getJSONObject(i);
                    movie.setPosterPath(jObj.getString(MOVIE_POSTER_PATH));
                    movie.setAdult(jObj.getBoolean(MOVIE_ADULT));
                    movie.setOverview(jObj.getString(MOVIE_OVERVIEW));
                    movie.setReleaseDate(jObj.getString(MOVIE_RELEASE_DATE));

//                    JSONArray jArray = jObj.getJSONArray(MOVIE_GENERE_IDS);
//
//                    int[] gen = new int[jArray.length()];
//                    for(int j = 0; j<jArray.length();j++){
//                        gen[j] = jArray.getInt(i);
//                    }
//
//                    movie.setGenreIDs(gen);
                    movie.setId(jObj.getInt(MOVIE_ID));
                    movie.setOriginalTitle(jObj.getString(MOVIE_ORIGINAL__TITLE));
                    movie.setOriginalLanguage(jObj.getString(MOVIE_ORIGINAL_LANGUAGE));
                    movie.setTitle(jObj.getString(MOVIE_TITLE));
                    movie.setBackdropPath(jObj.getString(MOVIE_BACKDROP_PATH));
                    movie.setPopularity(jObj.getDouble(MOVIE_POPULARITY));
                    movie.setVoteCount(jObj.getDouble(MOVIE_VOTE_COUNT));
                    movie.setVideo(jObj.getBoolean(MOVIE_VIDEO));
                    movie.setVoteAverage(jObj.getDouble(MOVIE_VOTE_AVERAGE));
                    movies.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return movies;
        }
    }


}