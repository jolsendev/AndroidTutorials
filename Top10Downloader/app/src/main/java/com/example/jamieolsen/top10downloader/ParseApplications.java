package com.example.jamieolsen.top10downloader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Jamie Olsen on 5/27/2016.
 */
public class ParseApplications {


    private String xmlData;
    private ArrayList<Application> applications;

    public ParseApplications(String data) {
        applications = new ArrayList<>();
        this.xmlData = data;
    }

    public ArrayList<Application> getApplications() {

        return applications;
    }

    public Boolean process(){
        boolean status = true;
        Application currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            XmlPullParser xpp = parserFactory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();

                switch(eventType){
                    case XmlPullParser.START_TAG:
                        if(tagName.equalsIgnoreCase("entry")){
                            inEntry = true;
                            currentRecord = new Application();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if(tagName.equalsIgnoreCase("entry")){
                                applications.add(currentRecord);
                                inEntry = false;
                            }else if(tagName.equalsIgnoreCase("name")){
                                currentRecord.setName(textValue);
                            }else if(tagName.equalsIgnoreCase("artist")){
                                currentRecord.setArtist(textValue);
                            }else if (tagName.equalsIgnoreCase("releaseDate")){
                                currentRecord.setReleaseDate(textValue);
                            }
                        }
                    default:
                        //Nothing else to do
                }
                eventType = xpp.next();
            }


        }catch (Exception e){
            status = false;
            e.printStackTrace();
        }

        for (Application app: applications) {
            Log.d("ParseApplications", "***************");
            Log.d("ParseApplications", "Name: "+app.getName());
            Log.d("ParseApplications", "Artist: "+app.getArtist());
            Log.d("ParseApplications", "ReleaseDate: "+app.getReleaseDate());

        }
        return true;

    }

}
