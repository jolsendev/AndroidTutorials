package com.example.jamie.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A5W5NZZ on 8/31/2016.
 */
public class CustomMovieAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Movie> mMovieList;
    LayoutInflater mInflater;

    public CustomMovieAdapter(Context mContext, ArrayList<Movie> mMovieList) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
         if (view == null){
             view = mInflater.inflate(R.layout.gridview_image_item, null);
             ImageView imageItem = (ImageView) view.findViewById(R.id.thumbnail);
             Picasso.with(mContext).load(mMovieList.get(position).getPosterPath()).into(imageItem);
         }
        return view;
    }

    public void add(Movie movie){
        mMovieList.add(movie);
    }
}
