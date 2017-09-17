package com.example.artefacto.order_app_client;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fjrois on 15/11/16.
 */
public class PlacesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String EXTRA_PARAM_ID = "ID";//Info from the Intent done from MainActivity
    private GridView gridView;
    private PlacesAdapter adapter;
    public static PlacesDB db;
    public static List<Place> list;
    private static Integer flagDBcreated = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);


        useToolbar();

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new PlacesAdapter(this, generatePlacesData(getApplicationContext()));
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    public static void fillDB(PlacesDB db) {

        db.addPlace(new Place("Place number 1", Integer.toString(R.drawable.image1)));
        db.addPlace(new Place("Place number 2", Integer.toString(R.drawable.image2)));
        db.addPlace(new Place("Place number 3", Integer.toString(R.drawable.image3)));
        db.addPlace(new Place("Place number 4", Integer.toString(R.drawable.image4)));
        db.addPlace(new Place("Place number 5", Integer.toString(R.drawable.image5)));
        db.addPlace(new Place("Place number 6", Integer.toString(R.drawable.image6)));
        db.addPlace(new Place("Place number 7", Integer.toString(R.drawable.image7)));
        db.addPlace(new Place("Place number 8", Integer.toString(R.drawable.image8)));
        db.addPlace(new Place("Place number 9", Integer.toString(R.drawable.image9)));
        db.addPlace(new Place("Place number 10", Integer.toString(R.drawable.image10)));

        // get all books
        list = db.getAllPlaces();
    }

    public static ArrayList<Place> generatePlacesData(Context context) {

        db = new PlacesDB(context);
        /** CRUD Operations **/
        if (flagDBcreated == 0) {
            flagDBcreated = 1;
            fillDB(db);
        } else {

        }

        list = db.getAllPlaces();

        return convertList2ArrayList(list);
    }

    public static ArrayList<Place> convertList2ArrayList(List list) {
        ArrayList<Place> arrayList = new ArrayList<Place>(list);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Place p = (Place) iterator.next();
        }
        return arrayList;
    }

    private void useToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Place place = (Place) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, PlacesActivityDetail.class);
        intent.putExtra(PlacesActivityDetail.EXTRA_PARAM_ID, position);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptionsCompat activityOptions =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this,
                            new Pair<View, String>(view.findViewById(R.id.image_place),
                                    PlacesActivityDetail.VIEW_NAME_HEADER_IMAGE)
                    );

            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        } else
            startActivity(intent);
    }

}
