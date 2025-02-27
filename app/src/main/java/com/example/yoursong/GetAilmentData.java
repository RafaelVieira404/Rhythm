package com.example.yoursong;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GetAilmentData implements Parcelable {

    private int id;
    private String name;
    private String description;
//    private Recovery recovery;
//    private Protection protection;

    public GetAilmentData(int id, String name, String description /*, Recovery recovery, Protection protection*/) {
        this.id = id;
        this.name = name;
        this.description = description;
//        this.recovery = recovery;
//        this.protection = protection;
    }

    protected GetAilmentData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
//        recovery = in.readParcelable()
//        recovery = in.readParcelable()
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName() {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GetAilmentData> CREATOR = new Creator<GetAilmentData>() {
        @Override
        public GetAilmentData createFromParcel(Parcel in) {
            return new GetAilmentData(in);
        }

        @Override
        public GetAilmentData[] newArray(int size) {
            return new GetAilmentData[size];
        }
    };



}