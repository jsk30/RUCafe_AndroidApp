package com.example.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.List;
import rucafe.*;

/**
 * Item Adapter for the ListView for current orders
 * @author Jay, Andre
 */
public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    private List<MenuItem> curr;
    private final int NONE_SELECTED = -1;
    private int selectedPosition = NONE_SELECTED;

    /**
     * Parametized Constructor for MenuItemAdapter
     * @param context current context
     * @param items List of menu items
     */
    public MenuItemAdapter(Context context, List<MenuItem> items) {
        super(context, 0, items);
        curr = items;
    }

    /**
     * Gets a view for that displays data of menuItem in current order.
     * @param position The position of the item within the adapter's data set of the item whose view
     *        we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to
     * @return View of data of MenuItem at position
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.curr_listview, parent, false);
        }

        MenuItem item = getItem(position);
        TextView itemName = convertView.findViewById(R.id.textViewItemName);
        TextView itemPrice = convertView.findViewById(R.id.textViewItemPrice);
        if (item instanceof Donut) {itemName.setText(((Donut) item).toStringNoQuant());}
        else { itemName.setText(item.toString());}
        itemName.setText(item.toString());
        itemPrice.setText(String.format("$%.2f", item.price()));

        if (position == selectedPosition) {
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.selected_item));
        } else {
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.normal_item));
        }

        return convertView;
    }

    /**
     * Listener for selecting a position in ListView
     * @param position
     */
    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    /**
     * Removes item at the position of the ListView.
     * @param position position selected form ListView
     */
    public void removeItem(int position) {
        if (position >= 0 && position < getCount()) {
            this.curr.remove(position);
            Toast.makeText(MenuItemAdapter.this.getContext(), "Item removed", Toast.LENGTH_SHORT).show();
            if (selectedPosition == position) {
                selectedPosition = NONE_SELECTED;
            } else if (selectedPosition > position) {
                selectedPosition = NONE_SELECTED;
            }
            notifyDataSetChanged();
        }
    }

    /**
     * gets the selected position in the ListView
     * @return the selected position
     */
    public int getSelectedPosition(){
        return this.selectedPosition;
    }

}



