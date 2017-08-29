package com.example.jamesatkin.monies.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by curly on 29/08/2017.
 */

public class PurchaseActivity extends AppCompatActivity {
    private String name;
    private float cost;
    private Date date;
    private String type;
    private boolean luxury;
    private String place;
    private String comment;

    private EditText textField = (EditText) findViewById(R.id.txt_Name);
    private

    RadioButton radioButton;





    private Purchase readFields() {
        // Name
        EditText textField = (EditText) findViewById(R.id.txt_Name);
        String content = textField.getText().toString();
        name = content;

        // Cost
        textField = (EditText) findViewById(R.id.txt_Cost);
        content = textField.getText().toString();
        String cleanString = content.toString().replaceAll("[£]", "");
        cost = Float.parseFloat(cleanString);

        // Date
        textField = (EditText) findViewById(R.id.txt_Date);
        content = textField.getText().toString();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(content);
        } catch (ParseException e) {
            //do something with exception
        }

        // Type
        textField = (EditText) findViewById(R.id.txt_Type);
        content = textField.getText().toString();
        type = content;

        // No need to set luxury as that is done on radio button click

        // Place
        textField = (EditText) findViewById(R.id.txt_Place);
        content = textField.getText().toString();
        place = content;

        // Comment
        textField = (EditText) findViewById(R.id.txt_Comment);
        content = textField.getText().toString();
        comment = content;



        Purchase purchase = new Purchase(MainActivity.idCount++, name, cost, date, type, place, luxury, comment);
        return purchase;
    }

}
