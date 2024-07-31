package rucafe;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project5.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {
    public OrderAdapter(Context context, List<Order> orders) {
        super(context, 0, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item_layout, parent, false);
        }

        Order order = getItem(position);

        TextView orderNumberTextView = convertView.findViewById(R.id.orderNumberTextView);
        TextView orderSubtotalTextView = convertView.findViewById(R.id.orderSubtotalTextView);
        Button removeOrderButton = convertView.findViewById(R.id.removeOrderButton);

        orderNumberTextView.setText("Order #" + order.getOrderNumber());
        orderSubtotalTextView.setText(String.format("Subtotal: $%.2f", order.calculateTotal()));

        removeOrderButton.setOnClickListener(v -> {
            OrderList.get().removeFromOrderList(order);
            this.remove(order);
            notifyDataSetChanged();
            Toast.makeText(getContext(), "Order Removed", Toast.LENGTH_SHORT).show();
        });

        orderNumberTextView.setOnClickListener(v -> {
            showOrderDetailsDialog(order, getContext());
        });

        return convertView;
    }

    private void showOrderDetailsDialog(Order order, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Order #" + order.getOrderNumber() + " Details");

        // Building the message showing each item and its price
        StringBuilder messageBuilder = new StringBuilder();
        for (MenuItem item : order.getItems()) {
            messageBuilder.append(item.toString()).append(" - $").append(String.format("%.2f", item.price())).append("\n");
        }
        messageBuilder.append("\nSubtotal: $").append(String.format("%.2f", order.calculateTotal()));

        builder.setMessage(messageBuilder.toString());

        builder.setPositiveButton("Close", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
