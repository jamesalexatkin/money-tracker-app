package com.example.jamesatkin.monies;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.jamesatkin.monies.activities.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.R.attr.data;
import static android.R.attr.lines;
import static com.example.jamesatkin.monies.activities.MainActivity.db;

public class ImportExport {

    public static void createFile(Context context) {
        // Generate file
        ArrayList<Purchase> purchases = MainActivity.db.getAllPurchases();
        String fileContents = "";
        for (int i = 0; i < purchases.size(); i++) {
            Purchase p = purchases.get(i);
            String newLine = "";
            newLine += p.getId() + ",";
            newLine += p.getName() + ",";
            newLine += p.getCost() + ",";
            newLine += p.getDate() + ",";
            newLine += p.getType() + ",";
            newLine += p.getPlace() + ",";
            newLine += p.getComment();
            fileContents += newLine + "\n";
        }

        // Write file to storage
        String filename = "purchases.csv";
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        try {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("purchases.csv", Context.MODE_PRIVATE));
//            outputStreamWriter.write(file);
//            outputStreamWriter.close();
//        }
//        catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
    }


}
