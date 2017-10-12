package com.example.jamesatkin.monies.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jamesatkin.monies.ImportExport;
import com.example.jamesatkin.monies.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onManageTypesClicked(View view) {
        Intent intent = new Intent(this, ManageTypesActivity.class);
        startActivity(intent);
    }

    public void onExportClicked(View view) {

        // CALL SOMETHING IN HERE TO CREATE FILE, THEN GET FILE PATH
        ImportExport.createFile(this);

        //  OR WHATEVER THE FILE IS CALLED
        String filename = "purchases.csv";
        File filelocation = new File(SettingsActivity.this.getFilesDir().getAbsolutePath(), filename);
        Uri path = Uri.fromFile(filelocation);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // Sets intent type to email
        emailIntent.setType("message/rfc822");
        String to[] = {"curlyw42@gmail.com"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        // Puts the attachment in the intent
        emailIntent.putExtra(Intent.EXTRA_STREAM, path);
        // Puts the subject in the intent
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Money Export");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void createDummy() {
        String s;
        try {
            String fileName = "testFileName.txt";
            File root = new File(Environment.getExternalStorageDirectory(), "testDir");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, fileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append("Testing email txt attachment.");
            writer.flush();
            writer.close();
            sendEmail(gpxfile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void sendEmail(String fileName){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT, "Test subject");
        i.putExtra(Intent.EXTRA_TEXT, "This is the body of the email");
        i.putExtra(Intent.EXTRA_STREAM, Uri.parse(fileName));
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
