package com.example.jamesatkin.monies.activities;


import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;

public class EditPurchaseActivity extends PurchaseActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getActionBar().setTitle("Edit Purchase");

        Bundle b = getIntent().getExtras();
        Purchase purchase = b.getParcelable("Purchase");

        id = purchase.getId();

        EditText textView = (EditText) findViewById(R.id.txt_Name);
        textView.setText(purchase.getName());

        textView = (EditText) findViewById(R.id.txt_Cost);
        textView.setText(purchase.getCostAsString());

        textView = (EditText) findViewById(R.id.txt_Date);
        textView.setText(purchase.getDateAsString("standard"));

//        textView = (EditText) findViewById(R.id.txt_Type);
//        textView.setText(purchase.getType());
        Spinner spinner = (Spinner) findViewById(R.id.spinner_Type);
        // Searches for the right type so as the id can be set properly
        spinner.setSelection(purchase.getType());

        textView = (EditText) findViewById(R.id.txt_Place);
        textView.setText(purchase.getPlace());

        textView = (EditText) findViewById(R.id.txt_Comment);
        textView.setText(purchase.getComment());
    }

    @Override
    public void onFinishClicked(View view) {
        Purchase purchase = super.readFields(id);
        MainActivity.db.updatePurchase(purchase);

        Toast.makeText(getApplicationContext(), "Purchase updated!", Toast.LENGTH_SHORT).show();
    }
}
