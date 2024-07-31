package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import java.util.List;

import rucafe.*;

/**
 * The main activity class that serves as the entry point for the application.
 * It initializes the main screen of the app and provides methods to navigate to different parts of the application,
 * including the coffee, donut, sandwich ordering screens, and the views for current and all orders.
 * @author Jay,Andres
 */
public class MainActivity extends AppCompatActivity {
    private Order currentOrder;
    private List<Order> orders;

    /**
     * Called when the activity is starting. This method is where the activity initialization happens.
     * It sets up the application's main interface and initializes the list of orders and the current order.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        orders = OrderList.get().getOrders();
        currentOrder = Order.get();
    }

    /**
     * A callback method executed right after onCreate().
     */
    protected void onStart() {
        super.onStart();
        System.out.println("onStart() executed");
    }

    /**
     * A callback method executed right after onStart().
     */
    protected void onResume() {
        super.onResume();
        System.out.println("onResume() executed");
    }

    /**
     * The event handler for the button click for Donut Activity
     * @param view the Android View which fired the event.
     */
    public void showDonut(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the SandwichActivity to allow the user to order sandwiches.
     * @param view The view that triggered this method call, typically a button.
    */
    public void showSandwich(View view) {
        Intent intent = new Intent(this, SandwichActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the CoffeeActivity to allow the user to order coffee.
     * @param view The view that triggered this method call, typically a button.
     */
    public void showCoffee(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the CurrentOrderActivity to display the current order.
     * @param view The view that triggered this method call, typically a button.
     */
    public void showCurrent(View view){
        Intent intent = new Intent(this, currentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the AllOrdersActivity to display all the orders.
     * @param view The view that triggered this method call, typically a button.
     */
    public void showAllOrders(View view) {
        Intent intent = new Intent(this, allOrdersActivity.class);
        startActivity(intent);
    }

}