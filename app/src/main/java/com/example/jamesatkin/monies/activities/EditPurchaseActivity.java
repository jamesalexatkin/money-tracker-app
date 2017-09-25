package com.example.jamesatkin.monies.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;

public class EditPurchaseActivity extends PurchaseActivity {

    private int id;
    private Purchase purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_purchase);

        Bundle b = getIntent().getExtras();
        purchase = b.getParcelable("Purchase");

        id = purchase.getId();

        initCostField();
        initTypeField();
        initDateField();

        EditText textView = (EditText) findViewById(R.id.txt_Name);
        textView.setText(purchase.getName());

        textView = (EditText) findViewById(R.id.txt_Cost);
        textView.setText(purchase.getCostAsString());

        textView = (EditText) findViewById(R.id.txt_Date);
        textView.setText(purchase.getDateAsString("standard"));

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
        purchase = super.readFields(id);
        MainActivity.db.updatePurchase(purchase);

        Toast.makeText(getApplicationContext(), "Purchase updated!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onDeleteClicked(View view) {
        //Put up the Yes/No message box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Delete purchase")
                .setMessage("Delete this purchase?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.db.deletePurchase(purchase);
                        //Yes button clicked, do something
                        Toast.makeText(EditPurchaseActivity.this, "Purchase deleted!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                // Do nothing when no is clicked
                .setNegativeButton("No", null)
                .show();
    }
}
