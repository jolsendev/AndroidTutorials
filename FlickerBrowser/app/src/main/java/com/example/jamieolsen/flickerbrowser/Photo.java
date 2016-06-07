package com.example.jamieolsen.flickerbrowser;

/**
 * Created by Jamie Olsen on 6/6/2016.
 */
public class Photo {
    private String mTitle;
    private String mAuthor;
    private String mAuthorId;
    private String mLink;
    private String mTabs;
    private String mImage;

    public Photo(String mImage, String mTitle, String mAuthor, String mAuthorId, String mLink, String mTabs) {

        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mAuthorId = mAuthorId;
        this.mLink = mLink;
        this.mTabs = mTabs;
    }
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmAuthorId() {
        return mAuthorId;
    }

    public void setmAuthorId(String mAuthorId) {
        this.mAuthorId = mAuthorId;
    }

    public String getmLink() {
        return mLink;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }

    public String getmTabs() {
        return mTabs;
    }

    public void setmTabs(String mTabs) {
        this.mTabs = mTabs;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mAuthorId='" + mAuthorId + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mTabs='" + mTabs + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
