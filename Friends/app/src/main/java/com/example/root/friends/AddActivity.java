package com.example.root.friends;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by root on 8/9/16.
 */
public class AddActivity extends FragmentActivity {
    private final String LOG_TAG = AddActivity.class.getSimpleName();
    TextView mNameTextView, mEmailTextView, mPhoneTextView;
    Button mButton;
    private ContentResolver contentResolver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mNameTextView = (TextView) findViewById(R.id.friendName);
        mEmailTextView = (TextView) findViewById(R.id.friendEmail);
        mPhoneTextView = (TextView) findViewById(R.id.friendPhone);
        mButton = (Button) findViewById(R.id.saveButton);

        contentResolver = AddActivity.this.getContentResolver();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()){
                    ContentValues values = new ContentValues();
                    values.put(FriendsContract.FriendsColumns.FRIENDS_NAME, mNameTextView.getText().toString());
                    values.put(FriendsContract.FriendsColumns.FRIENDS_PHONENUMBER, mPhoneTextView.getText().toString());
                    values.put(FriendsContract.FriendsColumns.FRIENDS_EMAIL, mEmailTextView.getText().toString());

                    Uri returned = contentResolver.insert(FriendsContract.URI_TABLE, values);

                    Log.d(LOG_TAG, "record that was returned was: "+returned.toString());

                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Make sure you have entered valid data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean isValid(){
        if(mNameTextView.getText().toString().length() == 0 || mEmailTextView.getText().toString().length() == 0 || mPhoneTextView.getText().toString().length() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean someDataEntered(){
        if(mNameTextView.getText().toString().length() > 0 || mEmailTextView.getText().toString().length() > 0 || mPhoneTextView.getText().toString().length() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if(someDataEntered()){
            FriendsDialog dialog = new FriendsDialog();
            Bundle bundle = new Bundle();
            bundle.putString(FriendsDialog.DIALOG_TYPE, FriendsDialog.CONFIRM_EXIT);
            dialog.setArguments(bundle);
            dialog.show(getSupportFragmentManager(), "Confirm exit");
        }else{
            super.onBackPressed();
        }
    }
}
