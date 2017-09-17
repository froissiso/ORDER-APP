package com.example.artefacto.order_app_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by fjrois on 16/11/16.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    /*
    final String MAIN_ACTIVITY="[Main activity]";

    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        bindViews();
        bindButtons();

    }

    private void bindViews(){
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
    }
    private void bindButtons(){
        button1.setOnClickListener(new StartOnClickListener());
        button2.setOnClickListener(new StartOnClickListener());
    }

    public class StartOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button1:
                    Intent intent = new Intent(v.getContext(), PlacesActivity.class);
                    startActivity(intent);
                    Log.i(MAIN_ACTIVITY,"[Places near me] button pressed");
                    break;

                case R.id.button2:
                    //Intent intent2 = new Intent(v.getContext(), status.class);
                    //startActivity(intent2);
                    Toast.makeText(MainActivity.this,"Functionality not avaible yet",Toast.LENGTH_SHORT).show();
                    Log.i(MAIN_ACTIVITY, "[Mates] button pressed");
                    break;
            }
        }
    }
    */
    private GridView gridView;
    private MainAdapter adapter;
    private ImageView logo;

    private static SqlClient dbClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new MainAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

        logo = (ImageView) findViewById(R.id.logo);
        //Glide.with(logo.getContext()).load(R.drawable.logo).into(logo);

        dbClient = new SqlClient(getApplicationContext());
        Client c = new Client("Miguel","3120001111");
        dbClient.addClientInfo(c);
    }

    public static SqlClient getSqlClient(){
        return dbClient;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Title item = (Title) parent.getItemAtPosition(position);

        if(position == 0){
            Intent intent = new Intent(this, PlacesActivity.class);
            intent.putExtra(PlacesActivity.EXTRA_PARAM_ID, item.getId());
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this,"Functionality not available yet",Toast.LENGTH_SHORT).show();
        }

    }
}
