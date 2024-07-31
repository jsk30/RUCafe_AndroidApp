package rucafe;

/**
 * Represents a donut, a specific type of {@link MenuItem} in the cafe.
 * Donuts have a type, flavor, and quantity, which determine their price.
 * @author Jay,Andres
 */
public class Donut extends MenuItem {
    private String type;
    private int quantity;
    private String flavor;
    private int image;
    private static final double YEAST_DONUT_PRICE = 1.79;
    private static final double CAKE_DONUT_PRICE = 1.89;
    private static final double DONUT_HOLE_PRICE = 0.39;

    /**
     * Constructs a Donut with the specified type, flavor, and quantity.
     * @param type the type of the donut (e.g., Yeast, Cake, Donut Hole).
     * @param flavor the flavor of the donut.
     */
    public Donut(String type, String flavor, int image) {
        this.type = type;
        this.flavor = flavor;
        this.image = image;
        this.quantity = 1;
    }

    /**
     * sets the quantity in donut
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the price of the donut based on its type, flavor, and quantity.
     * @return the total price of the donuts.
     */
    @Override
    public double price() {
        double unitPrice = switch (type) {
            case "Yeast" -> YEAST_DONUT_PRICE;
            case "Cake" -> CAKE_DONUT_PRICE;
            case "Donut Hole" -> DONUT_HOLE_PRICE;
            default -> 0.0;
        };
        return unitPrice*quantity;
    }

    /**
     * gets image associated with donut
     * @return the id of the image
     */
    public int getImage() {
        return image;
    }

    /**
     * returns the String in a format without quantity
     * @return a string of type and flavor
     */
    public String toStringNoQuant() {
        return String.format("Type: %s, Flavor: %s",
                this.type, this.flavor);
    }

    /**
     * Retrieves a string of donuts type, flavor, and quantity.
     * @return a string of type, flavor, and quantity
     */
    @Override
    public String toString() {
        return String.format("Type: %s, Flavor: %s, Quantity: %s",
                this.type, this.flavor, this.quantity);
    }
    
}
