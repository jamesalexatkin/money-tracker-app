//package com.example.jamesatkin.monies;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.widget.Button;
//
//public class SquareButton extends android.support.v7.widget.AppCompatButton {
//    public SquareButton(Context context) {
//        super(context);
//    }
//
//    public SquareButton(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public SquareButton(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    /*public SquareButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }*/
//
//    @Override
//    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        int size = width > height ? height : width;
//        setMeasuredDimension(size, size); // make it square
//
//    }
//}
