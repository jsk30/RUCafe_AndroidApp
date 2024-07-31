package com.example.project5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import rucafe.*;

/**
 * Activity for Current Orders
 * @author Jay, Andre
 */
public class currentOrderActivity extends AppCompatActivity {
    private TextView subtotalText, totalText, salesTaxText;
    private ListView currListView;
    private MenuItemAdapter adapter;
    private final int NONE_SELECTED = -1;
    private Button deleteButton;
    private Button finalizeButton;
    private double SALES_TAX_RATE = 0.06625;
    private double subtotal;
    private double salesTax;

    /**
     * Initializes for current order activity.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_current_orders);
        subtotalText = findViewById(R.id.subtotalTextView);
        totalText = findViewById(R.id.totalTextView);
        salesTaxText = findViewById(R.id.salesTaxTextView);
        currListView = findViewById(R.id.currListView);
        deleteButton = findViewById(R.id.bt_delete);
        finalizeButton = findViewById(R.id.bt_finalize);
        adapter = new MenuItemAdapter(this, Order.get().getItems());
        currListView.setAdapter(adapter);
        updateSubtotal();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currentOrderActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        currListView.setOnItemClickListener((parent, view, position, id) -> adapter.setSelectedPosition(position));
        deleteButton.setOnClickListener(v -> {
            int selectedPosition = adapter.getSelectedPosition();
            if (selectedPosition == NONE_SELECTED) {
                showAlert("No Item Selected", "Please select an item to delete.");
            } else {
                deleteSelectedItem(selectedPosition);
            }
        });

    }

    /**
     * updates subtotal and items relating to subtotal
     */
    private void updateSubtotal() {
        subtotal = Order.get().calculateTotal();
        salesTax = subtotal * SALES_TAX_RATE;
        salesTaxText.setText(String.format("Subtotal: $%.2f", salesTax));
        subtotalText.setText(String.format("Subtotal: $%.2f", subtotal));
        totalText.setText(String.format("Subtotal: $%.2f", subtotal + salesTax));
    }

    /**
     * Creates an alert
     * @param title title of the alert
     * @param message message in the alert
     */
    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    /**
     * removes Item at the selected position from singleton object and adapter.
     * @param position position selected in listview
     */
    private void deleteSelectedItem(int position) {
        Order.get().removeItem(position);
        adapter.removeItem(position);
        adapter.setSelectedPosition(NONE_SELECTED); // Reset selection
        adapter.notifyDataSetChanged();
        updateSubtotal();
    }

    /**
     * Finalizes the order and sends it to the OrderList singleton object. Then resets order.
     * @param view view that was selected
     */
    public void finalizeOrder(View view){
        if(!Order.get().getItems().isEmpty()) {
            OrderList.get().addToOrderList(Order.get());
            Order.resetOrder();
            Toast.makeText(currentOrderActivity.this, "Current Order Finalized.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            showAlert("No Order to Finalize.", "Please create an Order to Finalize.");
        }
    }

}