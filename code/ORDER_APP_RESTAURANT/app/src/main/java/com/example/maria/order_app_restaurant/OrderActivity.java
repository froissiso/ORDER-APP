package com.example.maria.order_app_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class OrderActivity extends AppCompatActivity {

    private ListView drinksList;
    private ListView foodList;
    private Button completeOrder;
    private String id;
    public static SqlHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elements);

        Intent intent = this.getIntent();
        id = intent.getStringExtra("id");

        String drinks = intent.getStringExtra("drinks");
        String food = intent.getStringExtra("food");

        String[] arrayDrinks = drinks.split(";");
        String[] arrayFood = food.split(";");

        MyDrinkAdapter drinkAdapter = new MyDrinkAdapter(this, arrayDrinks, id);
        drinksList = (ListView) findViewById(R.id.drinkList);
        drinksList.setAdapter(drinkAdapter);

        MyFoodAdapter foodAdapter = new MyFoodAdapter(this, arrayFood, id);

        foodList = (ListView) findViewById(R.id.foodList);
        foodList.setAdapter(foodAdapter);

        completeOrder = (Button) findViewById(R.id.completeElement);

        completeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new SqlHelper(getApplicationContext());
                db.deleteOrder(Integer.toString(Integer.parseInt(id)+1+My2ndActivity.nDeleted));
                My2ndActivity.nDeleted++;
                WD_CommServerActivity.orderready = true;

                Intent intent = new Intent(OrderActivity.this, My2ndActivity.class);
                OrderActivity.this.startActivity(intent);


            }
        });


    }
}