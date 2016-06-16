package com.example.jamieolsen.flickerbrowser;

import android.app.Activity;
import android.drm.ProcessedData;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";
    private List<Photo> mPhotoList = new ArrayList<Photo>();
    private RecyclerView mRecyclerView;
    private FlickerRecylerViewAdaptor flickerRecylerViewAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProcessPhotos processPhotos = new ProcessPhotos("Android,lollipop", true);

//        GetRawData theRawData = new GetRawData("https://www.flickr.com/services/feeds/photos_public.gne?tags=android,lollipop&format=json&nojsoncallback=1");
        GetFlickerJsonData theRawData = new GetFlickerJsonData("android,lollipop", true);
        theRawData.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        return super.onCreateOptionsMenu(menu);
    }

    public class ProcessPhotos extends GetFlickerJsonData{
        public ProcessPhotos(String searchCriteria, boolean matchAll){
            super(searchCriteria, matchAll);
        }

        public void execute(){
            super.execute();
            ProcessedData processedData = new ProcessedData();
            processedData.execute();
        }

        public class ProcessedData extends DownLoadJsonData{
            protected void onPostExecute(String webData){
                super.onPostExecute(webData);
                flickerRecylerViewAdaptor = new FlickerRecylerViewAdaptor(MainActivity.this, getMPhotos());
                mRecyclerView.setAdapter(flickerRecylerViewAdaptor);
            }
        }
    }
}
