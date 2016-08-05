package com.example.root.friends;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

/**
 * Created by root on 8/5/16.
 */
public class FriendsListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Friend>>{
    private final static String LOG_TAG = FriendsListFragment.class.getSimpleName()

    @Override
    public Loader<List<Friend>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Friend>> loader, List<Friend> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Friend>> loader) {

    }

    
}
