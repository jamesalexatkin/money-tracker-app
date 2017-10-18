package com.example.jamesatkin.monies;

import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jamesatkin.monies.activities.MainActivity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Contacts.SettingsColumns.KEY;
import static com.example.jamesatkin.monies.activities.MainActivity.getTypeNames;
import static com.example.jamesatkin.monies.activities.MainActivity.purchaseIdCount;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 7;

    // Database Name
    private static final String DATABASE_NAME = "dbManager";

    // Table names
    private static final String TABLE_PURCHASES = "purchases";
    private static final String TABLE_TYPES = "types";

    // Purchases table column names
    private static final String KEY_PURCHASES_ID = "id";
    private static final String KEY_PURCHASES_NAME = "name";
    private static final String KEY_PURCHASES_COST = "cost";
    private static final String KEY_PURCHASES_DATE = "date";
    private static final String KEY_PURCHASES_TYPE = "type";
    private static final String KEY_PURCHASES_PLACE = "place";
    private static final String KEY_PURCHASES_COMMENT = "comment";

    // Types table column names
    private static final String KEY_TYPES_ID = "id";
    private static final String KEY_TYPES_NAME = "name";
    private static final String KEY_TYPES_ICON = "icon";
    private static final String KEY_TYPES_LUXURY = "luxury";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PURCHASES_TABLE = "CREATE TABLE " + TABLE_PURCHASES + "("
                + KEY_PURCHASES_ID + " INTEGER PRIMARY KEY, "
                + KEY_PURCHASES_NAME + " TEXT, "
                + KEY_PURCHASES_COST + " FLOAT, "
                + KEY_PURCHASES_DATE + " INTEGER, "
                + KEY_PURCHASES_TYPE + " INTEGER, "
                + KEY_PURCHASES_PLACE + " PLACE, "
                + KEY_PURCHASES_COMMENT + " COMMENT "
                + ")";
        String CREATE_TYPES_TABLE = "CREATE TABLE " + TABLE_TYPES + "("
                + KEY_TYPES_ID + " INTEGER PRIMARY KEY, "
                + KEY_TYPES_NAME + " TEXT, "
                + KEY_TYPES_ICON + " INTEGER, "
                + KEY_TYPES_LUXURY + " BOOLEAN "
                + ")";
        db.execSQL(CREATE_PURCHASES_TABLE);
        db.execSQL(CREATE_TYPES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPES);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }


    // Purchase methods

    // Adding new purchase
    public void addPurchase(Purchase purchase) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PURCHASES_NAME, purchase.getName());
        values.put(KEY_PURCHASES_COST, purchase.getCost());
        values.put(KEY_PURCHASES_DATE, purchase.getDate().getTime());
        values.put(KEY_PURCHASES_TYPE, purchase.getType());
        values.put(KEY_PURCHASES_PLACE, purchase.getPlace());
        values.put(KEY_PURCHASES_COMMENT, purchase.getComment());

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

        Cursor cursor = db.query(TABLE_PURCHASES, new String[]{KEY_PURCHASES_ID,
                        KEY_PURCHASES_NAME, KEY_PURCHASES_COST, KEY_PURCHASES_DATE, KEY_PURCHASES_TYPE, KEY_PURCHASES_PLACE, KEY_PURCHASES_COMMENT}, KEY_PURCHASES_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Purchase purchase = new Purchase(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Float.parseFloat(cursor.getString(2)),
                new Date(Long.parseLong(cursor.getString(3))),
                Integer.parseInt(cursor.getString(4)),
                cursor.getString(5),
                cursor.getString(6));
        return purchase;
    }

    // Getting all purchases
    public ArrayList<Purchase> getAllPurchases() {
        ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PURCHASES + " ORDER BY " + KEY_PURCHASES_DATE + " DESC";

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
                purchase.setType(Integer.parseInt(cursor.getString(4)));
                purchase.setPlace(cursor.getString(5));
                purchase.setComment(cursor.getString(6));
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
        values.put(KEY_PURCHASES_NAME, purchase.getName());
        values.put(KEY_PURCHASES_COST, purchase.getCost());
        values.put(KEY_PURCHASES_DATE, purchase.getDate().getTime());
        values.put(KEY_PURCHASES_TYPE, purchase.getType());
        values.put(KEY_PURCHASES_PLACE, purchase.getPlace());
        values.put(KEY_PURCHASES_COMMENT, purchase.getComment());

        // updating row
        return db.update(TABLE_PURCHASES, values, KEY_PURCHASES_ID + " = ?",
                new String[]{String.valueOf(purchase.getId())});
    }

    // Deleting single purchase
    public void deletePurchase(Purchase purchase) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PURCHASES, KEY_PURCHASES_ID + " = ?",
                new String[]{String.valueOf(purchase.getId())});
        db.close();
    }


    // Type methods

    // Adding new type
    public void addType(Type type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPES_NAME, type.getName());
        values.put(KEY_TYPES_ICON, type.getIconId());
        values.put(KEY_TYPES_LUXURY, type.getLuxury());

        // Inserting Row
        db.insert(TABLE_TYPES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single type
    public Type getType(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TYPES, new String[] {KEY_TYPES_ID,
                        KEY_TYPES_NAME, KEY_TYPES_ICON, KEY_TYPES_LUXURY}, KEY_TYPES_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
//        String selectQuery = "SELECT " + KEY_TYPES_ID + ", " + KEY_TYPES_NAME + ", " + KEY_TYPES_ICON + ", " + KEY_TYPES_LUXURY + " FROM " + TABLE_TYPES + " WHERE " + KEY_TYPES_ID + " = " + id + ";";
//        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {

                String s = cursor.getString(0);

                Type type = new Type(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)),
                        Boolean.valueOf(cursor.getString(3)));
                return type;
            }
        }
        catch (Exception e) {

        }
        return null;
    }

    // Getting all types
    public ArrayList<Type> getAllTypes() {
        ArrayList<Type> typeList = new ArrayList<Type>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TYPES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Type type = new Type();
                type.setId(Integer.parseInt(cursor.getString(0)));
                type.setName(cursor.getString(1));
                type.setIconId(Integer.parseInt(cursor.getString(2)));
                // For some reason, boolean is stored as 0 or 1, so compares int value of string to 1 to check if true or false
                type.setLuxury(Boolean.valueOf(Integer.parseInt(cursor.getString(3)) == 1));
                // Adding type to list
                typeList.add(type);
            } while (cursor.moveToNext());
        }

        // return contact list
        return typeList;
    }

    // Getting types count
    public int getTypesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TYPES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // Return count
        return cursor.getCount();
    }

    // Updating single type
    public int updateType(Type type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPES_NAME, type.getName());
        values.put(KEY_TYPES_ICON, type.getIconId());
        values.put(KEY_TYPES_LUXURY, type.getLuxury());

        // updating row
        return db.update(TABLE_TYPES, values, KEY_TYPES_ID + " = ?",
                new String[]{String.valueOf(type.getId())});
    }

    // Deleting single type
    public void deleteType(Type type) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TYPES, KEY_TYPES_ID + " = ?",
                new String[]{String.valueOf(type.getId())});
        db.close();
    }


    public float[] getByPeriod(String period) {
        String[] typeNames = MainActivity.getTypeNames();
        float[] costs = new float[typeNames.length];

        String sumQuery = "";

        switch (period) {
            case "Week":

                break;
            case "Month":
                break;
            case "Year":
                break;
            case "All time":
                for (int i = 0; i < typeNames.length; i++) {
                    sumQuery = "SELECT SUM(" + KEY_PURCHASES_COST + ") FROM " + TABLE_PURCHASES + " WHERE " + KEY_PURCHASES_TYPE + " = " + i;
                    costs[i] = performSumQuery(sumQuery);
                }
                break;
            default:
                break;
        }


        return costs;
    }

    private float performSumQuery(String sumQuery) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sumQuery, null);

        float sum = 0.0f;

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                sum = (Float.parseFloat(cursor.getString(0)));
            } while (cursor.moveToNext());
        }

        return sum;
    }
}
