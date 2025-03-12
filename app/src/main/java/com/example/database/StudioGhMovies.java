package com.example.database;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class StudioGhMovies implements Parcelable {

    private String id;
    private String title;
    private String original_title;
    private String original_title_romanised;
    private String image;
    private String movie_banner;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private int running_time;
    private String rt_score;
    ArrayList<String> people;


    public StudioGhMovies (String id, String title, String original_title, String original_title_romanised,String image, String movie_banner,
                          String description, String director, String producer, String release_date, int running_time, String rt_score, ArrayList<String> people) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.original_title_romanised = original_title_romanised;
        this.image = image;
        this.movie_banner = movie_banner;
        this.description = description;
        this.director = director;
        this.producer = producer;
        this.release_date = release_date;
        this.running_time = running_time;
        this.rt_score = rt_score;
        this.people = people;
    }

    protected StudioGhMovies(Parcel in) {
        id = in.readString();
        title = in.readString();
        original_title = in.readString();
        original_title_romanised = in.readString();
        image = in.readString();
        movie_banner = in.readString();
        description = in.readString();
        director = in.readString();
        producer = in.readString();
        release_date = in.readString();
        running_time = in.readInt();
        rt_score = in.readString();
        people = new ArrayList<>();
        in.readStringList(people);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(original_title);
        dest.writeString(original_title_romanised);
        dest.writeString(image);
        dest.writeString(movie_banner);
        dest.writeString(description);
        dest.writeString(director);
        dest.writeString(producer);
        dest.writeString(release_date);
        dest.writeInt(running_time);
        dest.writeString(rt_score);
        dest.writeStringList(people);
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

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_title_romanised() {
        return original_title_romanised;
    }

    public void setOriginal_title_romanised(String originalTitleRomanised) {
        this.original_title_romanised = originalTitleRomanised;
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
        this.director = director.substring(0, 1).toUpperCase();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer.substring(0, 1).toUpperCase();
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRunning_time() {
        return running_time;
    }

    public void setRunning_time(int running_time) {
        this.running_time = running_time;
    }

    public String getRt_score() {
        return rt_score;
    }

    public void setRt_score(String rt_score) {
        this.rt_score = rt_score;
    }

    public ArrayList<String> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<String> people) {
        this.people = people;
    }

    public String getMovie_banner() {
        return movie_banner;
    }

    public void setMovie_banner(String movie_banner) {
        this.movie_banner = movie_banner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String setInfoText(String releaseDate, int runningTime, String rtScore) {
        String string = String.format("Release: %s    Time: %d  \nScoreRT: ", releaseDate, runningTime) + rtScore + "%";
        return string;
    }
    public String setDescriptionText(String description) {
        int end = description.length();
        String string;
        if (end > 100) {
            string = String.format("Description:" + " %s...", description.substring(0, 100));
        } else {
            string = String.format("Description:" + "%s", description.substring(0, end));
        }
        return string;

    }
}