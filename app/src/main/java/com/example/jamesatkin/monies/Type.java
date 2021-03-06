package com.example.jamesatkin.monies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import static android.R.attr.icon;
import static android.R.attr.type;

public class Type implements Parcelable {
    private int id;
    private String name;
    private int iconId;
    private boolean luxury;

    public Type() {    }

    public Type(int id, String name, int iconId, Boolean luxury) {
        this.id = id;
        this.name = name;
        this.iconId = iconId;
        this.luxury = luxury;
    }

    public Type(Parcel src) {
        this.id = src.readInt();
        this.name = src.readString();
        this.iconId = src.readInt();
        // luxury == true if byte != 0
        this.luxury = src.readByte() != 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(iconId);
        // If luxury == true, byte == 1
        dest.writeByte((byte) (luxury ? 1 : 0));
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Type createFromParcel(Parcel in) {
            return new Type(in);
        }

        public Type[] newArray(int size) {
            return new Type[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public boolean getLuxury() {
        return luxury;
    }

    public void setLuxury(boolean luxury) {
        this.luxury = luxury;
    }


}
