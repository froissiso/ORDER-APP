package com.example.artefacto.order_app_client;

import android.database.sqlite.SQLiteOpenHelper;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.util.Log;
    import java.util.ArrayList;
    import java.util.List;

    public class SqlClient extends SQLiteOpenHelper {

        // Database Version
        private static final int DATABASE_VERSION = 7;
        // Database Name
        private static final String DATABASE_NAME = "ClientInfoDB";
        // Books table name
        private static final String TABLE_CLIENTINFO = "clientinfo";
        // Books Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_USER = "user";
        public static final String KEY_PHONE = "phone";

        public SqlClient(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // SQL statement to create book table
            String CREATE_CLIENTINFO_TABLE = "CREATE TABLE "+ TABLE_CLIENTINFO+" ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "user TEXT, " +
                    "phone TEXT)";
            // create books table
            db.execSQL(CREATE_CLIENTINFO_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older books table if existed
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLIENTINFO);
            // create fresh books table
            this.onCreate(db);
        }

        /*CRUD operations (create "add", read "get", update, delete) */
        public void addClientInfo(Client info){
            Log.d("addClientInfo", info.toString());
            // 1. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();
            // 2. create ContentValues to add key "column"/value
            ContentValues values = new ContentValues();

            values.put(KEY_USER, info.getName()); // get title
            values.put(KEY_PHONE, info.getPhoneNumber()); // get title

            // 3. insert
            db.insert(TABLE_CLIENTINFO, // table
                    null, //nullColumnHack
                    values); // key/value -> keys = column names/values
            // 4. Close dbase
            db.close();
        }

        // Get All Books
        public String[] getAllInfo() {
            String[] clientInfo = new String[]{};
            // 1. build the query
            String query = "SELECT * FROM " + TABLE_CLIENTINFO;
            // 2. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            // 3. go over each row, build book and add it to list
            if (cursor.moveToFirst()) {
                do {
                    clientInfo = new String[]{};
                    clientInfo[0] = cursor.getString(0);
                    clientInfo[1] = cursor.getString(1);
                    clientInfo[2] = cursor.getString(2);
                    // Add book to books
                } while (cursor.moveToNext());
            }
            if(clientInfo !=null) {
                Log.d("getAllInfo()", clientInfo.toString());
            }
            return clientInfo; // return books
        }


        // Get Order by Id
        public Client getClientInfoById(String id) {
            Client c = new Client();
            Cursor cursor = getReadableDatabase().rawQuery("select * from "+TABLE_CLIENTINFO+" where id = ?", new String[] { id });
            if (cursor.moveToFirst()) {
                do {
                    c = new Client();
                    c.setId(Integer.parseInt(cursor.getString(0)));
                    c.setName(cursor.getString(1));
                    c.setPhoneNumber(cursor.getString(2));

                } while (cursor.moveToNext());
            }
            return c; // return books
        }
        // Get Book by Title

        // Updating single order
        public int updateClient(Client info, String newUserName, String newPhoneNumber) {
            // 1. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();
            // 2. create ContentValues to add key "column"/value
            ContentValues values = new ContentValues();
            values.put("userName", newUserName);
            values.put("phoneNumber", newPhoneNumber);
            // 3. updating row
            int i = db.update(TABLE_CLIENTINFO, //table
                    values, // column/value
                    KEY_ID+" = ?", // selections
                    new String[] { String.valueOf(info.getId()) }); //selection args
            // 4. close dbase
            db.close();
            Log.d("UpdateClientInfo", info.toString());
            return i;
        }

        // Deleting single order
        public void deleteClientInfo(String id) {
            // 1. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();
            // 2. delete
            db.delete(TABLE_CLIENTINFO, KEY_ID+" = ?",
                    new String[] { String.valueOf(id) });
            //3. close
            db.close();
            Log.d("deleteClientInfo", getClientInfoById(id).toString());
        }

        public List<String> getnColumns() {
            String selectQuery = "SELECT * FROM " + TABLE_CLIENTINFO;
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor c = database.rawQuery(selectQuery, null);
            List<String> columnNames = new ArrayList<String>();
            for(int i =0 ; i<c.getColumnCount(); i++){
                columnNames.add(c.getColumnName(i));
            }
            return columnNames;
        }

    }

