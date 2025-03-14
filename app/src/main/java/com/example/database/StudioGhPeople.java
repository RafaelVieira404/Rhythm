package com.example.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class StudioGhPeople implements Parcelable {

    private String id;
    private String name;
    private String gender;
    private String age;
    private String eye_color;
    private String hair_color;
    private String[] films;
    private String species;
    private String url;

    public StudioGhPeople(String id, String name, String gender, String age, String eye_color, String hair_color, String[] films,  String species, String url) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.eye_color = eye_color;
        this.hair_color = hair_color;
        this.films = films;
        this.species = species;
        this.url = url;

    }

    public StudioGhPeople(String id, String name, String gender, int age, String eye_color, String hair_color, String[] films,  String species, String url) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = String.valueOf(age);
        this.eye_color = eye_color;
        this.hair_color = hair_color;
        this.films = films;
        this.species = species;
        this.url = url;

    }

    protected StudioGhPeople(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.gender = in.readString();
        this.age = in.readString();
        this.eye_color = in.readString();
        this.hair_color = in.readString();
    }

    public static final Creator<StudioGhPeople> CREATOR = new Creator<StudioGhPeople>() {
        @Override
        public StudioGhPeople createFromParcel(Parcel in) {
            return new StudioGhPeople(in);
        }

        @Override
        public StudioGhPeople[] newArray(int size) {
            return new StudioGhPeople[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(age);
        dest.writeString(eye_color);
        dest.writeString(hair_color);
        dest.writeStringList(Arrays.asList(films));
        dest.writeString(species);
        dest.writeString(url);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEye_color() {
        return eye_color;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String setInfoText(String gender, String age, String hairColor, String eyeColor) {
        String string = String.format("Gender: %s" + "\n" + "Age: %d" + "\n" + "HairColor: %s" + "\n" + "EyeColor: %s" + "\n", gender, age, hairColor, eyeColor );
        return string;
    }

    public String[] getFilms() {
        return films;
    }

    public void setFilms(String[] films) {
        this.films = films;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
