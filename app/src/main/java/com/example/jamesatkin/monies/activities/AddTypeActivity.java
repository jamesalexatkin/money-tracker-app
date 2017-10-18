package com.example.jamesatkin.monies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;
import com.example.jamesatkin.monies.TypeIcon;

public class AddTypeActivity extends TypeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImageButtonImage(0);
    }

    public void onFinishClicked(View view) {
        //validateFields();

        // Add to database
        Type type = super.readFields(MainActivity.typeIdCount++);
        MainActivity.db.addType(type);
        MainActivity.updateTypeNames();

        //Display confirmation message
        Toast.makeText(getApplicationContext(), "Type added!", Toast.LENGTH_SHORT).show();
        finish();
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
