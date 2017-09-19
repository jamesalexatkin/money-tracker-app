package com.example.jamesatkin.monies;

public class Type {
    private int id;
    private String name;
    private boolean luxury;

    public Type() {    }

    public Type(int id, String name, Boolean luxury) {
        this.id = id;
        this.name = name;
        this.luxury = luxury;
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

    public boolean getLuxury() {
        return luxury;
    }

    public void setLuxury(boolean luxury) {
        this.luxury = luxury;
    }
}
