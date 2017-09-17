package com.example.maria.order_app_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyDrinkAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] arrayDrinks;
    private final String id;

    public MyDrinkAdapter(Context context, String[] drinkArray, String orderId) {

        super(context, R.layout.elements, drinkArray);

        this.context = context;
        arrayDrinks = drinkArray;
        id = orderId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;
        TextView drink;
        ImageView pic;

        // 2. Get rowView from inflater & 3. Get the two text view from the rowView & 4. Set the text for textView

        rowView = inflater.inflate(R.layout.ordered, parent, false);

        drink = (TextView) rowView.findViewById(R.id.elementUd);
        pic = (ImageView) rowView.findViewById(R.id.picture);

        String dText = arrayDrinks[position].toString();
        drink.setText(dText);

        switch (dText){
            case "Coronita":
                pic.setImageResource(R.drawable.coronita);
                break;
            case "Budweiser":
                pic.setImageResource(R.drawable.budweiser);
                break;
            case "Red wine":
                pic.setImageResource(R.drawable.red_wine);
                break;
            case "White wine":
                pic.setImageResource(R.drawable.white_wine);
                break;
            case "Bud light":
                pic.setImageResource(R.drawable.bud_light);
                break;
            case "Coca Cola":
                pic.setImageResource(R.drawable.cocacola);
                break;
            case "Water":
                pic.setImageResource(R.drawable.water);
                break;
            case "Dr.Pepper":
                pic.setImageResource(R.drawable.dr_pepper);
                break;
            case "Fanta":
                pic.setImageResource(R.drawable.fanta);
                break;
            case "Sprite":
                pic.setImageResource(R.drawable.sprite);
                break;
            default:
                break;

        }


        // 5. return rowView
        return rowView;
    }
}