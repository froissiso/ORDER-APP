package com.example.maria.order_app_restaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Order> {

    //private final Context context;
    private final ArrayList<Order> itemsArrayList;
    private String drinks;
    private String food;
    public static String nId;
    private ListView mainListView;
    private View rowView;
    private Activity activity;
    private SqlHelper db;
    private Integer position;
    public static Order order;


    public MyAdapter(Activity activity, ArrayList<Order> itemsArrayList) {

        super(activity, R.layout.orders, itemsArrayList);

        this.activity = activity;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView id;
        TextView customerName;
        TextView customerPhone;
        final Button viewOrderButton;

        // 2. Get rowView from inflater & 3. Get the two text view from the rowView & 4. Set the text for textView

        rowView = inflater.inflate(R.layout.all, parent, false);

        db = new SqlHelper(getContext());

        id = (TextView) rowView.findViewById(R.id.nId);
        customerName = (TextView) rowView.findViewById(R.id.customerName);
        customerPhone = (TextView) rowView.findViewById(R.id.phoneNumber);

        id.setText(Integer.toString(itemsArrayList.get(position).getId()));
        customerName.setText(itemsArrayList.get(position).getCustomerName());
        customerPhone.setText(itemsArrayList.get(position).getPhoneNumber());

        viewOrderButton = (Button) rowView.findViewById(R.id.viewOrderButton);
        this.position = position;
        this.nId = Integer.toString(itemsArrayList.get(position).getId());
        viewOrderButton.setTag(new Integer(position));

        mainListView = (ListView) rowView.findViewById(R.id.order);


        viewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = (Integer) v.getTag();
                Intent i = new Intent(activity, OrderActivity.class);
                i.putExtra("id",Integer.toString(position));

                Log.e("DRINKS", itemsArrayList.get(position).getDrinks());
                Log.e("FOOD", itemsArrayList.get(position).getFood());
                i.putExtra("drinks", itemsArrayList.get(position).getDrinks());
                i.putExtra("food",itemsArrayList.get(position).getFood());

                activity.startActivity(i);
            }
        });

        // 5. return rowView
        return rowView;
    }
}