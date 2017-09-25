package com.example.jamesatkin.monies.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jamesatkin.monies.MoneyTextWatcher;
import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class PurchaseActivity extends AppCompatActivity {
    protected String name;
    protected float cost;
    protected Date date;
    protected int type;
    protected String place;
    protected String comment;

    private DatePicker datePicker;
    private Calendar calendar;
    private EditText dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        initCostField();
        initTypeField();
        initDateField();



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


    @SuppressWarnings("deprecation")
    protected void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    dateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    updateDate(arg1, arg2+1, arg3);
                }
            };

    private void updateDate(int year, int month, int day) {
        String dayString = padWithZero(day);
        String monthString = padWithZero(month);
        dateView.setText(dayString + "/" + monthString + "/" + year);
    }

    private String padWithZero(int num) {
        if (num < 10) {
            return "0" + num;
        }
        else return String.valueOf(num);
    }

    protected void initCostField() {
        // Add listener to Cost field to make it behave correctly
        EditText costField = (EditText) findViewById(R.id.txt_Cost);
        costField.addTextChangedListener(new MoneyTextWatcher(costField));
        costField.setText("£0.00");
    }

    protected void initTypeField() {
        // Set up spinner
        Spinner dropdown = (Spinner)findViewById(R.id.spinner_Type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MainActivity.getTypeNames());
        dropdown.setAdapter(adapter);
    }

    protected void initDateField() {
        dateView = (EditText) findViewById(R.id.txt_Date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        updateDate(year, month+1, day);

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });
    }
}
