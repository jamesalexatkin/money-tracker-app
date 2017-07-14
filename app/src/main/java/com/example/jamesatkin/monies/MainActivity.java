package com.example.jamesatkin.monies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*button = (Button) findViewById(R.id.btn_addPurchase);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPurchaseActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public void doSomething(View view) {
        Intent intent = new Intent(this, AddPurchaseActivity.class);
        startActivity(intent);
    }
}
