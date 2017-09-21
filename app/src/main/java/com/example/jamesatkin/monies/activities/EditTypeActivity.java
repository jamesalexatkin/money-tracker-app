package com.example.jamesatkin.monies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;

import static android.R.attr.id;

public class EditTypeActivity extends TypeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        Type type = b.getParcelable("Type");

        id = type.getId();

        EditText textView = (EditText) findViewById(R.id.txt_Name);
        textView.setText(type.getName());

        CheckBox checkBoxLuxury = (CheckBox) findViewById(R.id.checkbox_luxury);
        checkBoxLuxury.setChecked(type.getLuxury());
    }

    @Override
    public void onFinishClicked(View view) {
        Type type = super.readFields(id);
        MainActivity.db.updateType(type);

        Toast.makeText(getApplicationContext(), "Type updated!", Toast.LENGTH_SHORT).show();
    }
}
