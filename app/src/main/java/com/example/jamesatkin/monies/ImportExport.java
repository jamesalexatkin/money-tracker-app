package com.example.jamesatkin.monies;


import com.example.jamesatkin.monies.activities.MainActivity;

import java.util.ArrayList;

import static com.example.jamesatkin.monies.activities.MainActivity.db;

public class ImportExport {

    public void createFile() {
        ArrayList<Purchase> purchases = MainActivity.db.getAllPurchases();
        String[] lines = new String[purchases.size()];
        for (int i = 0; i < lines.length; i++) {
            Purchase p = purchases.get(i);
            String newLine = "";
            newLine += p.getId() + ",";
            newLine += p.getName() + ",";
            newLine += p.getCost() + ",";
            newLine += p.getDate() + ",";
            newLine += p.getType() + ",";
            newLine += p.getPlace() + ",";
            newLine += p.getComment();
            lines[i] = newLine;
        }
    }
}
