package com.example.root.friends;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by root on 7/31/16.
 */
public class FriendsContract {
    interface FriendsColumns {
        String FRIENDS_NAME = "friends_name";
        String FRIENDS_EMAIL = "friends_email";
        String FRIENDS_PHONENUMBER = "friends_phone";

    }
    public static final String CONTENT_AUTHORITY = "com.example.root.friends.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    private static final String PATH_FRIENDS = "friends";
    public static final String[] TOP_LEVEL_PATHS = {
            PATH_FRIENDS
    };
    public static class Friends implements FriendsColumns, BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_FRIENDS).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+CONTENT_AUTHORITY+".friends";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+CONTENT_AUTHORITY+".friends";

        public static Uri BuildFriendUri(String friendId){
            return CONTENT_URI.buildUpon().appendEncodedPath(friendId).build();
        }

        public static String getFriendId(Uri uri){
            return uri.getPathSegments().get(1);
        }

    }
}