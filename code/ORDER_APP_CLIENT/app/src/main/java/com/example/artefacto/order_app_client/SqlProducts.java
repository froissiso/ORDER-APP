package com.example.artefacto.order_app_client;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SqlProducts extends SQLiteOpenHelper {
        // Database Version
        private static final int DATABASE_VERSION = 13;
        // Database Name
        private static final String DATABASE_NAME = "ProductDB";
        // Books table name
        private static final String TABLE_PRODUCTS = "products";
        // Books Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_TYPE = "type";
        public static final String KEY_NAME = "name";
        public static final String KEY_DRAWABLE = "idDrawable";
        public static final String KEY_PRICE = "price";

        public SqlProducts(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // SQL statement to create book table
            String CREATE_ORDERS_TABLE = "CREATE TABLE "+TABLE_PRODUCTS+" ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "type TEXT, " +
                    "name TEXT, " +"idDrawable TEXT, " +"price DOUBLE)";
            // create books table
            db.execSQL(CREATE_ORDERS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older books table if existed
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
            // create fresh books table
            this.onCreate(db);
        }

        /*CRUD operations (create "add", read "get", update, delete) */
        public void addProduct(Product product){
            Log.d("addProduct", product.toString());
            // 1. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();
            // 2. create ContentValues to add key "column"/value
            ContentValues values = new ContentValues();

            values.put(KEY_NAME, product.getName()); // get title
            values.put(KEY_TYPE, product.getType()); // get title
            values.put(KEY_DRAWABLE, product.getDrawable()); // get title
            values.put(KEY_PRICE, product.getPrice()); // get title

            // 3. insert
            db.insert(TABLE_PRODUCTS, // table
                    null, //nullColumnHack
                    values); // key/value -> keys = column names/values
            // 4. Close dbase
            db.close();
        }

        public List<Product> getAllDrinks() {
            List<Product> productList = new LinkedList<Product>();
            String drinks = "drink";
            Cursor cursor = getReadableDatabase().rawQuery("select * from "+ TABLE_PRODUCTS +" where type = ?", new String[] { drinks });
            Product product = new Product();
            if (cursor.moveToFirst()) {
                do {
                    product = new Product();
                    product.setId(Integer.parseInt(cursor.getString(0)));
                    product.setType(cursor.getString(1));
                    product.setName(cursor.getString(2));
                    product.setDrawable(cursor.getString(3));
                    product.setPrice(Double.parseDouble(cursor.getString(4)));
                    // Add book to books
                    productList.add(product);
                } while (cursor.moveToNext());
            }

            Log.d("getAllDrinks()", productList.toString());
            return productList; // return books
        }

    public List<String> getAllDrinkNames() {
        List<String> drinkNamesList = new LinkedList<String>();
        String drinks = "drink";
        Cursor cursor = getReadableDatabase().rawQuery("select name from "+ TABLE_PRODUCTS +" where type = ?", new String[] { drinks });

        if (cursor.moveToFirst()) {
            do {
                drinkNamesList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        Log.d("getAllDrinkNames()", drinkNamesList.toString());
        return drinkNamesList; // return books
    }

    public List<Product> getAllFood() {
        List<Product> productList = new LinkedList<Product>();
        String food = "food";
        Cursor cursor = getReadableDatabase().rawQuery("select * from "+ TABLE_PRODUCTS +" where type = ?", new String[] { food });
        Product product = new Product();
        if (cursor.moveToFirst()) {
            do {
                product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setType(cursor.getString(1));
                product.setName(cursor.getString(2));
                product.setDrawable(cursor.getString(3));
                product.setPrice(Double.parseDouble(cursor.getString(4)));
                // Add book to books
                productList.add(product);
            } while (cursor.moveToNext());
        }

        Log.d("getAllFood()", productList.toString());
        return productList; // return books
    }

    public List<String> getAllFoodNames() {
        List<String> foodNamesList = new LinkedList<String>();
        String food = "food";
        Cursor cursor = getReadableDatabase().rawQuery("select name from "+ TABLE_PRODUCTS +" where type = ?", new String[] { food });

        if (cursor.moveToFirst()) {
            do {
                foodNamesList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        Log.d("getAllFoodNames()", foodNamesList.toString());
        return foodNamesList; // return books
    }

    public List<String> getAllDrawables(String type) {
        List<String> drawableList = new LinkedList<String>();
        Cursor cursor = getReadableDatabase().rawQuery("select "+KEY_DRAWABLE+" from "+ TABLE_PRODUCTS +" where type = ?", new String[] { type });

        // 2. get reference to writable DB
        if (cursor.moveToFirst()) {
            do {
                drawableList.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        Log.d("getAllDrawables()-->"+type, drawableList.toString());
        return drawableList; // return books
    }

            // Get All Books
            public List<Product> getAllProducts() {
                List<Product> productList = new LinkedList<Product>();
                // 1. build the query
                String query = "SELECT * FROM " + TABLE_PRODUCTS;
                // 2. get reference to writable DB
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(query, null);
                // 3. go over each row, build book and add it to list
                Product product = null;
                if (cursor.moveToFirst()) {
                    do {
                        product = new Product();
                        product.setId(Integer.parseInt(cursor.getString(0)));
                        product.setType(cursor.getString(1));
                        product.setName(cursor.getString(2));
                        product.setDrawable(cursor.getString(3));
                        product.setPrice(Double.parseDouble(cursor.getString(4)));
                        // Add book to books
                        productList.add(product);
                    } while (cursor.moveToNext());
                }
                if (product != null) {
                    Log.d("getAllProducts()", product.toString());
                }
                return productList; // return books
            }


        // Get Order by Id
        public Product getProductById(String id) {
            Product p = new Product();
            Cursor cursor = getReadableDatabase().rawQuery("select * from "+ TABLE_PRODUCTS +" where id = ?", new String[] { id });
            if (cursor.moveToFirst()) {
                do {
                    p = new Product();
                    p.setId(Integer.parseInt(cursor.getString(0)));
                    p.setType(cursor.getString(1));
                    p.setName(cursor.getString(2));
                    p.setDrawable(cursor.getString(3));
                    p.setPrice(Double.parseDouble(cursor.getString(4)));

                } while (cursor.moveToNext());
            }
            return p; // return books
        }
        // Get Book by Title

        // Updating single order
        public int updateProduct(Product product, String newId, String newType, String newName, String newDrawable, String newPrice) {
            // 1. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();
            // 2. create ContentValues to add key "column"/value
            ContentValues values = new ContentValues();
            values.put("id", newId);
            values.put("type", newType);
            values.put("name", newName);
            values.put("drawable", newDrawable);
            values.put("price", newPrice);
            // 3. updating row
            int i = db.update(TABLE_PRODUCTS, //table
                    values, // column/value
                    KEY_ID+" = ?", // selections
                    new String[] { String.valueOf(product.getId()) }); //selection args
            // 4. close dbase
            db.close();
            Log.d("UpdateProduct", product.toString());
            return i;
        }

        // Deleting single order
        public void deleteProduct(String id) {
            // 1. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();
            // 2. delete
            db.delete(TABLE_PRODUCTS, KEY_ID+" = ?",
                    new String[] { String.valueOf(id) });
            //3. close
            db.close();
            Log.d("deleteProduct", getProductById(id).toString());
        }

        public List<String> getnColumns() {
            String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor c = database.rawQuery(selectQuery, null);
            List<String> columnNames = new ArrayList<String>();
            for(int i =0 ; i<c.getColumnCount(); i++){
                columnNames.add(c.getColumnName(i));
            }
            return columnNames;
        }
}
