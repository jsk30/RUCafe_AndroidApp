package com.example.project5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rucafe.*;

/**
 * Activity for Donut Orders
 * @author Jay, Andre
 */
public class DonutActivity extends AppCompatActivity {
    private ArrayList<Donut> items = new ArrayList<>();

    private int [] itemImages = {R.drawable.choco, R.drawable.glazed, R.drawable.strawberry, R.drawable.coffee, R.drawable.glaze_sprinkle, R.drawable.chocosprinkle, R.drawable.lemoncake, R.drawable.chococake, R.drawable.apple_crisp, R.drawable.dhole, R.drawable.choco_donutholes, R.drawable.coffee_dhole};

    /**
     * Initializes Donut Activity
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems();
        DonutAdapter adapter = new DonutAdapter(this, items);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * sets up the donuts to be added to a list to be ready to use in DonutAdapter.
     */
    private void setupMenuItems() {

        String [] itemType = getResources().getStringArray(R.array.itemType);
        String [] itemFlav = getResources().getStringArray(R.array.itemFlavors);

        for (int i = 0; i < itemType.length; i++) {
            items.add(new Donut(itemType[i], itemFlav[i], itemImages[i]));
        }
    }


}