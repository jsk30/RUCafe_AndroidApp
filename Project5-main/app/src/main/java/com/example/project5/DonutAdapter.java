package com.example.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rucafe.Donut;
import rucafe.MenuItem;
import rucafe.Order;

/**
 * DonutAdapter to be used for the Donut RecyclerView
 * @author Jay, Andre
 */
public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder> {
    private Context context; //need the context to inflate the layout
    private ArrayList<Donut> items; //need the data binding to each row of RecyclerView
    private final Integer[] quantities = {1, 2, 3, 4, 5};

    /**
     * Parametized constructor for the DonutAdapter
     * @param context
     * @param items
     */
    public DonutAdapter(Context context, ArrayList<Donut> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DonutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new DonutHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of DonutHolder
     * @param position the index of the Donut in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull DonutHolder holder, int position) {
        //assign values for each row
        Donut donut = items.get(position);
        holder.tv_name.setText(items.get(position).toString());
        String temp = "$" + items.get(position).price();
        holder.tv_price.setText(temp);
        holder.im_item.setImageResource(items.get(position).getImage());
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, quantities);
        holder.spinnerQuantity.setAdapter(quantityAdapter);

        holder.btn_add.setOnClickListener(view -> holder.addDonutToOrder(donut));
        holder.currentDonut = items.get(position);
        holder.updatePriceDisplay( 1);//default
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * ViewHolder for displaying Donut in RecyclerView
     */
    public static class DonutHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Button btn_add;
        private Donut currentDonut;

        private Spinner spinnerQuantity;
        private ConstraintLayout parentLayout;

        /**
         * Donut Holder constructor for initializing views.
         * @param itemView
         */
        public DonutHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            spinnerQuantity = itemView.findViewById(R.id.spin_quant);
            parentLayout = itemView.findViewById(R.id.rowLayout);

            spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    int quantity = (int) adapterView.getItemAtPosition(position);
                    updatePriceDisplay(quantity);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        /**
         * Updates the price shown in Donut Recycler view
         * @param quantity quantity selected in spinner
         */
        private void updatePriceDisplay(int quantity) {
            if (currentDonut != null) {
                double price = currentDonut.price() * quantity;
                tv_price.setText(String.format("$%.2f", price));
            }
        }

        /**
         * adds the Donut in current Order
         * @param donut donut to be added.
         */
        public void addDonutToOrder(Donut donut) {
            AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
            alert.setTitle("Add to order");
            int quantity = Integer.parseInt(spinnerQuantity.getSelectedItem().toString());
            donut.setQuantity(quantity);
            alert.setMessage("Add " + donut.toString() + " to your order?");

            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    int quantity = Integer.parseInt(spinnerQuantity.getSelectedItem().toString());
                    donut.setQuantity(quantity);
                    Order.get().addItem(donut);
                    Toast.makeText(itemView.getContext(), donut.toString() + " added to your order.", Toast.LENGTH_LONG).show();
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    donut.setQuantity(Integer.parseInt(spinnerQuantity.getSelectedItem().toString()));
                    Toast.makeText(itemView.getContext(), donut.toString() + " not added.", Toast.LENGTH_LONG).show();
                }
            });

            AlertDialog dialog = alert.create();
            dialog.show();
        }

    }
}
