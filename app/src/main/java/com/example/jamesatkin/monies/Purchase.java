package com.example.jamesatkin.monies;

import java.util.Date;

class Purchase {
    private String name;
    private float cost;
    private Date date;
    private String type;
    private String place;
    private boolean luxury;
    private String comment;

    public Purchase(String name, float cost, Date date, String type, String place, boolean luxury, String comment) {
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.type = type;
        this.place = place;
        this.luxury = luxury;
        this.comment = comment;
    }
}
