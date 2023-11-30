/**
 * MainActivity class is the activity for the Main menu GUI
 * Houses functions to navigate to other activities
 *
 * @author Udayan Rai, Garvit Gupta
 */
package com.example.rucafeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public static Order order;
    public static StoreOrders ordersList;

    /**
     * Initializes the main view GUI and the required buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        order=new Order();
        ordersList= new StoreOrders();
        ImageButton ordering_donut_button= findViewById(R.id.orderDonutButton);
        ordering_donut_button.setOnClickListener(new View.OnClickListener() {

            /**
             * Method which opens the Donuts Order Page if user clicks the correct button
             * @param view
             */
            @Override
            public void onClick(View view) {
                openDonutsPage();
            }
        });

        ImageButton order_coffee_button= findViewById(R.id.orderingCoffeeButton);
        order_coffee_button.setOnClickListener(new View.OnClickListener() {

            /**
             * Method which opens the Coffee Order Page if user clicks the correct button
             * @param view
             */
            @Override
            public void onClick(View view) {
                openCoffeePage();
            }
        });

        Button yourOrderButton=findViewById(R.id.yourOrderButton);
        yourOrderButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Method which opens the Your Orders Page if user clicks the correct button
             * @param view
             */
            @Override
            public void onClick(View view) { openOrderPage(); }
        });

        Button storeOrdersButton= findViewById(R.id.storeOrdersButton);
        storeOrdersButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Method which opens the Store Orders Page if user clicks the correct button
             * @param view
             */
            @Override
            public void onClick(View view) {
                openStoreOrdersPage();
            }
        });
    }

    /**
     * Helper method which creates an activity to open the Donuts page
     */
    public void openDonutsPage(){
        Intent intent= new Intent(this, Ordering_Donuts_Activity.class);
        startActivity(intent);
    }
    /**
     * Helper method which creates an activity to open the Coffee page
     */
    public void openCoffeePage(){
        Intent intent= new Intent(this, Ordering_coffee_Activity.class);
        startActivity(intent);
    }
    /**
     * Helper method which creates an activity to open the Your Orders page
     */
    public void openOrderPage(){
        Intent intent= new Intent(this, Your_Order_Activity.class);
        startActivity(intent);
    }
    /**
     * Helper method which creates an activity to open the Store Orders page
     */
    public void openStoreOrdersPage(){
        Intent intent= new Intent(this, Store_Orders_Activity.class);
        startActivity(intent);
    }
}