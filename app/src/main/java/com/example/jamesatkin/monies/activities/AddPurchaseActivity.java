package com.example.jamesatkin.monies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.util.Date;

public class AddPurchaseActivity extends PurchaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = "";
        cost = 0.0f;
        date = new Date();
        type = 0;
        place = "";
        comment = "";
    }

    public void onFinishClicked(View view) {
        // Add to database
        Purchase purchase = super.readFields(MainActivity.purchaseIdCount++);
        MainActivity.db.addPurchase(purchase);

        //Display confirmation message
        Toast.makeText(getApplicationContext(), "Purchase added!", Toast.LENGTH_SHORT).show();
        finish();
    }
}