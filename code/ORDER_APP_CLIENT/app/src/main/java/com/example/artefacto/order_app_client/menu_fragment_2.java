package com.example.artefacto.order_app_client;

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
public class menu_fragment_2 extends Fragment {

    public static menu_fragment_2 newInstance(){
        menu_fragment_2 frag = new menu_fragment_2();
        return frag;
    }

    public menu_fragment_2(){}

    private ListView listView;
    private ListAdapter listAdapter;
    private List<Product> listOfFoods;
    private List<String> listOfFoods_names;
    private List<String> listOfFoodDrawables;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.menu_fragment_2, container, false);

        //Get Food products from main products list
        listOfFoods = new ArrayList<Product>();
        listOfFoods = MenuActivity.dbProducts.getAllFood();
        /*
        for(String product_name: MenuActivity.menu_products.keySet()){
            Product product = MenuActivity.menu_products.get(product_name);
            if(product.getType()=="Food"){
                listOfFoods.add(product);
            }
        }
*/
        //Get names of products from drinks list
        listOfFoods_names = new ArrayList<String>();

        listOfFoods_names = MenuActivity.dbProducts.getAllFoodNames();
        /*
        for(Product food : listOfFoods){
            listOfFoods_names.add(food.getName());
        }
*/

        listOfFoodDrawables = new ArrayList<String>();
        listOfFoodDrawables = MenuActivity.dbProducts.getAllDrawables("food");

        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new menu_fragment_2_CustomListAdapter(getActivity(), listOfFoods_names, listOfFoodDrawables);
        listView.setAdapter(listAdapter);

        return rootView;
    }
}