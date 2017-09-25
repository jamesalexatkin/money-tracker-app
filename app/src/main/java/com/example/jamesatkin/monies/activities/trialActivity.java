package com.example.jamesatkin.monies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jamesatkin.monies.R;

public class trialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial2);

        LinearLayout addPurchase = (LinearLayout)findViewById(R.id.ll_addPurchase);
        addPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(trialActivity.this, AddPurchaseActivity.class);
                startActivity(picture_intent );
            }
        });
    }

//    public void onAddPurchaseClicked(View view) {
//        Intent intent = new Intent(this, AddPurchaseActivity.class);
//        startActivity(intent);
//    }
}
