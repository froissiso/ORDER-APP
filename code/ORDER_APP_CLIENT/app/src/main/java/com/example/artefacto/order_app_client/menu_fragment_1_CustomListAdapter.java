package com.example.artefacto.order_app_client;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by fjrois on 16/11/16.
 */
public class menu_fragment_1_CustomListAdapter extends ArrayAdapter<String> {
    private final Activity Context;

    //public static ArrayList<Product> drinks_in_cart;

    private final List<String> listOfDrinks;
    private final List<String> listOfDrinkDrawables;

    public menu_fragment_1_CustomListAdapter(Activity context, List<String> ListOfDrinks, List<String> ListOfDrinkDrawables) {
        super(context, R.layout.my_item, ListOfDrinks);

        this.Context = context;
        this.listOfDrinks = ListOfDrinks;
        this.listOfDrinkDrawables = ListOfDrinkDrawables;

        //this.ListMyMovies = ListMyDrinks;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        View ListViewSingle = inflater.inflate(R.layout.my_item, null, true);

        TextView listViewText = (TextView) ListViewSingle.findViewById(R.id.textView_item_name);
        ImageView listViewImage = (ImageView) ListViewSingle.findViewById(R.id.imageView_item);
        Button button_plus = (Button) ListViewSingle.findViewById(R.id.button_plus);
        listViewText.setText(listOfDrinks.get(position));
        int id = getContext().getResources().getIdentifier(listOfDrinkDrawables.get(position), "drawable", getContext().getPackageName());
        Drawable drawable = getContext().getResources().getDrawable(id, getContext().getResources().newTheme());
        listViewImage.setImageDrawable(drawable);

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //MenuActivity.products_in_cart.add(MenuActivity.menu_products.get(listOfDrinks.get(position)));
                MenuActivity menuActivity = new MenuActivity();
                menuActivity.addDrink2Cart(MenuActivity.menu_products.get(listOfDrinks.get(position)));
                menuActivity.addProduct2Cart(MenuActivity.menu_products.get(listOfDrinks.get(position)));
                //MenuActivity.drinks_in_cart.add(MenuActivity.menu_products.get(listOfDrinks.get(position)));
                Toast.makeText(getContext(),"Added "+listOfDrinks.get(position),Toast.LENGTH_SHORT).show();
                Log.i("Added product: ",listOfDrinks.get(position));
                Log.d("drinks_in_cart size: ",String.valueOf(MenuActivity.drinks_in_cart.size()));
                //Toast.makeText(getContext(),MenuActivity.drinks_in_cart.size(),Toast.LENGTH_SHORT).show();

            }
        });


        return ListViewSingle;
    }
}
