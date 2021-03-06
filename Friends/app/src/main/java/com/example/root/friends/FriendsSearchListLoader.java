package com.example.root.friends;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a5w5nzz on 8/4/2016.
 */
public class FriendsSearchListLoader extends AsyncTaskLoader<List<Friend>> {
    private static final String LOG_TEG = FriendsSearchListLoader.class.getSimpleName();
    private List<Friend> mFriend;
    private ContentResolver mContentResolver;
    private Cursor mCursor;
    private String filterText;
    public FriendsSearchListLoader(Context context, Uri uri, ContentResolver contentResolver, String filterText){
        super(context);
        this.mContentResolver = contentResolver;
        this.filterText = filterText;

    }
    @Override
    public List<Friend> loadInBackground() {
        String[] projection = {
                BaseColumns._ID,
                FriendsContract.FriendsColumns.FRIENDS_NAME,
                FriendsContract.FriendsColumns.FRIENDS_PHONENUMBER,
                FriendsContract.FriendsColumns.FRIENDS_EMAIL
        };
        List<Friend> entry = new ArrayList<Friend>();
        String selection = FriendsContract.FriendsColumns.FRIENDS_NAME + " Like '" + filterText+"%'";
        mCursor = mContentResolver.query(FriendsContract.URI_TABLE, projection, selection, null, null);
        if(mCursor != null){
            if(mCursor.moveToFirst()){
                do{
                    int _id = mCursor.getColumnIndex(BaseColumns._ID);
                    String name = mCursor.getString(mCursor.getColumnIndex(FriendsContract.FriendsColumns.FRIENDS_NAME));
                    String phone = mCursor.getString(mCursor.getColumnIndex(FriendsContract.FriendsColumns.FRIENDS_PHONENUMBER));
                    String email = mCursor.getString(mCursor.getColumnIndex(FriendsContract.FriendsColumns.FRIENDS_EMAIL));
                    Friend friend = new Friend(_id, phone,name,email);
                    entry.add(friend);
                }while (mCursor.moveToNext());
            }
        }
        return entry;
    }

    @Override
    protected void onStartLoading() {
        if(mFriend != null){
            deliverResult(mFriend);
        }

        if(takeContentChanged() || mFriend == null){
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        if(mCursor != null){
            mCursor.close();
        }

        mFriend = null;
    }

    @Override
    public void onCanceled(List<Friend> friends) {
        super.onCanceled(friends);
        if(mCursor != null){
            mCursor.close();
        }
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
    }

    @Override
    public void deliverResult(List<Friend> friends) {
        if(isReset()){
            if(friends != null){
                mCursor.close();
            }
        }

        List<Friend> oldFriendsList = mFriend;
        if(mFriend == null || mFriend.size()==0){
            Log.d(LOG_TEG,"+++++ No Data Returned");
        }
        mFriend = friends;

        if(isStarted()){
            super.deliverResult(friends);
        }

        if(oldFriendsList != friends){
            mCursor.close();
        }
    }
}
