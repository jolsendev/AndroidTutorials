package com.example.jamieolsen.flickerbrowser;

import android.net.Uri;
import android.util.Log;

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

    public void processResult() {

        if(getmDownloadStatus() != DownloadStatus.OK){
            Log.e(LOG_TAG, "Error in downloading data");
            return;
        }

    }



    public class DownLoadJsonData extends DownloadRawData{
        protected void onPostExecute(String webData){
            super.onPostExecute(webData);
            processResult();
        }

        protected String doInBackground(String... params){
            return super.doInBackground(params);
        }
    }
}
