package com.example.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class StudioGhPeople implements Parcelable {

    private String id;
    private String name;
    private String gender;
    private int age;
    private String eyeColor;
    private String hairColor;

    public StudioGhPeople(String id, String name, String gender, int age, String eyeColor, String hairColor) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    protected StudioGhPeople(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.gender = in.readString();
        this.age = in.readInt();
        this.eyeColor = in.readString();
        this.hairColor = in.readString();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String setInfoText(String gender, int age, String hairColor, String eyeColor) {
        String string = String.format("Gender: %s" + "\n" + "Age: %d" + "\n" + "HairColor: %s" + "\n" + "EyeColor: %s" + "\n", gender, age, hairColor, eyeColor );
        return string;
    }
}
