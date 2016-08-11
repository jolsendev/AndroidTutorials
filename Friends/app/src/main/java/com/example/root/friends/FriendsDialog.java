package com.example.root.friends;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by root on 8/8/16.
 */
public class FriendsDialog extends android.support.v4.app.DialogFragment {
    private final static String LOG_TAG = FriendsDialog.class.getSimpleName();
    private LayoutInflater mLayoutInflater;
    public final static String DIALOG_TYPE = "command";
    public final static String DELETE_RECORD= "deleteRecord";
    public final static String DELETE_DATABASE= "deleteDatabase";
    public final static String CONFIRM_EXIT = "confirmExit";

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
            popup.setText("Are you sure you want to delete "+name+" from your friends list?");
            builder.setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Deleting record "+_id, Toast.LENGTH_LONG).show();
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    Uri uri = FriendsContract.Friend.buildFriendUri(String.valueOf(_id));
                    contentResolver.delete(uri,null,null);
                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }else if(command.equals(DELETE_DATABASE)){
            TextView popup = (TextView) view.findViewById(R.id.popup_message);
            popup.setText("Are you sure= you want to delete from your friends list?");
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
        }else if(command.equals(CONFIRM_EXIT)){
            TextView popup = (TextView) view.findViewById(R.id.popup_message);
            popup.setText("Are you sure you want to exit without saving");
            builder.setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getActivity().finish();
                }
            });
        }else{
            Log.d(LOG_TAG, "Invalid command pass parameter");
        }
        return builder.create();

    }
}
