package com.example.jamieolsen.flickerbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Jamie Olsen on 6/1/2016.
 */
enum DownloadStatus {IDLE, PROCESSING, NOT_INSTALLED, FAILED_OR_EMPTY, OK}
public class GetRawData {
    private String LOG_TAG = GetRawData.class.getSimpleName();
    private String mRawURL;
    private String mData;
    private DownloadStatus mDownloadStatus;

    public GetRawData(String mRawURL) {
        this.mRawURL = mRawURL;
        this.mDownloadStatus = DownloadStatus.IDLE;
    }

    public void reset(){
        this.mDownloadStatus = null;
        this.mRawURL = null;
        this.mData = null;
    }

    public DownloadStatus getmDownloadStatus() {
        return mDownloadStatus;
    }

    public String getmData() {
        return mData;
    }

    public void execute(){
        this.mDownloadStatus = DownloadStatus.PROCESSING;
        DownloadRawData downloadRawData = new DownloadRawData();
        downloadRawData.execute(mRawURL);
    }
    public class DownloadRawData extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            if(params == null){
                return null;
            }

            try{
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if(inputStream == null){
                    return null;
                }
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine())!= null){
                    buffer.append(line+"\n");
                }
                return buffer.toString();
            }catch(IOException e){
                Log.e(LOG_TAG, "ERROR", e);
                return null;
            } finally{
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
                if(reader != null){
                    try{
                        reader.close();
                    }catch(final IOException e){
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
                return "";
            }
        }

        protected void onPostExecute(String webData){
            mData = webData;
            Log.e(LOG_TAG, "Data returned was: "+mData);
            if (mData == null) {
                if(mRawURL == null){
                    mDownloadStatus = DownloadStatus.NOT_INSTALLED;
                }else{
                    mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
                }
            }else{
                mDownloadStatus = DownloadStatus.OK;
            }
        }
    }
}
