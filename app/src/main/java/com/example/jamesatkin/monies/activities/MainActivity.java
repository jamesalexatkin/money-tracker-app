package com.example.jamesatkin.monies.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jamesatkin.monies.DatabaseHandler;
import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button;
    public static DatabaseHandler db;
    public static String[] typeNames;
    public static int purchaseIdCount = 0;
    public static int typeIdCount = 0;

    public static String[] getTypeNames() {
        ArrayList<Type> typesList = db.getAllTypes();
        String[] typeNamesArray = new String[typesList.size()];
        for (int i = 0; i < typeNamesArray.length; i++) {
            typeNamesArray[i] = typesList.get(i).getName();
        }
        return typeNamesArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
        typeNames = getTypeNames();

        //setButtonSizes();
        //setButtonImages();
    }

    private void setButtonImages() {
        Button btn1 = (Button) findViewById(R.id.btn_addPurchase);
        Button btn2 = (Button) findViewById(R.id.btn_viewPurchases);
        Button btn3 = (Button) findViewById(R.id.btn_3);
        Button btn4 = (Button) findViewById(R.id.btn_4);
        int size = btn1.getWidth();


        Drawable img = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_list_black_24dp);
        img.setBounds(0, 0, (int)(img.getIntrinsicWidth()*0.1), (int)(img.getIntrinsicHeight()*0.1));
        ScaleDrawable scaledImg = new ScaleDrawable(img, 0, 20, 10);
        Button button = (Button)findViewById(R.id.btn_viewPurchases);
        button.setCompoundDrawables(scaledImg.getDrawable(), null, null, null);
    }

    private void setButtonSizes() {
        Button btn1 = (Button) findViewById(R.id.btn_addPurchase);
        Button btn2 = (Button) findViewById(R.id.btn_viewPurchases);
        Button btn3 = (Button) findViewById(R.id.btn_3);
        Button btn4 = (Button) findViewById(R.id.btn_4);

        int width = btn1.getWidth();
        int height = btn1.getHeight();
        int dimension;
        if (width < height) {
            dimension = width;
            btn1.setHeight(dimension);
            btn2.setHeight(dimension);
            btn3.setHeight(dimension);
            btn4.setHeight(dimension);
        }
        else {
            dimension = height;
            btn1.setWidth(dimension);
            btn2.setWidth(dimension);
            btn3.setWidth(dimension);
            btn4.setWidth(dimension);
        }


    }

    public void onAddPurchaseClicked(View view) {
        Intent intent = new Intent(this, AddPurchaseActivity.class);
        startActivity(intent);
    }

    public void onViewPurchasesClicked(View view) {
        Intent intent = new Intent(this, ViewPurchasesActivity.class);
        startActivity(intent);
    }

    public void onSettingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public static int getTypeId(String searchKey) {
        if (searchKey != null) {
            for (int i = 0; i < typeNames.length; i++) {
                if (typeNames[i] == searchKey) {
                    return i;
                }
            }
        }
        return 0;
    }
}
