package com.example.jamesatkin.monies.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;

public class EditTypeActivity extends TypeActivity {

    Type type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_type);

        Bundle b = getIntent().getExtras();
        type = b.getParcelable("Type");

        id = type.getId();

        EditText textView = (EditText) findViewById(R.id.txt_Name);
        textView.setText(type.getName());

        checkBoxLuxury = (CheckBox) findViewById(R.id.checkbox_luxury);
        checkBoxLuxury.setChecked(type.getLuxury());
    }

    @Override
    public void onFinishClicked(View view) {
        Type type = super.readFields(id);
        MainActivity.db.updateType(type);
        MainActivity.updateTypeNames();

        Toast.makeText(getApplicationContext(), "Type updated!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onDeleteClicked(View view) {
        //Put up the Yes/No message box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Delete type")
                .setMessage("Delete this type?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.db.deleteType(type);
                        //Yes button clicked, do something
                        Toast.makeText(EditTypeActivity.this, "Type deleted!", Toast.LENGTH_SHORT).show();
                        finish();
                        MainActivity.updateTypeNames();
                    }
                })
                // Do nothing when no is clicked
                .setNegativeButton("No", null)
                .show();
    }
}
