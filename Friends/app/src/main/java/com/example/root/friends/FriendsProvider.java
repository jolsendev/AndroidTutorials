package com.example.root.friends;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by root on 7/31/16.
 */
public class FriendsProvider extends ContentProvider{
    private FriendsDatabase mOpenHelper;
    private static String TAG = FriendsProvider.class.getSimpleName();
    private static UriMatcher sUriMatcher = buildUriMatcher();
    private static final int FRIENDS = 100;
    private static final int FRIENDS_ID = 101;

    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FriendsContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "friends", FRIENDS);
        matcher.addURI(authority, "friends/*", FRIENDS_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new FriendsDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch(match){
            case FRIENDS:
                return FriendsContract.Friends.CONTENT_TYPE;
            case FRIENDS_ID:
                return FriendsContract.Friends.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri");
        }
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(FriendsDatabase.Tables.FRIENDS);
        switch (match){
            case FRIENDS:
                //do nothing
                break;
            case FRIENDS_ID:
                String id = FriendsContract.Friends.getFriendId(uri);
                queryBuilder.appendWhere(BaseColumns._ID +"="+id);
                break;
            default:
                //do nothing
                break;
        }
        Cursor cursor = queryBuilder.query(db, strings, s, strings1,null, null, s1);
        return cursor;

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Log.v(TAG,"insert(uri= "+uri+", ContentValues= "+contentValues.toString()+")");
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match){
            case FRIENDS:
                long recordId = db.insertOrThrow(FriendsDatabase.Tables.FRIENDS, null, contentValues);
                return FriendsContract.Friends.BuildFriendUri(String.valueOf(recordId));
            default:
                throw new IllegalArgumentException("Unknown Uri");


        }

    }

    @Override
    public int delete(Uri uri, String selections, String[] selectionArgs) {
        Log.v(TAG,"delete(Uri="+uri+", String="+selections+", String[]="+selectionArgs+")");
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match= sUriMatcher.match(uri);
        switch (match){
            case FRIENDS:
                int recordId = db.delete(FriendsDatabase.Tables.FRIENDS, selections, selectionArgs);
                return recordId;
            default:
                throw new IllegalArgumentException("Unknown Uri");
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    private void deleteDatabase(){
        mOpenHelper.close();
        FriendsDatabase.deleteDatabase(getContext());
        mOpenHelper = new FriendsDatabase(getContext());
    }
}
