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

public class PlacesDB extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 15;
    // Database Name
    private static final String DATABASE_NAME = "PlacesDB";
    // Books table name
    private static final String TABLE_PLACES = "places";
    // Books Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IDDRAWABLE = "idDrawable";

    public PlacesDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_PLACES_TABLE = "CREATE TABLE "+TABLE_PLACES+" ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "name TEXT, " +
                "idDrawable TEXT)";
        // create books table
        db.execSQL(CREATE_PLACES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PLACES);
        // create fresh books table
        this.onCreate(db);
    }

    /*CRUD operations (create "add", read "get", update, delete) */
    public void addPlace(Place place){
        Log.d("addPlace", place.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, place.getName()); // get title
        values.put(KEY_IDDRAWABLE, place.getIdDrawable()); // get title

        // 3. insert
        db.insert(TABLE_PLACES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values
        // 4. Close dbase
        db.close();
    }

    // Get All Books
    public List<Place> getAllPlaces() {
        List<Place> placeList = new LinkedList<Place>();
        // 1. build the query
        String query = "SELECT * FROM " + TABLE_PLACES;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build book and add it to list
        Place place = null;
        if (cursor.moveToFirst()) {
            do {
                place = new Place();
                place.setId(Integer.parseInt(cursor.getString(0)));
                place.setName(cursor.getString(1));
                place.setIdDrawable(cursor.getString(2));
                // Add book to books
                placeList.add(place);
            } while (cursor.moveToNext());
        }
        if(place !=null) {
            Log.d("getAllPlaces()", place.toString());
        }
        return placeList; // return books
    }

    // Get Order by Id
    public Place getPlaceById(String id) {
        Place p = new Place();
        Cursor cursor = getReadableDatabase().rawQuery("select * from "+TABLE_PLACES+" where id = ?", new String[] { id });
        if (cursor.moveToFirst()) {
            do {
                p = new Place();
                p.setId(Integer.parseInt(cursor.getString(0)));
                p.setName(cursor.getString(1));
                p.setIdDrawable(cursor.getString(2));


            } while (cursor.moveToNext());
        }
        return p; // return books
    }
    // Get Book by Title

    // Updating single order
    public int updatePlace(Place place, String newName, String newIdDrawable) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("idDrawable", newIdDrawable);
        // 3. updating row
        int i = db.update(TABLE_PLACES, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(place.getId()) }); //selection args
        // 4. close dbase
        db.close();
        Log.d("UpdatePlace", place.toString());
        return i;
    }

    // Deleting single order
    public void deletePlace(String id) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_PLACES, KEY_ID+" = ?",
                new String[] { String.valueOf(id) });
        //3. close
        db.close();
        Log.d("deletePlace", getPlaceById(id).toString());
    }

    public List<String> getnColumns() {
        String selectQuery = "SELECT * FROM " + TABLE_PLACES;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        List<String> columnNames = new ArrayList<String>();
        for(int i =0 ; i<c.getColumnCount(); i++){
            columnNames.add(c.getColumnName(i));
        }
        return columnNames;
    }



}