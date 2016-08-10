package com.example.root.friends;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by root on 8/9/16.
 */
public class EditActivity extends FragmentActivity {
    private final String LOG_TAG = EditActivity.class.getSimpleName();
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

        contentResolver = EditActivity.this.getContentResolver();
        Intent intent = getIntent();
        final String _id = intent.getStringExtra(FriendsContract.Friend.FRIENDS_ID);
        String name = intent.getStringExtra(FriendsContract.FriendsColumns.FRIENDS_NAME);
        String email = intent.getStringExtra(FriendsContract.FriendsColumns.FRIENDS_EMAIL);
        String phone = intent.getStringExtra(FriendsContract.FriendsColumns.FRIENDS_PHONENUMBER);

        mNameTextView.setText(name);
        mEmailTextView.setText(email);
        mPhoneTextView.setText(phone);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(FriendsContract.FriendsColumns.FRIENDS_NAME, mNameTextView.getText().toString());
                values.put(FriendsContract.FriendsColumns.FRIENDS_PHONENUMBER, mPhoneTextView.getText().toString());
                values.put(FriendsContract.FriendsColumns.FRIENDS_EMAIL, mEmailTextView.getText().toString());
                //send the id we are working on
                Uri uri = FriendsContract.Friend.buildFriendUri(_id);
                //populate the fields on the screen
                int recordsUpdated = contentResolver.update(uri, values, null, null);
                Log.d(LOG_TAG, "Number of records that were updated: "+recordsUpdated);
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return true;
    }
}
