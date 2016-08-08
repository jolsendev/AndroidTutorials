package com.example.root.friends;

import android.content.ContentResolver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

/**
 * Created by root on 8/5/16.
 */
public class FriendsListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Friend>>{
    private final static String LOG_TAG = FriendsListFragment.class.getSimpleName();
    private FriendsCustomAdapter mFriendsAdapter;
    private static final int LOADER_ID = 1;
    private ContentResolver mResolver;
    private List<Friend> mFriends;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mResolver = getActivity().getContentResolver();
        mFriendsAdapter = new FriendsCustomAdapter(getActivity(), getChildFragmentManager());
        setEmptyText("No Friends");
        setListAdapter(mFriendsAdapter);
        setListShown(false);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Friend>> onCreateLoader(int id, Bundle args) {
        mResolver = getActivity().getContentResolver();

        return new FriendsListLoader(getActivity(), FriendsContract.URI_TABLE, mResolver);
    }

    @Override
    public void onLoadFinished(Loader<List<Friend>> loader, List<Friend> friends) {
        mFriendsAdapter.setData(friends);
        mFriends = friends;
        if(isResumed()){
            setListShown(true);
        }else{
            setListShownNoAnimation(true);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Friend>> loader) {
        mFriendsAdapter.setData(null);
    }


}
