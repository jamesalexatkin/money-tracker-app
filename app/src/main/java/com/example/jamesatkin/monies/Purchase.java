package com.example.jamesatkin.monies;

import java.util.Date;

class Purchase {
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
}
