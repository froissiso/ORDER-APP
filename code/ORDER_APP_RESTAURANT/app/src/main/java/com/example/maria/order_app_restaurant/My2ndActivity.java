package com.example.maria.order_app_restaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class My2ndActivity extends AppCompatActivity {

    private ListView mainListView;
    public static List<Order> list;
    public static SqlHelper db;
    private static Integer flagDBcreated = 0;
    public static Integer nDeleted = 0;
    private static String message;

    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders);
        Intent i = getIntent();
        message = i.getStringExtra("message");
        //Hasta aqui va bien!!!

        MyAdapter adapter = new MyAdapter(this, generateData(this));
        mainListView = (ListView) findViewById(R.id.order);
        mainListView.setAdapter(adapter);

    }

    public static void fillDB(SqlHelper db) {
        Log.e("Createdby", "Maria Garcia"); //Show the author of the DB
        // add Books
        String[] order = message.split("/");

            db.addOrder(new Order(order[0], order[1], order[2], order[3]));

        // get all books
        list = db.getAllOrders();
    }

    public static ArrayList<Order> generateData(Context context) {

        db = new SqlHelper(context);
        if (flagDBcreated == 0) {
            flagDBcreated = 1;
            fillDB(db);
        } else {

        }

        list = db.getAllOrders();

        return convertList2ArrayList(list);
    }

    public static ArrayList<Order> convertList2ArrayList(List list) {
        ArrayList<Order> arrayList = new ArrayList<Order>(list);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Order b = (Order) iterator.next();
        }
        return arrayList;
    }

}