package com.example.jamesatkin.monies.activities;

public class TypeIcon {
    private int id;
    private String drawablePath;

    public TypeIcon(int id, String drawablePath) {
        this.id = id;
        this.drawablePath = drawablePath;
    }

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
}
