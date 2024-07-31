package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import rucafe.Coffee;
import rucafe.Order;

/**
 * Activity class for customizing and adding a coffee order.
 * Allows users to select coffee size, quantity, and add-ins like Sweet Cream, French Vanilla, etc.,
 * and add the customized coffee to the current order. The subtotal for the current selection
 * is displayed and updated dynamically as the user makes changes.
 * @author Jay,Andres
 */
public class CoffeeActivity extends AppCompatActivity {

    private Spinner spinnerSize, spinnerQuantity;
    private CheckBox checkBoxSC, checkBoxFV, checkBoxIC, checkBoxM, checkBoxC;
    private TextView coffeeSubtotal;
    private Button addButton;
    private Order currentOrder;
    private final String[] coffeeSizes = {"Short", "Tall", "Grande", "Venti"};
    private final String[] quantities = {"1", "2", "3", "4", "5"};

    /**
     * Called when the activity is starting. Initializes the activity, UI components, and event listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        // Initialize the Order
        currentOrder = Order.get();

        // Initialize UI Components
        spinnerSize = findViewById(R.id.spinnerCoffeeSize);
        spinnerQuantity = findViewById(R.id.spinnerCoffeeQuantity);
        checkBoxSC = findViewById(R.id.checkBoxSweetCream);
        checkBoxFV = findViewById(R.id.checkBoxFrenchVanilla);
        checkBoxIC = findViewById(R.id.checkBoxIrishCream);
        checkBoxM = findViewById(R.id.checkBoxMocha);
        checkBoxC = findViewById(R.id.checkBoxCaramel);
        coffeeSubtotal = findViewById(R.id.coffeeSubtotalText);
        addButton = findViewById(R.id.addCoffeeToOrder);

        Coffee initialCoffee = new Coffee("Short", false, false, false, false, false, 1);
        updateSubtotal(initialCoffee.price());

        // Setup Spinners
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, coffeeSizes);
        spinnerSize.setAdapter(sizeAdapter);

        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, quantities);
        spinnerQuantity.setAdapter(quantityAdapter);

        // Add to Order Button Click Listener
        addButton.setOnClickListener(view -> onAddOrder());

        // Listener for size and quantity spinners
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculateAndUpdatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };
        spinnerSize.setOnItemSelectedListener(spinnerListener);
        spinnerQuantity.setOnItemSelectedListener(spinnerListener);

        // Listener for checkboxes
        CompoundButton.OnCheckedChangeListener checkBoxListener = (buttonView, isChecked) -> calculateAndUpdatePrice();
        checkBoxSC.setOnCheckedChangeListener(checkBoxListener);
        checkBoxFV.setOnCheckedChangeListener(checkBoxListener);
        checkBoxIC.setOnCheckedChangeListener(checkBoxListener);
        checkBoxM.setOnCheckedChangeListener(checkBoxListener);
        checkBoxC.setOnCheckedChangeListener(checkBoxListener);

        // Initialize the subtotal display
        calculateAndUpdatePrice();
    }

    /**
     * Handles the action for the 'Add to Order' button click.
     * Creates a Coffee object based on the selected options, adds it to the current order,
     * and displays a confirmation message.
     */
    private void onAddOrder() {
        String size = spinnerSize.getSelectedItem().toString();
        int quantity = Integer.parseInt(spinnerQuantity.getSelectedItem().toString());
        boolean sC = checkBoxSC.isChecked();
        boolean fV = checkBoxFV.isChecked();
        boolean iC = checkBoxIC.isChecked();
        boolean m = checkBoxM.isChecked();
        boolean c = checkBoxC.isChecked();

        Coffee coffee = new Coffee(size, sC, fV, iC, m, c, quantity);
        currentOrder.addItem(coffee);
        updateSubtotal(coffee.price());

        Toast.makeText(CoffeeActivity.this, "Coffee added to your order!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Calculates the price of the currently selected coffee options and updates the subtotal displayed.
     */
    private void calculateAndUpdatePrice() {
        String size = spinnerSize.getSelectedItem().toString();
        int quantity = Integer.parseInt(spinnerQuantity.getSelectedItem().toString());
        boolean sC = checkBoxSC.isChecked();
        boolean fV = checkBoxFV.isChecked();
        boolean iC = checkBoxIC.isChecked();
        boolean m = checkBoxM.isChecked();
        boolean c = checkBoxC.isChecked();

        Coffee coffee = new Coffee(size, sC, fV, iC, m, c, quantity);
        updateSubtotal(coffee.price());
    }

    /**
     * Updates the subtotal TextView with the given price.
     * @param price The price to display in the subtotal TextView.
     */
    private void updateSubtotal(double price) {
        coffeeSubtotal.setText(String.format("Subtotal: $%.2f", price));
    }
}
