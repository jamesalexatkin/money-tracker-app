package com.example.jamesatkin.monies;

import android.os.Parcel;
import android.os.Parcelable;

public class TypeIcon implements Parcelable {
    private int id;
    private String drawablePath;

    public TypeIcon(int id, String drawablePath) {
        this.id = id;
        this.drawablePath = drawablePath;
    }

    public TypeIcon(Parcel src) {
        this.id = src.readInt();
        this.drawablePath = src.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(drawablePath);
    }

    public static final Creator<TypeIcon> CREATOR = new Creator<TypeIcon>() {
        @Override
        public TypeIcon createFromParcel(Parcel in) {
            return new TypeIcon(in);
        }

        @Override
        public TypeIcon[] newArray(int size) {
            return new TypeIcon[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrawablePath() {
        return drawablePath;
    }

    public void setDrawablePath(String drawablePath) {
        this.drawablePath = drawablePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
