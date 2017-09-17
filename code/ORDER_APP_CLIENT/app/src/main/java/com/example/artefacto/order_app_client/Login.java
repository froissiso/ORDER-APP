package com.example.artefacto.order_app_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText textRestaurantName;
    private EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
        Intent intent = new Intent(this, MainActivity.class);
        // Start Activity and get feedback.
        if(restaurantName.equals("Maria") && password.equals("1234")){
            this.startActivity(intent);
        }  else if (restaurantName.equals("Fran") && password.equals("1234")){
            this.startActivity(intent);
        } else if (restaurantName.equals("Beñat") && password.equals("1234")){
            this.startActivity(intent);
        }  else if (restaurantName.equals("Miguel") && password.equals("1234")){
            this.startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Wrong user or password, please try again", Toast.LENGTH_SHORT).show();
        }
    }

}
