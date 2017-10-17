package com.example.jamesatkin.monies.activities;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.adapters.TypeIconAdapter;

public class IconSelectDialog {

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_icon_select);

        GridView gridview = (GridView) activity.findViewById(R.id.gridview_icons);
        gridview.setAdapter(new TypeIconAdapter(this, MainActivity.getTypeIcons()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // DO STUFFFF
            }
        });

//        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//        text.setText(msg);
//
//        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

        dialog.show();

    }
}
