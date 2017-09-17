package com.example.maria.order_app_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFoodAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] arrayFood;
    private String id;

    public MyFoodAdapter(Context context, String[] foodArray, String orderId) {

        super(context, R.layout.elements, foodArray);

        this.context = context;
        arrayFood = foodArray;
        id = orderId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;
        TextView food;
        ImageView pic;

        // 2. Get rowView from inflater & 3. Get the two text view from the rowView & 4. Set the text for textView

        rowView = inflater.inflate(R.layout.ordered, parent, false);

        food = (TextView) rowView.findViewById(R.id.elementUd);
        pic = (ImageView) rowView.findViewById(R.id.picture);

        String dText = arrayFood[position].toString();
        food.setText(dText);

        switch (dText){
            case "Burger":
                pic.setImageResource(R.drawable.burger);
                break;
            case "Nachos":
                pic.setImageResource(R.drawable.nachos);
                break;
            case "Guacamole":
                pic.setImageResource(R.drawable.guacamole);
                break;
            case "Hot Dog":
                pic.setImageResource(R.drawable.hotdog);
                break;
            case "Pizza":
                pic.setImageResource(R.drawable.pizza);
                break;
            case "Burrito":
                pic.setImageResource(R.drawable.burrito);
                break;
            case "Chicken Wings":
                pic.setImageResource(R.drawable.chicken_wings);
                break;
            case "Fries":
                pic.setImageResource(R.drawable.fries);
                break;
            case "Onion rings":
                pic.setImageResource(R.drawable.onion_rings);
                break;
            case "Sushi":
                pic.setImageResource(R.drawable.sushi);
                break;
            default:
                break;

        }

        // 5. return rowView
        return rowView;
    }
}