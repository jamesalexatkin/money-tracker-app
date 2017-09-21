package com.example.jamesatkin.monies.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jamesatkin.monies.MoneyTextWatcher;
import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class PurchaseActivity extends AppCompatActivity {
    protected String name;
    protected float cost;
    protected Date date;
    protected int type;
    protected String place;
    protected String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        // Add listener to Cost field to make it behave correctly
        EditText costField = (EditText) findViewById(R.id.txt_Cost);
        costField.addTextChangedListener(new MoneyTextWatcher(costField));
        costField.setText("£0.00");


        // Set up spinner
        Spinner dropdown = (Spinner)findViewById(R.id.spinner_Type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MainActivity.typeNames);
        dropdown.setAdapter(adapter);
    }

    public abstract void onFinishClicked(View view);

    public Purchase readFields(int id) {
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
        Spinner spinner = (Spinner) findViewById(R.id.spinner_Type);
        content = spinner.getSelectedItem().toString();

        type = MainActivity.getTypeId(content);

        // Place
        textField = (EditText) findViewById(R.id.txt_Place);
        content = textField.getText().toString();
        place = content;

        // Comment
        textField = (EditText) findViewById(R.id.txt_Comment);
        content = textField.getText().toString();
        comment = content;


        Purchase purchase = new Purchase(id, name, cost, date, type, place, comment);
        return purchase;
    }
}
