package com.example.jamieolsen.top10downloader;

/**
 * Created by Jamie Olsen on 5/27/2016.
 */
public class Application {
    private String name;
    private String artist;
    private String releaseDate;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + '\n' +
                "Artist: " + getArtist() + '\n' +
                "ReleaseDate: " + getReleaseDate();
    }
}
