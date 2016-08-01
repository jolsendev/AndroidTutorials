package com.example.root.friends;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

/**
 * Created by root on 7/31/16.
 */
public class FriendsDatabase extends SQLiteOpenHelper{
    private static final String TAG = FriendsDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "friends.db";
    private static final int DATABASE_VERSION = 2;
    private final Context mContext;

    interface Tables {
        String FRIENDS = "friends";
    }

    public FriendsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+Tables.FRIENDS+" (" +
                BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FriendsContract.Friends.FRIENDS_NAME+" TEXT NOT NULL,"
                + FriendsContract.Friends.FRIENDS_EMAIL+" TEXT NOT NULL,"
                + FriendsContract.Friends.FRIENDS_PHONENUMBER+" TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        int version = oldVersion;
        if(version == 1){
            // add code to upgrade
            version = 2;
        }
        if (version != DATABASE_VERSION){

            sqLiteDatabase.execSQL("DROP TABLE IS EXISTS "+Tables.FRIENDS);
            onCreate(sqLiteDatabase);
        }

    }

    public static void deleteDatabase(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }

}
