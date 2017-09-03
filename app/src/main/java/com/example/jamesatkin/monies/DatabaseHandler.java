package com.example.jamesatkin.monies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.format;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "purchasesManager";

    // Contacts table name
    private static final String TABLE_PURCHASES = "purchases";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COST = "cost";
    private static final String KEY_DATE = "date";
    private static final String KEY_TYPE = "type";
    private static final String KEY_LUXURY = "luxury";
    private static final String KEY_PLACE = "place";
    private static final String KEY_COMMENT = "comment";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PURCHASES_TABLE = "CREATE TABLE " + TABLE_PURCHASES + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_COST + " FLOAT, "
                + KEY_DATE + " INTEGER, "
                + KEY_TYPE + " TEXT, "
                + KEY_LUXURY + " BOOLEAN, "
                + KEY_PLACE + " PLACE, "
                + KEY_COMMENT + " COMMENT "
                + ")";
        db.execSQL(CREATE_PURCHASES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASES);

        // Create tables again
        onCreate(db);
    }

    // Adding new purchase
    public void addPurchase(Purchase purchase) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, purchase.getName());
        values.put(KEY_COST, purchase.getCost());
        values.put(KEY_DATE, purchase.getDate().getTime());
        values.put(KEY_TYPE, purchase.getType());
        values.put(KEY_LUXURY, purchase.getLuxury());
        values.put(KEY_PLACE, purchase.getPlace());
        values.put(KEY_COMMENT, purchase.getComment());

        // Inserting Row
        db.insert(TABLE_PURCHASES, null, values);
        db.close(); // Closing database connection
    }

    // Method for converting a date to a string to store in the database
    private String dateToString(java.util.Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = formatter.format(d);
        return dateString;
    }

    // Getting single purchase
    public Purchase getPurchase(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PURCHASES, new String[] { KEY_ID,
                        KEY_NAME, KEY_COST, KEY_DATE, KEY_TYPE, KEY_LUXURY, KEY_PLACE, KEY_COMMENT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Purchase purchase = new Purchase(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Float.parseFloat(cursor.getString(2)),
                new Date(Long.parseLong(cursor.getString(3))),
                cursor.getString(4),
                cursor.getString(5),
                Boolean.valueOf(cursor.getString(6)),
                cursor.getString(7));
        // return contact
        return purchase;
    }

    // Getting all purchases
    public ArrayList<Purchase> getAllPurchases() {
        ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PURCHASES + " ORDER BY " + KEY_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Purchase purchase = new Purchase();
                purchase.setId(Integer.parseInt(cursor.getString(0)));
                purchase.setName(cursor.getString(1));
                purchase.setCost(Float.parseFloat(cursor.getString(2)));


                //purchase.setDate(Date.valueOf(cursor.getString(3)));
                purchase.setDate(new Date(Long.parseLong(cursor.getString(3))));
                purchase.setType(cursor.getString(4));
                purchase.setLuxury(Boolean.valueOf(cursor.getString(5)));
                purchase.setPlace(cursor.getString(6));
                purchase.setComment(cursor.getString(7));
                // Adding purchase to list
                purchaseList.add(purchase);
            } while (cursor.moveToNext());
        }

        // return contact list
        return purchaseList;
    }

    private Date stringToDate(String s) {
        //THIS LINE NOT WORKING!!!!!!!!!!!!!!
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date sqlDate;
        try {
            java.util.Date utilDate = format.parse(s);
            sqlDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            // 0 seconds since 1 Jan 1970, so uses this date
            sqlDate = new Date(0);
        }

        return sqlDate;
    }

    // Getting purchases count
    public int getPurchasesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PURCHASES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // Return count
        return cursor.getCount();
    }

    // Updating single purchase
    public int updatePurchase(Purchase purchase) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, purchase.getName());
        values.put(KEY_COST, purchase.getCost());
        values.put(KEY_DATE, purchase.getDate().getTime());
        values.put(KEY_TYPE, purchase.getType());
        values.put(KEY_LUXURY, purchase.getLuxury());
        values.put(KEY_PLACE, purchase.getPlace());
        values.put(KEY_COMMENT, purchase.getComment());

        // updating row
        return db.update(TABLE_PURCHASES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(purchase.getId()) });
    }

    // Deleting single purchase
    public void deletePurchase(Purchase purchase) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PURCHASES, KEY_ID + " = ?",
                new String[] { String.valueOf(purchase.getId()) });
        db.close();
    }
}