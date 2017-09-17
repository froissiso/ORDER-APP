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
 * Created by fjrois on 19/11/16.
 */
public class menu_fragment_2_CustomListAdapter extends ArrayAdapter<String> {
    private final Activity Context;

    //public static ArrayList<Product> foods_in_cart;

    private final List<String> listOfFoods;
    private final List<String> listOfFoodDrawables;


    public menu_fragment_2_CustomListAdapter(Activity context, List<String> ListOfFoods, List<String> ListOfFoodDrawables){
        super(context, R.layout.my_item, ListOfFoods);

        this.Context = context;
        this.listOfFoods = ListOfFoods;
        this.listOfFoodDrawables = ListOfFoodDrawables;

        //this.ListMyMovies = ListMyFoods;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        View ListViewSingle = inflater.inflate(R.layout.my_item, null, true);

        //foods_in_cart = new ArrayList<Product>();

        TextView listViewText = (TextView) ListViewSingle.findViewById(R.id.textView_item_name);
        ImageView listViewImage = (ImageView) ListViewSingle.findViewById(R.id.imageView_item);
        Button button_plus = (Button) ListViewSingle.findViewById(R.id.button_plus);

        int id = getContext().getResources().getIdentifier(listOfFoodDrawables.get(position), "drawable", getContext().getPackageName());
        Drawable drawable = getContext().getResources().getDrawable(id, getContext().getResources().newTheme());
        listViewImage.setImageDrawable(drawable);


        listViewText.setText(listOfFoods.get(position));
        //listViewImage.setImageResource(____);
        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //MenuActivity.products_in_cart.add(MenuActivity.menu_products.get(listOfFoods.get(position)));
                MenuActivity menuActivity = new MenuActivity();
                menuActivity.addFood2Cart(MenuActivity.menu_products.get(listOfFoods.get(position)));
                menuActivity.addProduct2Cart(MenuActivity.menu_products.get(listOfFoods.get(position)));
                //MenuActivity.foods_in_cart.add(MenuActivity.menu_products.get(listOfFoods.get(position)));
                Toast.makeText(getContext(),"Added "+listOfFoods.get(position),Toast.LENGTH_SHORT).show();
                Log.i("Added product: ",listOfFoods.get(position));
                Log.d("foods_in_cart size: ",String.valueOf(MenuActivity.foods_in_cart.size()));
                //Toast.makeText(getContext(),MenuActivity.foods_in_cart.size(),Toast.LENGTH_SHORT).show();

            }
        });


        return ListViewSingle;
    }
}
