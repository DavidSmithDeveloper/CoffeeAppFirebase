package com.trios.coffeeappfirebase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button openPizzaMenuBtn = findViewById(R.id.pizzamenu_button);
        openPizzaMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to open the second view
                Intent intent = new Intent(MenuActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });

        Button callButton = findViewById(R.id.phone);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the phone number you want to call
                String phoneNumber = "YOUR_PHONE_NUMBER_HERE";

                // Create an intent with the ACTION_DIAL action and the phone number
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));

                // Start the phone call activity
                startActivity(intent);
            }
        });
    }
}
