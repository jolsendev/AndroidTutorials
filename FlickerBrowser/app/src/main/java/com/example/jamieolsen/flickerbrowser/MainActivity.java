package com.example.jamieolsen.flickerbrowser;

import android.support.v7.app.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        GetRawData theRawData = new GetRawData("https://www.flickr.com/services/feeds/photos_public.gne?tags=android,lollipop&format=json&nojsoncallback=1");
        GetFlickerJsonData theRawData = new GetFlickerJsonData("android,lollipop", true);
        theRawData.execute();
    }
}
