package rucafe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of orders in the cafe application.
 * Allows for adding, removing, and retrieving orders.
 * @author Jay,Andres
 */
public class OrderList {
    private List<Order> orders;
    /**
     * Constructs an OrderList with an empty list of orders.
     */
    private OrderList(){
        orders = new ArrayList<>();
    }
    private static OrderList sOrderList; //empty Singleton object for OrderList

    /**
     * Creates singleton object if not created otherwise returns singleton object
     * @return OrderList singleton object
     */
    public static OrderList get(){
        if(sOrderList == null){
            sOrderList = new OrderList();
        }
        return sOrderList;
    }

    /**
     * Adds an order to the list.
     *
     * @param order The order to be added.
     * @return true if the order is added successfully, false otherwise.
     */
    public boolean addToOrderList(Order order) {
        return orders.add(order);
    }

    /**
     * Removes an order from the list.
     *
     * @param order The order to be removed.
     * @return true if the order is removed successfully, false otherwise.
     */
    public boolean removeFromOrderList(Order order) {
        return orders.remove(order);
    }

    /**
     * Retrieves a copy of the list of orders.
     *
     * @return A new list containing all the orders.
     */
    public List<Order> getOrderList() {
        return new ArrayList<>(orders);
    }

    /**
     * Gets the number of orders in the list.
     *
     * @return The size of the orders list.
     */
    public int getSize() {
        return orders.size();
    }

    /**
     * Gets the list of orders.
     *
     * PSA: This method exposes the internal list of orders. Modifications
     * to the returned list will affect the original list. Use getOrderList()
     * for a defensive copy.
     *
     * @return The list of orders.
     */
    public List<Order> getOrders() {
        return orders;
    }
}
