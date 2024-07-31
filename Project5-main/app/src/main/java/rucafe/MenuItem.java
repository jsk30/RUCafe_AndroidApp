package rucafe;

/**
 * Abstract class representing a menu item in a cafe. This class provides
 * a template for specific menu items, requiring them to implement a method
 * to determine their price.
 * @author Jay,Andres
 */
public abstract class MenuItem {

    /**
     * Calculates the price of the menu item.
     * @return the price of the menu item.
     */
    public abstract double price();
}

