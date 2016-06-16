package com.example.jamieolsen.flickerbrowser;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by a5w5nzz on 6/8/2016.
 */
public class GetFlickerJsonData extends GetRawData {

    private String LOG_TAG = GetFlickerJsonData.class.getSimpleName();
    private List<Photo> mPhoto;
    private Uri mDestinationUri;


    public GetFlickerJsonData(String searchCriteria, boolean matchAll) {
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
    }

    public void execute(){
        super.setmRawURL(mDestinationUri.toString());
        DownLoadJsonData downLoadJsonData = new DownLoadJsonData();
        Log.v(LOG_TAG,"Built URI = "+mDestinationUri.toString());
        //downLoadJsonData.execute()
    }
    private boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {
        final String FLICKER_BASE_URI = "https://api.flickr.com/services/feeds/photos_public.gne";
        final String TAGS_PARAM = "tags";
        final String TAGMODE_PARAM = "tagmode";
        final String FORMAT = "format";
        final String NO_JSON_CALLBACK_PARAM = "nojsoncallback";
        mDestinationUri = Uri.parse(FLICKER_BASE_URI).buildUpon()
                .appendQueryParameter(TAGS_PARAM, searchCriteria)
                .appendQueryParameter(TAGMODE_PARAM, matchAll ? "ALL":"ANY")
                .appendQueryParameter(FORMAT, "json")
                .appendQueryParameter(NO_JSON_CALLBACK_PARAM, "1")
                .build();

        return mDestinationUri != null;
    }

    public List<Photo> getMPhotos(){
        return mPhoto;
    }

    public void processResult() {

        if(getmDownloadStatus() != DownloadStatus.OK){
            Log.e(LOG_TAG, "Error in downloading data");
            return;
        }

        final String FLICKER_ITEMS = "items";
        final String FLICKER_TITLE = "title";
        final String FLICKER_MEDIA = "media";
        final String FLICKER_PHOTO_URL = "m";
        final String FLICKER_AUTHOR = "author";
        final String FLICKER_AUTHOR_ID = "author_id";
        final String FLICKER_LINK = "link";
        final String FLICKER_TAGS = "tags";


        try{
            JSONObject jsonObject = new JSONObject(getmData());
            JSONArray itemArray = jsonObject.getJSONArray(FLICKER_ITEMS);
            for(int i = 0; i< itemArray.length(); i++){
                JSONObject jsonPhoto = itemArray.getJSONObject(i);
                String title = jsonPhoto.getString(FLICKER_TITLE);
                String author = jsonPhoto.getString(FLICKER_AUTHOR);
                String authorId = jsonPhoto.getString(FLICKER_AUTHOR_ID);
                String link = jsonPhoto.getString(FLICKER_LINK);
                String tags = jsonPhoto.getString(FLICKER_TAGS);
                JSONObject jsonMedia = jsonPhoto.getJSONObject(FLICKER_MEDIA);
                String photoUrl = jsonMedia.getString(FLICKER_PHOTO_URL);

                Photo photoObject = new Photo(title, author, authorId,link,tags, photoUrl);
                this.mPhoto.add(photoObject);
            }

            for(Photo singlePhoto : mPhoto){
                Log.e(LOG_TAG, singlePhoto.toString());
            }
        }catch(JSONException je){
            je.printStackTrace();
            Log.e(LOG_TAG, "Error processing json data");
        }


    }

    public class DownLoadJsonData extends DownloadRawData{
        protected void onPostExecute(String webData){
            super.onPostExecute(webData);
            processResult();
        }

        protected String doInBackground(String... params){
            String[] par = {mDestinationUri.toString()};
            return super.doInBackground(par);
        }

    }
}
