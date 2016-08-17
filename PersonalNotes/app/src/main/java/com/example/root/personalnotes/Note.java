package com.example.root.personalnotes;

import android.graphics.Bitmap;

/**
 * Created by root on 8/17/16.
 */
public class Note {
    private String mTitle, mDescription, mDate, mTime, mImagePath, mType;
    private int mId;
    private boolean mHasNoImage = false;
    private int mStorageSelection;

    private Bitmap mBitmap;

    public Note(String mTitle, String mDescription, String mDate, String mTime, int mId, int mStorageSelection, String mType) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mId = mId;
        this.mStorageSelection = mStorageSelection;
        this.mType = mType;
    }

    // Create note from a reminderString (contains contents in a single string
    // delimited by a $ sign - see convertToString method later which
    // creates this.
    public Note(String reminderString) {
        // using \\ before a character tells the function
        // to NOT treat the character as a special regular expression
        // $ is normally interpreted as end of line or end of string
        String[] fields = reminderString.split("\\$");
        this.mType = fields[0];
        this.mId = Integer.parseInt(fields[1]);
        this.mTitle = fields[2];
        this.mDate = fields[5];
        this.mTime = fields[3];
        this.mImagePath = fields[4];
        this.mStorageSelection = Integer.parseInt(fields[6]);
        if (mType.equals(AppConstant.NORMAL)) {
            this.mDescription = fields[7];
            Note aNote = new Note(this.mTitle, this.mDescription, this.mDate, this.mTime, this.mId, this.mStorageSelection, this.mType);
            // Previous constructor does not set this, so we do it manually after invoking
            // the constructor
            aNote.setImagePath(this.mImagePath);
        } else {
            String list = "";
            for(int i = 7;i<fields.length;i++)
                list = list+fields[i];
            this.mDescription = list;
        }
    }

    public String convertToString() {
        return mType + "$"
                + mId + "$"
                + mTitle + "$"
                + mTime + "$"
                + mImagePath + "$"
                + mDate + "$"
                + mStorageSelection + "$"
                + mDescription;
    }


}
