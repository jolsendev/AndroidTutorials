package com.example.root.friends;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by root on 8/8/16.
 */
public class FriendsDialog extends android.support.v4.app.DialogFragment {
    private LayoutInflater mLayoutInflater;
    public final static String DIALOG_TYPE = "command";
    public final static String DELETE_RECORD= "deleteRecord";
    public final static String DELETE_DATABASE= "deleteDatabase";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mLayoutInflater = getActivity().getLayoutInflater();
        final View view  = mLayoutInflater.inflate(R.layout.friend_dialog,null);
        String command = getArguments().getString(DIALOG_TYPE);
        if(command.equals(DELETE_RECORD)){
            final int _id = getArguments(). getInt(FriendsContract.FriendsColumns.FRIENDS_ID);
            String name = getArguments().getString(FriendsContract.FriendsColumns.FRIENDS_NAME);
            TextView popup = (TextView) view.findViewById(R.id.popup_message);
            popup.setText("Are you sure= you want to delete "+name+" from your friends list?");
            builder.setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    Uri uri = FriendsContract.Friend.BuildFriendUri(String.valueOf(_id));
                    contentResolver.delete(uri,null,null);
                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }else if(command.equals(DELETE_DATABASE)){
            TextView popup = (TextView) view.findViewById(R.id.popup_message);
            popup.setText("Are you sure= you want to delete "+name+" from your friends list?");
            builder.setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    Uri uri = FriendsContract.URI_TABLE;
                    contentResolver.delete(uri,null,null);
                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }
        return builder.create();

    }
}
