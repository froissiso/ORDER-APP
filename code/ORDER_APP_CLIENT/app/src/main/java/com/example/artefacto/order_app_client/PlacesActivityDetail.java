package com.example.artefacto.order_app_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Activity that shows the image selected extended
 */
public class PlacesActivityDetail extends AppCompatActivity {

    public static final String EXTRA_PARAM_ID = "ID";//Info from the Intent done from PlaceActivity
    public static final String VIEW_NAME_HEADER_IMAGE = "shared_image";
    private Place detailedPlace;
    private ImageView imageExtended;
    private PlacesDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_places_activity_detail);
        int i = getIntent().getIntExtra(EXTRA_PARAM_ID,0)+1;
        db = new PlacesDB(getApplicationContext());
        detailedPlace = db.getPlaceById(String.valueOf(i));

        useToolbar();

        imageExtended = (ImageView) findViewById(R.id.image_extended);

        loadExtendedImage(String.valueOf(i));


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        imageExtended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuActivity.class);
                intent.putExtra("place_name", detailedPlace.getName()); //name to the next activity to show the proper menu
                startActivity(intent);
                Log.i("[PlacesActivityDetail]","[imageExtended] pressed");
                Log.d("Looking menu of place: ", detailedPlace.getName());
            }
        });

        Toast.makeText(PlacesActivityDetail.this,"Touch image to see menu",Toast.LENGTH_LONG).show();
    }


    private void loadExtendedImage(String id) {
        /*
        Glide.with(imageExtended.getContext())
                .load(detailedPlace.getIdDrawable())
                .into(imageExtended);
                */

        imageExtended.setImageResource(Integer.parseInt(db.getPlaceById(id).getIdDrawable()));
    }

    private void useToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toolbar.setTitle(detailedPlace.getName());
        setSupportActionBar(toolbar);

    }

}