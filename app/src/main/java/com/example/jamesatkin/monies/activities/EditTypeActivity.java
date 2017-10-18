package com.example.jamesatkin.monies.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;
import com.example.jamesatkin.monies.TypeIcon;

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

        setImageButtonImage(type.getIconId());

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

    public void onTypeIconClicked(View view) {
        Intent intent = new Intent(this, SelectTypeIconActivity.class);
        startActivityForResult(intent, 1);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                setImageButtonImage(data.getIntExtra("icon", 0));
            }
        }
    }

    private void setImageButtonImage(int typeIconId) {
        // Gets the image button
        ImageButton btn = (ImageButton) findViewById(R.id.btn_typeIcon);

        TypeIcon typeIcon = MainActivity.getTypeIconById(typeIconId);

        // Gets the id of the actual image to display, using the name of the TypeIcon
        String name = typeIcon.getDrawablePath();
        final int id = getResources().getIdentifier(name, "drawable", getPackageName());
        btn.setImageResource(id);

        iconId = typeIconId;
    }
}
