package com.example.artefacto.order_app_client;

//import android.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjrois on 16/11/16.
 */
public class menu_fragment_1 extends Fragment {

    public static menu_fragment_1 newInstance(){
        menu_fragment_1 frag = new menu_fragment_1();
        return frag;
    }

    public menu_fragment_1(){}

    private ListView listView;
    private ListAdapter listAdapter;
    private List<Product> listOfDrinks;
    private List<String> listOfDrinks_names;
    private List<String> listOfDrinkDrawables;

    private static SqlProducts sqlProducts;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_fragment_1, container, false);
        /*
        listOfDrinks = new ArrayList<String>();
        listOfDrinks.add("Corona");
        listOfDrinks.add("Red wine");
        listOfDrinks.add("White wine");
        listOfDrinks.add("Budweiser");
        listOfDrinks.add("Bud light");
        listOfDrinks.add("Coca Cola");
        listOfDrinks.add("Dr.Pepper");
        listOfDrinks.add("Water");
        listOfDrinks.add("Fanta");
        listOfDrinks.add("Sprite");
        for(int i = 1;i<22;i++){
            listOfDrinks.add("Drink "+i);
        }
        */

        //Get Drink products from main products list
        listOfDrinks = new ArrayList<Product>();

        listOfDrinks = MenuActivity.dbProducts.getAllDrinks();
       /*
        for(String product_name: MenuActivity.menu_products.keySet()){
            Product product = MenuActivity.menu_products.get(product_name);
            if(product.getType()=="Drink"){
                listOfDrinks.add(product);
            }
        }
*/
        //Get names of products from drinks list
        listOfDrinks_names = new ArrayList<String>();

       /*
        for(Product drink : listOfDrinks){
            listOfDrinks_names.add(drink.getName());
        }
*/
        listOfDrinks_names = MenuActivity.dbProducts.getAllDrinkNames();

        listOfDrinkDrawables = new ArrayList<String>();
        listOfDrinkDrawables = MenuActivity.dbProducts.getAllDrawables("drink");

        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new menu_fragment_1_CustomListAdapter(getActivity(), listOfDrinks_names, listOfDrinkDrawables);
        listView.setAdapter(listAdapter);

        return rootView;
    }
}
