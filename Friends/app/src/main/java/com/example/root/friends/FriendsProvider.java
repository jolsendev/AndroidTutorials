package com.example.root.friends;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
                return FriendsContract.Friend.CONTENT_TYPE;
            case FRIENDS_ID:
                return FriendsContract.Friend.CONTENT_ITEM_TYPE;
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
                String id = FriendsContract.Friend.getFriendId(uri);
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
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match){
            case FRIENDS:
                long recordId = db.insertOrThrow(FriendsDatabase.Tables.FRIENDS, null, contentValues);
                return FriendsContract.Friend.buildFriendUri(String.valueOf(recordId));
            default:
                throw new IllegalArgumentException("Unknown Uri");


        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.v(TAG,"delete(Uri="+uri+", String="+selection+", String[]="+selectionArgs+")");
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match= sUriMatcher.match(uri);

        if(uri.equals(FriendsContract.URI_TABLE)){
            deleteDatabase();
            return 0;
        }

        switch (match){
            case FRIENDS:
                //do nothing
                break;
            case FRIENDS_ID:
                String recordId = FriendsContract.Friend.getFriendId(uri);//db.delete(FriendsDatabase.Tables.FRIENDS, selection, selectionArgs)
                String selectionCriteria = BaseColumns._ID+"="+recordId+(!TextUtils.isEmpty(selection)? "AND ("+selection+")": "" );
                return db.delete(FriendsDatabase.Tables.FRIENDS, selectionCriteria, selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown Uri");
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        Log.v(TAG,"update(Uri="+uri+", ContentValues="+contentValues+", String="+selection+", String[]="+selectionArgs+")");
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match= sUriMatcher.match(uri);
        String selectionCriteria = selection;
        switch (match){
            case FRIENDS:
                break;
            case FRIENDS_ID:
                String id = FriendsContract.Friend.getFriendId(uri);
                selectionCriteria = BaseColumns._ID+"="+id+(!TextUtils.isEmpty(selection) ? "AND ("+selection+")" : "");
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri");
        }
        return db.update(FriendsDatabase.Tables.FRIENDS,contentValues,selectionCriteria,selectionArgs);
    }

    private void deleteDatabase(){
        mOpenHelper.close();
        FriendsDatabase.deleteDatabase(getContext());
        mOpenHelper = new FriendsDatabase(getContext());
    }
}
