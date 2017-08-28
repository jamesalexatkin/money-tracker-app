package com.example.jamesatkin.monies;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddPurchaseActivity extends AppCompatActivity {

    private String name = "";
    private float cost = 0.0f;
    private Date date = new Date();
    private String type = "";
    private boolean luxury = false;
    private String place = "";
    private String comment = "";

    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);

        // Add listener to Cost field to make it behave correctly
        EditText costField = (EditText) findViewById(R.id.txt_Cost);
        costField.addTextChangedListener(new MoneyTextWatcher(costField));
        costField.setText("£0.00");

        //WHY IS THIS NOT WORKING????????????????
        radioButton = (RadioButton) findViewById(R.id.radio_luxury);
        radioButton.setChecked(false);
    }

    public void onRadioLuxuryClicked(View view) {
        // Toggles value
        luxury = !luxury;
        radioButton.toggle();
    }

    public void onFinishClicked(View view) {
        //readFields();
        //validateFields();

        // Add to database
        Purchase purchase = readFields();
        MainActivity.db.addPurchase(purchase);

        //Display confirmation message
        Toast.makeText(getApplicationContext(), "@string/message_purchaseComplete", Toast.LENGTH_SHORT).show();
    }

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

//    Calendar myCalendar = Calendar.getInstance();
//
//    DatePickerDialog.OnDateSetListener dateDialog = new DatePickerDialog.OnDateSetListener() {
//
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//            // TODO Auto-generated method stub
//            myCalendar.set(Calendar.YEAR, year);
//            myCalendar.set(Calendar.MONTH, monthOfYear);
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updateLabel();
//        }
//
//    };
//    EditText dateEditText = (EditText) findViewById(R.id.txt_Date);
//    dateEditText.setOnClickListener(new   OnClickListener() {
//        @Override
//        public void onClick (View v){
//            // TODO Auto-generated method stub
//            new DatePickerDialog(classname.this, date, myCalendar
//                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//        }
//    });
//
//    private void updateLabel() {
//
//        String myFormat = "MM/dd/yy"; //In which you need put here
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//
//        edittext.setText(sdf.format(myCalendar.getTime()));
//    }
}