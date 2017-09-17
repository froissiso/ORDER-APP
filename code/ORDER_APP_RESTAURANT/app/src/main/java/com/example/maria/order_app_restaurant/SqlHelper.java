package com.example.maria.order_app_restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SqlHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 46;
    // Database Name
    private static final String DATABASE_NAME = "OrderDB";
    // Books table name
    private static final String TABLE_ORDERS = "orders";
    // Books Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_USER = "user";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_DRINKS = "drinks";
    public static final String KEY_FOOD = "food";

    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_ORDERS_TABLE = "CREATE TABLE orders ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "user TEXT, " +
                "phone TEXT, " + "drinks TEXT, " + "food TEXT )";
        // create books table
        db.execSQL(CREATE_ORDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS orders");
        // create fresh books table
        this.onCreate(db);
    }

    /*CRUD operations (create "add", read "get", update, delete) */
    public void addOrder(Order order){
        Log.d("addOrder", order.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        values.put(KEY_USER, order.getCustomerName()); // get title
        values.put(KEY_PHONE, order.getPhoneNumber()); // get title
        values.put(KEY_DRINKS, order.getDrinks()); // get title
        values.put(KEY_FOOD, order.getFood()); // get author

        // 3. insert
        db.insert(TABLE_ORDERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values
        // 4. Close dbase
        db.close();
    }

    // Get All Books
    public List<Order> getAllOrders() {
        List<Order> orderList = new LinkedList<Order>();
        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ORDERS;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build book and add it to list
        Order order = null;
        if (cursor.moveToFirst()) {
            do {
                order = new Order();
                order.setId(Integer.parseInt(cursor.getString(0)));
                order.setCustomerName(cursor.getString(1));
                order.setPhoneNumber(cursor.getString(2));
                order.setDrinks(cursor.getString(3));
                order.setFood(cursor.getString(4));
                // Add book to books
                orderList.add(order);
            } while (cursor.moveToNext());
        }
        if(order !=null) {
            Log.d("getAllOrders()", order.toString());
        }
        return orderList; // return books
    }


    // Get All Drinks
    public ArrayList<String> getAllDrinks() {
        List<String> drinksList = new LinkedList<String>();
        // 1. build the query
        String query = "SELECT drinks FROM " + TABLE_ORDERS;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build book and add it to list
        if (cursor.moveToFirst()) {
            do {
                drinksList.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        if(drinksList !=null) {
            Log.d("getAllDrinks()", drinksList.toString());
        }
        ArrayList<String> drinksArrayList = new ArrayList<String>(drinksList);
        return drinksArrayList; // return drinks
    }


    // Get Order by Id
    public Order getOrderById(String id) {
        Order b = new Order();
        Cursor cursor = getReadableDatabase().rawQuery("select * from orders where id = ?", new String[] { id });
        if (cursor.moveToFirst()) {
            do {
                b = new Order();
                b.setId(Integer.parseInt(cursor.getString(0)));
                b.setCustomerName(cursor.getString(1));
                b.setPhoneNumber(cursor.getString(2));
                b.setDrinks(cursor.getString(3));
                b.setFood(cursor.getString(4));

            } while (cursor.moveToNext());
        }
        return b; // return books
    }
    // Get Book by Title

    // Updating single order
    public int updateOrder(Order order, String newDrinks, String newFood, String newUserName, String newPhoneNumber) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("userName", newUserName);
        values.put("phoneNumber", newPhoneNumber);
        values.put("drinks", newDrinks);
        values.put("food", newFood);
        // 3. updating row
        int i = db.update(TABLE_ORDERS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(order.getId()) }); //selection args
        // 4. close dbase
        db.close();
        Log.d("UpdateOrder", order.toString());
        return i;
    }

    // Deleting single order
    public void deleteOrder(String id) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_ORDERS, KEY_ID+" = ?",
                new String[] { String.valueOf(id) });
        //3. close
        db.close();
        Log.d("deleteOrder", getOrderById(id).toString());
    }

    public List<String> getnColumns() {
        String selectQuery = "SELECT * FROM " + TABLE_ORDERS;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        List<String> columnNames = new ArrayList<String>();
        for(int i =0 ; i<c.getColumnCount(); i++){
            columnNames.add(c.getColumnName(i));
        }
        return columnNames;
    }

}