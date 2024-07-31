package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import rucafe.*;

/**
 * Activity for Sandwich Orders
 * @author Jay, Andres
 */
public class SandwichActivity extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinner2;
    private String [] breads = {"Bagel", "Sour Dough", "Wheat Toast"};
    private String [] meats = {"Beef", "Chicken", "Fish"};
    private boolean cheese;
    private boolean lettuce;
    private boolean tomato;
    private boolean onions;
    private int addOns = 0;
    private ArrayAdapter<String> breadsAdapter;
    private ArrayAdapter<String> meatsAdapter;
    private CheckBox cB_Cheese;
    private CheckBox cB_Lettuce;
    private CheckBox cB_Onions;
    private CheckBox cB_Tomato;
    private TextView sandwichTitle;
    private TextView subtotalText;
    private double subtotal = 10.99;
    private final double CHEESE_ADDON = 1.00;
    private final double VEG_ADDON = 0.30;

    /**
     * Initializes the Sandwich Activity
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);
        spinner = findViewById(R.id.spinner);
        breadsAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, breads);
        spinner.setAdapter(breadsAdapter);
        spinner2 = findViewById(R.id.spinner2);
        meatsAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, meats);
        spinner2.setAdapter(meatsAdapter);
        sandwichTitle = findViewById(R.id.sandwichTitle);
        subtotalText = findViewById(R.id.subtotalText);
        cB_Cheese = findViewById(R.id.cB_Cheese);
        cB_Cheese.setChecked(false);
        cB_Cheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     cheese = true;
                     subtotal += CHEESE_ADDON;
                     addOns += 1;
                     updateSubtotal();
                 }else{
                     cheese = false;
                     subtotal -= CHEESE_ADDON;
                     addOns -= 1;
                     updateSubtotal();
                 }
             }
        });
        cB_Lettuce = findViewById(R.id.cB_Lettuce);
        cB_Lettuce.setChecked(false);
        cB_Lettuce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lettuce = true;
                    subtotal += VEG_ADDON;
                    addOns += 1;
                    updateSubtotal();
                }else{
                    lettuce = false;
                    subtotal -= VEG_ADDON;
                    addOns -= 1;
                    updateSubtotal();
                }
            }
        });
        cB_Tomato = findViewById(R.id.cB_Tomato);
        cB_Tomato.setChecked(false);
        cB_Tomato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tomato = true;
                    subtotal += VEG_ADDON;
                    addOns += 1;
                    updateSubtotal();
                }else{
                    tomato = false;
                    subtotal -= VEG_ADDON;
                    addOns -= 1;
                    updateSubtotal();
                }
            }
        });
        cB_Onions = findViewById(R.id.cB_Onions);
        cB_Onions.setChecked(false);
        cB_Onions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    onions = true;
                    subtotal += VEG_ADDON;
                    addOns += 1;
                    updateSubtotal();
                }else{
                    onions = false;
                    addOns -= 1;
                    subtotal -= VEG_ADDON;
                    updateSubtotal();
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //event handler below
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bread = spinner.getSelectedItem().toString();
                String meat = spinner2.getSelectedItem().toString();
                System.out.println(meat);
                Sandwich temp = new Sandwich(bread, meat, cheese, lettuce, tomato, onions);
                subtotal = temp.price();
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //event handler below
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bread = spinner.getSelectedItem().toString();
                String meat = spinner2.getSelectedItem().toString();
                Sandwich temp = new Sandwich(bread, meat, cheese, lettuce, tomato, onions);
                subtotal = temp.price();
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    /**
     * updates the subtotal Text in Sandwich
     */
    private void updateSubtotal() {
        subtotalText.setText(String.format("Subtotal: $%.2f", subtotal));
    }

    /**
     * adds the sandwich to the order
     * @param view the view button that was clicked
     */
    public void onAddOrder(View view){
        String bread = spinner.getSelectedItem().toString();
        String meat = spinner2.getSelectedItem().toString();
        Sandwich temp = new Sandwich(bread, meat, cheese, lettuce, tomato, onions);
        Order.get().addItem(temp);

        Toast.makeText(SandwichActivity.this, "Sandwich added to your order!", Toast.LENGTH_SHORT).show();
    }

}