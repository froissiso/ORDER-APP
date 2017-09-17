package com.example.artefacto.order_app_client;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {

    //Main information resources for MenuActivity and fragments
    public static ArrayList<String> typesOfProducts_in_cart;
    public static Map<String,Product> menu_products;
    //public static ArrayList<Product> products_in_cart;
    public static ArrayList<Product> products_in_cart;
    public static ArrayList<Product> drinks_in_cart;
    public static ArrayList<Product> foods_in_cart;

    public static SqlProducts dbProducts;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Slide to see the other sections >>", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });



        //Get place name from intent
        String placeName = getIntent().getStringExtra("place_name");
        Log.d("Place name MenuActivity",placeName);
        //Initialize empty lists with products in cart
        products_in_cart = new ArrayList<Product>();
        drinks_in_cart = new ArrayList<Product>();
        foods_in_cart = new ArrayList<Product>();
        //Look up menu from place in database by name

        //Temporal data construction while database is not available

        String drinkType = "drink";
        menu_products = new LinkedHashMap<String,Product>();
        Product product1 = new Product("Coronita",drinkType,"coronita",1.43);
        Product product2 = new Product("Red wine",drinkType,"red_wine",1.43);
        Product product3 = new Product("White wine",drinkType,"white_wine",1.43);
        Product product4 = new Product("Budweiser",drinkType,"budweiser",1.43);
        Product product5 = new Product("Bud light",drinkType,"bud_light",1.43);
        Product product6 = new Product("Coca Cola",drinkType,"cocacola",1.43);
        Product product7 = new Product("Dr.Pepper",drinkType,"dr_pepper",1.43);
        Product product8 = new Product("Water",drinkType,"water",1.43);
        Product product9 = new Product("Fanta",drinkType,"fanta",1.43);
        Product product10 = new Product("Sprite",drinkType,"sprite",1.43);

        dbProducts = new SqlProducts(getApplicationContext());

        dbProducts.addProduct(product1);
        dbProducts.addProduct(product2);
        dbProducts.addProduct(product3);
        dbProducts.addProduct(product4);
        dbProducts.addProduct(product5);
        dbProducts.addProduct(product6);
        dbProducts.addProduct(product7);
        dbProducts.addProduct(product8);
        dbProducts.addProduct(product9);
        dbProducts.addProduct(product10);

        menu_products.put(product1.getName(),product1);
        menu_products.put(product2.getName(),product2);
        menu_products.put(product3.getName(),product3);
        menu_products.put(product4.getName(),product4);
        menu_products.put(product5.getName(),product5);
        menu_products.put(product6.getName(),product6);
        menu_products.put(product7.getName(),product7);
        menu_products.put(product8.getName(),product8);
        menu_products.put(product9.getName(),product9);
        menu_products.put(product10.getName(),product10);

        String foodType = "food";

        Product product11 = new Product("Burger", foodType, "burger", 79.95);
        Product product12 = new Product("Guacamole", foodType, "guacamole",6.25);
        Product product13 = new Product("Nachos", foodType, "nachos",6.25);
        Product product14 = new Product("Hot Dog", foodType, "hotdog",6.25);
        Product product15 = new Product("Pizza", foodType, "pizza",6.25);
        Product product16 = new Product("Burrito", foodType, "burrito",6.25);
        Product product17 = new Product("Chicken Wings", foodType, "chicken_wings",6.25);
        Product product18 = new Product("Fries", foodType, "fries",6.25);
        Product product19 = new Product("Onion rings", foodType, "onion_rings",6.25);
        Product product20 = new Product("Sushi", foodType, "sushi",6.25);


        dbProducts.addProduct(product11);
        dbProducts.addProduct(product12);
        dbProducts.addProduct(product13);
        dbProducts.addProduct(product14);
        dbProducts.addProduct(product15);
        dbProducts.addProduct(product16);
        dbProducts.addProduct(product17);
        dbProducts.addProduct(product18);
        dbProducts.addProduct(product19);
        dbProducts.addProduct(product20);


        menu_products.put(product11.getName(),product12);
        menu_products.put(product12.getName(),product11);
        menu_products.put(product13.getName(),product13);
        menu_products.put(product14.getName(),product14);
        menu_products.put(product15.getName(),product15);
        menu_products.put(product16.getName(),product16);
        menu_products.put(product17.getName(),product17);
        menu_products.put(product18.getName(),product18);
        menu_products.put(product19.getName(),product19);
        menu_products.put(product20.getName(),product20);





        //Initialize types of products in cart
        typesOfProducts_in_cart = new ArrayList<String>();

    }

    public void addProduct2Cart(Product product){
        products_in_cart.add(product);
    }
    public void addDrink2Cart(Product product){
        drinks_in_cart.add(product);
    }
    public void addFood2Cart(Product product){
        foods_in_cart.add(product);
    }
    public ArrayList<String> getTypesOfProducts_in_cart(){
        Log.i("IN CART SIZE!: ", String.valueOf(products_in_cart.size()));
        for(Product p: products_in_cart){
            Log.d("Type trying to add: ",p.getType());
            if(!typesOfProducts_in_cart.contains(p.getType())){
                typesOfProducts_in_cart.add(p.getType());
                Log.i("Type of product added: ",p.getType());
            }
        }
        return typesOfProducts_in_cart;
    }
    public void deleteProductFromCart(String productName){
        Product product = menu_products.get(productName);
        products_in_cart.remove(product);
        if(product.getType().equals("drink")){
            drinks_in_cart.remove(product);
        }
        else if(product.getType().equals("food")){
            foods_in_cart.remove(product);
        }
        Log.i("Product removed cart", productName);
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).


            switch(position){
                case 0: return menu_fragment_1.newInstance();
                case 1: return menu_fragment_2.newInstance();
                case 2: return menu_fragment_3.newInstance();
                case 3: return menu_fragment_4.newInstance();
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }
}
