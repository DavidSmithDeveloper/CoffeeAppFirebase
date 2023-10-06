package com.trios.coffeeappfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private static final double SMALL_PIZZA_PRICE = 8.00;
    private static final double MEDIUM_PIZZA_PRICE = 10.00;
    private static final double LARGE_PIZZA_PRICE = 12.00;
    private static final double PEPPERONI_TOPPING_PRICE = 1.75;
    private static final double MUSHROOMS_TOPPING_PRICE = 1.50;
    private static final double GREEN_PEPPERS_TOPPING_PRICE = 1.25;
    private static final double COCA_COLA_PRICE = 1.50;
    private static final double SPRITE_PRICE = 1.50;
    private static final double ROOT_BEER_PRICE = 1.75;

    private RadioButton radioSmall, radioMedium, radioLarge;
    private RadioButton radioPepperoni, radioMushrooms, radioGreenPeppers;
    private RadioButton radioCocaCola, radioSprite, radioRootBeer;
    private RadioGroup radioGroupSize, radioGroupToppings, radioGroupDrinks;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        radioGroupSize = findViewById(R.id.radioGroupSize);
        radioGroupToppings = findViewById(R.id.radioGroupToppings);
        radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
        checkoutButton = findViewById(R.id.checkoutButton);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String size = "";

                int selectedSizeId = radioGroupSize.getCheckedRadioButtonId();
                if (selectedSizeId != -1) {
                    RadioButton selectedSizeRadioButton = findViewById(selectedSizeId);
                    size = selectedSizeRadioButton.getText().toString();
                }

                StringBuilder toppingsText = new StringBuilder("Selected Toppings: ");

                int selectedToppingsId = radioGroupToppings.getCheckedRadioButtonId();
                if (selectedToppingsId != -1) {
                    RadioButton selectedToppingsRadioButton = findViewById(selectedToppingsId);
                    toppingsText.append(selectedToppingsRadioButton.getText()).append(", ");
                }

                StringBuilder drinksText = new StringBuilder("Selected Drinks: ");

                int selectedDrinksId = radioGroupDrinks.getCheckedRadioButtonId();
                if (selectedDrinksId != -1) {
                    RadioButton selectedDrinksRadioButton = findViewById(selectedDrinksId);
                    drinksText.append(selectedDrinksRadioButton.getText()).append(", ");
                }

                double totalPrice = calculateTotalPrice(size, toppingsText.toString(), drinksText.toString());

                String selectedPizzaDetails = "Selected Pizza Size: " + size + "\n" +
                        toppingsText.toString() + "\n" +
                        drinksText.toString() + "\n" +
                        "Total Price: $" + String.format("%.2f", totalPrice);

                Intent cartIntent = new Intent(OrderActivity.this, CartActivity.class);
                cartIntent.putExtra("pizzaDetails", selectedPizzaDetails);
                cartIntent.putExtra("pizzaTotalPrice", totalPrice);

                startActivity(cartIntent);
            }
        });
    }

    private double calculateTotalPrice(String size, String toppingsText, String drinksText) {
        double sizePrice = 0.00;

        if ("Small".equals(size)) {
            sizePrice = SMALL_PIZZA_PRICE;
        } else if ("Medium".equals(size)) {
            sizePrice = MEDIUM_PIZZA_PRICE;
        } else if ("Large".equals(size)) {
            sizePrice = LARGE_PIZZA_PRICE;
        }

        double toppingsPrice = 0.00;

        if (toppingsText.contains("Pepperoni")) {
            toppingsPrice += PEPPERONI_TOPPING_PRICE;
        }

        if (toppingsText.contains("Mushrooms")) {
            toppingsPrice += MUSHROOMS_TOPPING_PRICE;
        }

        if (toppingsText.contains("Green Peppers")) {
            toppingsPrice += GREEN_PEPPERS_TOPPING_PRICE;
        }

        double drinksPrice = 0.00;

        if (drinksText.contains("Coca-Cola")) {
            drinksPrice += COCA_COLA_PRICE;
        }

        if (drinksText.contains("Sprite")) {
            drinksPrice += SPRITE_PRICE;
        }

        if (drinksText.contains("Root Beer")) {
            drinksPrice += ROOT_BEER_PRICE;
        }

        return sizePrice + toppingsPrice + drinksPrice;
    }
}
