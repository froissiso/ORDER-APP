package com.example.artefacto.order_app_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fjrois on 19/11/16.
 */

public class menu_fragment_4 extends Fragment {

    public static menu_fragment_4 newInstance(){
        menu_fragment_4 frag = new menu_fragment_4();
        return frag;
    }

    public menu_fragment_4(){}

    private Button button1;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mapCollection;
    ExpandableListView expandableListView;
    public SqlClient dbCl;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.menu_fragment_4, container, false);

        button1 = (Button) rootView.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(view.getContext(),"Your order will be ready soon", Toast.LENGTH_SHORT).show();

                String order_chain = "";

                int n_drinks_in_cart = MenuActivity.drinks_in_cart.size();
                Log.i("Num. of drinks in cart",String.valueOf(n_drinks_in_cart));

                dbCl = new SqlClient(getContext());

                order_chain += "Miguel/3120001122/";
                for(Product product: MenuActivity.drinks_in_cart){
                    order_chain += product.getName();
                    n_drinks_in_cart -= 1;

                    if(n_drinks_in_cart != 0){
                        order_chain += ";";
                    }
                }

                int n_foods_in_cart = MenuActivity.foods_in_cart.size();
                Log.i("Num. of foods in cart",String.valueOf(n_foods_in_cart));
                order_chain += "/";
                for(Product product: MenuActivity.foods_in_cart){
                    order_chain += product.getName();
                    n_foods_in_cart -= 1;

                    if(n_foods_in_cart != 0){
                        order_chain += ";";
                    }
                }
                Log.i("Order chain",order_chain);

                Intent intent = new Intent(getActivity(), WD_ClientActivitySendOrder.class);
                intent.putExtra("order_chain", order_chain);
                startActivity(intent);
            }
        });


        createGroupList();

        createCollection();

        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        final ExpandableListAdapter expListAdapter = new menu_fragment_4_CustomExpandableListAdapter(getActivity(), groupList, mapCollection);
        expandableListView.setAdapter(expListAdapter);

        //setGroupIndicatorToRight();

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(groupPosition, childPosition);
                Toast.makeText(rootView.getContext(), selected, Toast.LENGTH_LONG).show();

                return true;
            }
        });
        return rootView;
    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        /*
        groupList.add("Drinks");
        groupList.add("Food");
        groupList.add("Grupo3");
        groupList.add("Grupo4");
        groupList.add("Grupo5");
        groupList.add("Grupo6");
        */

        MenuActivity m = new MenuActivity();
        Log.d("3HERE!!!!!","TEST");

        groupList = m.getTypesOfProducts_in_cart();
        Log.d("1HERE!!!!!",String.valueOf(groupList.size()));
        Log.d("4HERE!!!!!",String.valueOf(MenuActivity.typesOfProducts_in_cart));


        for(String group:groupList){
            Log.d("Types of products: ", group);
            Log.d("2HERE!!!!!","TEST");
        }
    }

    private void createCollection() {
        // preparing drinks collection(child)
        //String[] drinks_group_childs = {"child11","child12","child13"};

        ArrayList<String> drinks_in_cart_names = new ArrayList<String>();
        for(Product drink: MenuActivity.drinks_in_cart){
            drinks_in_cart_names.add(drink.getName());
            Log.d("Drinks in cart: ",drink.getName());
        }
        String[] drinks_group_childs = new String[drinks_in_cart_names.size()];
        drinks_group_childs = drinks_in_cart_names.toArray(drinks_group_childs);
        //String chain = "";
        //for(String s: drinks_group_childs){
        //    chain+=" "+s;
        //}
        //Log.d("drinks_group_childs",chain);

        //String[] foods_group_childs = {"child21","child22"};

        ArrayList<String> foods_in_cart_names = new ArrayList<String>();
        for(Product food: MenuActivity.foods_in_cart){
            foods_in_cart_names.add(food.getName());
            Log.d("Drinks in cart: ",food.getName());
        }
        String[] foods_group_childs = new String[foods_in_cart_names.size()];
        foods_group_childs = foods_in_cart_names.toArray(foods_group_childs);

        String[] group3_childs = {"child31","child32","child33","child34","child35"};
        String[] group4_childs = {"child41","child42","child43","child44","child45"};
        String[] group5_childs = {"child51","child52","child53","child54","child55","child56","child57"};
        String[] group6_childs = {"child61","child62","child63"};

        mapCollection = new LinkedHashMap<String, List<String>>();

        for (String group : groupList) {
            if (group.equals("drink")) {
                loadChild(drinks_group_childs);
            } else if (group.equals("food"))
                loadChild(foods_group_childs);
            else if (group.equals("Grupo3"))
                loadChild(group3_childs);
            else if (group.equals("Grupo4"))
                loadChild(group4_childs);
            else if (group.equals("Grupo5"))
                loadChild(group5_childs);
            else
                loadChild(group6_childs);

            mapCollection.put(group, childList);
            Log.d("groupList",String.valueOf(groupList));
            Log.d("childList",String.valueOf(childList));

        }
    }

    private void loadChild(String[] group) {
        childList = new ArrayList<String>();
        for (String child : group)
            childList.add(child);
    }
    /*
    private void setGroupIndicatorToRight() {
        // Get the screen width
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }
    */

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
}

