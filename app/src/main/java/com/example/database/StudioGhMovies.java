package com.example.database;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class StudioGhMovies implements Parcelable {

    private String id;
    private String title;
    private String originalTitle;
    private String originalTitleRomanticised;
    private String description;
    private String director;
    private String producer;
    private int releaseDate;
    private int runningTime;
    private int rtScore;
    ArrayList<String> peopleUrl;


    public StudioGhMovies (String id, String title, String originalTitle, String originalTitleRomanticised,
                          String description, String director, String producer, int releaseDate, int runningTime, int rtScore, ArrayList<String> peopleUrl) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.originalTitleRomanticised = originalTitleRomanticised;
        this.description = description;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.rtScore = rtScore;
        this.peopleUrl = peopleUrl;
    }

    protected StudioGhMovies(Parcel in) {
        id = in.readString();
        title = in.readString();
        originalTitle = in.readString();
        originalTitleRomanticised = in.readString();
        description = in.readString();
        director = in.readString();
        producer = in.readString();
        releaseDate = in.readInt();
        runningTime = in.readInt();
        rtScore = in.readInt();
        peopleUrl = new ArrayList<>();
        in.readStringList(peopleUrl);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(originalTitle);
        dest.writeString(originalTitleRomanticised);
        dest.writeString(description);
        dest.writeString(director);
        dest.writeString(producer);
        dest.writeInt(releaseDate);
        dest.writeInt(runningTime);
        dest.writeInt(rtScore);
        dest.writeStringList(peopleUrl);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudioGhMovies> CREATOR = new Creator<StudioGhMovies>() {
        @Override
        public StudioGhMovies createFromParcel(Parcel in) {
            return new StudioGhMovies(in);
        }

        @Override
        public StudioGhMovies[] newArray(int size) {
            return new StudioGhMovies[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalTitleRomanticised() {
        return originalTitleRomanticised;
    }

    public void setOriginalTitleRomanticised(String originalTitleRomanised) {
        this.originalTitleRomanticised = originalTitleRomanised;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getRtScore() {
        return rtScore;
    }

    public void setRtScore(int rtScore) {
        this.rtScore = rtScore;
    }

    public ArrayList<String> getPeopleUrl() {
        return peopleUrl;
    }

    public void setPeopleUrl(ArrayList<String> peopleUrl) {
        this.peopleUrl = peopleUrl;
    }
}