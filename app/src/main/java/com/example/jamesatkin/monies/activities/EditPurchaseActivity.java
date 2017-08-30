package com.example.jamesatkin.monies.activities;


import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;

public class EditPurchaseActivity extends PurchaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getActionBar().setTitle("Edit Purchase");

        Bundle b = getIntent().getExtras();
        Purchase purchase = b.getParcelable("Purchase");

        EditText textView = (EditText) findViewById(R.id.txt_Name);
        textView.setText(purchase.getName());

        textView = (EditText) findViewById(R.id.txt_Cost);
        textView.setText(purchase.getCostAsString());

        textView = (EditText) findViewById(R.id.txt_Date);
        textView.setText(purchase.getDateAsString());

        textView = (EditText) findViewById(R.id.txt_Type);
        textView.setText(purchase.getType());

        RadioButton radio = (RadioButton) findViewById(R.id.radio_luxury);
        radio.setChecked(purchase.getLuxury());

        textView = (EditText) findViewById(R.id.txt_Place);
        textView.setText(purchase.getPlace());

        textView = (EditText) findViewById(R.id.txt_Comment);
        textView.setText(purchase.getComment());
    }

    @Override
    public void onFinishClicked(View view) {

    }
}
