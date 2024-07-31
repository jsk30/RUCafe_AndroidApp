package com.example.project5;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import rucafe.Order;
import rucafe.OrderAdapter;
import rucafe.OrderList;

/**
 * Activity class for displaying all orders in a ListView.
 * This activity fetches the list of orders from the OrderList and
 * uses an OrderAdapter to populate a ListView with the current orders.
 * @author Jay,Andres
 */
public class allOrdersActivity extends AppCompatActivity {
    private ListView allOrdersListView;
    private OrderAdapter orderAdapter; // The adapter for the list of orders

    /**
     * Initializes the activity, sets its content view, and updates the list of orders.
     * The method fetches the current order list and initializes the ListView
     * with an OrderAdapter.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data
     *                           it most recently supplied in onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

        allOrdersListView = findViewById(R.id.allOrdersListView);
        updateOrderList();

    }

    /**
     * Fetches the current list of orders and updates the ListView to display these orders.
     * It creates a new instance of OrderAdapter with the latest list of orders
     * and sets it as the adapter for the allOrdersListView.
     */
    private void updateOrderList() {
        List<Order> orders = OrderList.get().getOrderList();
        orderAdapter = new OrderAdapter(this, orders);
        allOrdersListView.setAdapter(orderAdapter);
    }
}
