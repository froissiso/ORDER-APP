package com.example.maria.order_app_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textRestaurantName;
    private EditText textPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textRestaurantName = (EditText)this.findViewById(R.id.text_restaurantName);
        this.textPassword = (EditText)this.findViewById(R.id.text_password);
    }
    // When '2nd Activity' completed, it sends back a feedback.
    // (If you have started it by startActivityForResult())
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

    }
    // The method is called when the user clicks on "Show Greeting" button.
    public void start(View view) {
        String restaurantName= this.textRestaurantName.getText().toString();
        String password= this.textPassword.getText().toString();
        Intent intent = new Intent(this, WD_ServerActivity.class);
        // Start Activity and get feedback.
        if(restaurantName.equals("retro") && password.equals("1234")){
            this.startActivity(intent);
        } else if (restaurantName.equals("bar") && password.equals("1234")){
            this.startActivity(intent);

        }  else if (restaurantName.equals("gotham") && password.equals("1234")){
            this.startActivity(intent);

        }  else if (restaurantName.equals("shisha") && password.equals("1234")){
            this.startActivity(intent);

        }  else if (restaurantName.equals("rock") && password.equals("1234")){
            this.startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(),"Wrong user or password, please try again", Toast.LENGTH_SHORT).show();
        }
    }

}
