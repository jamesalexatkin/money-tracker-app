package com.example.jamesatkin.monies;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.R.attr.format;

public class Purchase implements Parcelable {
    private int id;
    private String name;
    private float cost;
    private Date date;
    private String type;
    private String place;
    private boolean luxury;
    private String comment;

    public Purchase() {
    }

    public Purchase(int id, String name, float cost, Date date, String type, String place, boolean luxury, String comment) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.type = type;
        this.place = place;
        this.luxury = luxury;
        this.comment = comment;
    }

    public Purchase(Parcel src) {
        this.id = src.readInt();
        this.name = src.readString();
        this.cost = src.readFloat();
        // Reads date from long
        this.date = new Date(src.readLong());
        this.type = src.readString();
        this.place = src.readString();
        // luxury == true if byte != 0
        this.luxury = src.readByte() != 0;
        this.comment = src.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(cost);
        // Writes date as long, requires converting when read
        dest.writeLong(date.getTime());
        dest.writeString(type);
        dest.writeString(place);
        // If luxury == true, byte == 1
        dest.writeByte((byte) (luxury ? 1 : 0));
        dest.writeString(comment);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Purchase createFromParcel(Parcel in) {
            return new Purchase(in);
        }

        public Purchase[] newArray(int size) {
            return new Purchase[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getCost() {
        return cost;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public boolean getLuxury() {
        return luxury;
    }

    public String getPlace() {
        return place;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLuxury(boolean luxury) {
        this.luxury = luxury;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCostAsString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        return numberFormat.format(cost);
    }

    public String getDateAsString(String type) {
        switch (type) {
            case "standard" :
                return new SimpleDateFormat("dd/MM/yyyy").format(date);
            case "month" :
                return new SimpleDateFormat("dd MMM yyyy").format(date);
            default:
                return "##/##/####";
        }
    }
}
