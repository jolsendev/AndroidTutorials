package com.example.jamieolsen.flickerbrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by a5w5nzz on 6/15/2016.
 */
public class FlickerRecylerViewAdaptor extends RecyclerView.Adapter<FlickerImageViewHolder> {
    private List<Photo> mPhotoList;
    private Context mContext;

    public FlickerRecylerViewAdaptor(Context mContext, List<Photo> mPhotoList) {
        this.mContext = mContext;
        this.mPhotoList = mPhotoList;
    }

    @Override
    public FlickerImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse,null);
        FlickerImageViewHolder flickerImageViewHolder = new FlickerImageViewHolder(view);
        return flickerImageViewHolder;
    }

    @Override
    public void onBindViewHolder(FlickerImageViewHolder holder, int position) {
        Photo photoItem = mPhotoList.get(position);

        Picasso.with(mContext).load(photoItem.getmImage())
                .error(R.drawable.android_place_holder)
                .placeholder(R.drawable.android_place_holder)
                .into(holder.thumbnail);

        holder.title.setText(photoItem.getmTitle());

    }

    @Override
    public int getItemCount() {
        return (null != mPhotoList ? mPhotoList.size() : 0);
    }
}
