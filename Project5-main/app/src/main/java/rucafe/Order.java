package rucafe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Represents an order placed in the cafe, consisting of multiple menu items.
 * @author Jay,Andres
 */
public class Order implements Serializable {
    private int orderNumber;
    private static final AtomicInteger orderNumberGenerator = new AtomicInteger(0);
    private List<MenuItem> items = new ArrayList<>();

    /**
     * Constructs a new Order with a unique order number.
     */
    private Order() {
        this.orderNumber = orderNumberGenerator.incrementAndGet();
    }
    private static Order sCurrentOrder;
    /**
     * Constructs a new Order with a specific order number.
     * Useful for restoring orders from saved data.
     */
    public static Order get() {
        if (sCurrentOrder == null){
            sCurrentOrder = new Order();
        }
        return sCurrentOrder;
    }

    /**
     * Resets the current order, creating a new one.
     */
    public static synchronized void resetOrder() {
        sCurrentOrder = new Order(); // Create a new Order, resetting the old one
    }

    /**
     * Retrieves the unique order number for this order.
     * @return the order number.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Adds a {@link MenuItem} to this order.
     * @param item the menu item to be added to the order.
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    /**
     * Removes a {@link MenuItem} from this order.
     * @param item the menu item to be removed from the order.
     */
    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    /**
     * Removes a {@link MenuItem} from this order by its index.
     * @param index the index of the menu item to be removed from the order.
     */
    public void removeItem(int index) { items.remove(index); }

    /**
     * Merges another order into this one, adding all its items.
     * @param order the order whose items are to be added to this one.
     */
    public void mergeOrders(Order order) { items.addAll(order.getItems());}

    /**
     * Calculates the total price of all items in the order.
     * @return the total price of the order.
     */
    public double calculateTotal() {
        return items.stream().mapToDouble(MenuItem::price).sum();
    }

    /**
     * Retrieves a defensive copy of the list of items in this order.
     * @return a copy of the list of items in this order.
     */
    public List<MenuItem> getItems() {
        return new ArrayList<>(items); // Return a copy of the items list
    }

    /**
     * Returns a string representation of the order, including its order number.
     * @return a string describing the order.
     */
    @Override
    public String toString() {
        return "Order #" + this.orderNumber;
    }



}
