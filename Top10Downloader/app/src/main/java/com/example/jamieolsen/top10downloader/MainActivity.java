package com.example.jamieolsen.top10downloader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class DownLoadData extends AsyncTask<String, Void, String>{

        private String mFileContent;
        @Override
        protected String doInBackground(String... params)
        {
            mFileContent = downloadXMLFile(params[0]);
            if(mFileContent == null){
                Log.d("DownLoadData","ERROR: downloading");
            }
            return mFileContent;
        }

        private String downloadXMLFile(String urlPath) {

            StringBuilder tempBuffer = new StringBuilder();
            try{
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection ) url.openConnection();
                int response = connection.getResponseCode();
                Log.d("DownLoadData", "The response code was "+response);
                InputStream is = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);

                int charRead;
                char[] inputBufer = new char[500];
                while(true){
                    charRead = reader.read();
                    if(charRead <= 0 ){
                        break;
                    }
                    tempBuffer.append(String.copyValueOf(inputBufer, 0, charRead));
                }
                return tempBuffer.toString();
            }catch(IOException e){
                Log.d("DownLoadData", "Message "+e.getMessage());
            }
            return null;
        }
    }
}
