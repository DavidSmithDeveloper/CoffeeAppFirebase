package com.trios.coffeeappfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private TextView textViewCartDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize UI elements
        textViewCartDetails = findViewById(R.id.textViewCartDetails);

        // Get data from the Intent
        Intent intent = getIntent();
        String pizzaDetails = intent.getStringExtra("pizzaDetails");
        double pizzaTotalPrice = intent.getDoubleExtra("pizzaTotalPrice", 0.0);
        String toppingsDetails = intent.getStringExtra("toppingsDetails");
        double toppingsTotalPrice = intent.getDoubleExtra("toppingsTotalPrice", 0.0);
        String dipsDetails = intent.getStringExtra("dipsDetails");
        double dipsTotalPrice = intent.getDoubleExtra("dipsTotalPrice", 0.0);

        // Create a StringBuilder to store cart details
        StringBuilder cartDetails = new StringBuilder("Cart Details:\n");

        // Append pizza details if available
        if (pizzaDetails != null && !pizzaDetails.isEmpty()) {
            cartDetails.append("Pizza Details:\n").append(pizzaDetails).append("\n");
            cartDetails.append("Pizza Price: $").append(String.format("%.2f", pizzaTotalPrice)).append("\n");
        }

        // Append toppings details if available
        if (toppingsDetails != null && !toppingsDetails.isEmpty()) {
            cartDetails.append("Toppings Details:\n").append(toppingsDetails).append("\n");
            cartDetails.append("Toppings Price: $").append(String.format("%.2f", toppingsTotalPrice)).append("\n");
        }

        // Append dips details if available
        if (dipsDetails != null && !dipsDetails.isEmpty()) {
            cartDetails.append("Dips Details:\n").append(dipsDetails).append("\n");
            cartDetails.append("Dips Price: $").append(String.format("%.2f", dipsTotalPrice)).append("\n");
        }

        // Calculate the overall total price
        double overallTotalPrice = pizzaTotalPrice + toppingsTotalPrice + dipsTotalPrice;

        // Display the overall total price
        cartDetails.append("Overall Total Price: $").append(String.format("%.2f", overallTotalPrice)).append("\n");

        // Display "Thank you for your order" message
        cartDetails.append("\nThank you for your order!");

        // Display the cart details in the TextView
        textViewCartDetails.setText(cartDetails.toString());
    }
}

