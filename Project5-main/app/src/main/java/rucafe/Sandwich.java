package rucafe;

/**
 * Represents a sandwich in the cafe application, extending the MenuItem class.
 * It allows customization of bread type, protein, and various add-ons.
 * @author Jay,Andres
 */
public class Sandwich extends MenuItem {
    private String bread;
    private String protein;
    private boolean cheese;
    private boolean lettuce;
    private boolean tomato;
    private boolean onions;
    private int addOns;
    private static final int DEFAULT_ADDS = 0;
    private static final double BEEF_PRICE = 10.99;
    private static final double CHICKEN_PRICE = 8.99;
    private static final double FISH_PRICE = 9.99;
    private static final double VEGGIE_ADD_ON_PRICE = 0.30;
    private static final double CHEESE_ADD_ON_PRICE = 1.00;

    /**
     * Constructs a Sandwich object with specified protein and add-ons.
     *
     * @param protein the type of protein for the sandwich (e.g., beef, chicken, fish)
     * @param cheese whether the sandwich includes cheese
     * @param lettuce whether the sandwich includes lettuce
     * @param tomato whether the sandwich includes tomato
     * @param onions whether the sandwich includes onions
     */
    public Sandwich(String protein, boolean cheese, boolean lettuce, boolean tomato, boolean onions) {
        this.addOns = DEFAULT_ADDS;
        this.protein = protein;
        this.lettuce = lettuce;
        this.cheese = cheese;
        if(lettuce) addOns += 1;
        this.tomato = tomato;
        if(tomato) addOns += 1;
        this.onions = onions;
        if(onions) addOns += 1;

    }

    /**
     * Constructs a Sandwich object with specified bread, protein, and add-ons.
     *
     * @param bread the type of bread for the sandwich
     * @param protein the type of protein for the sandwich
     * @param cheese whether the sandwich includes cheese
     * @param lettuce whether the sandwich includes lettuce
     * @param tomato whether the sandwich includes tomato
     * @param onions whether the sandwich includes onions
     */
    public Sandwich(String bread, String protein, boolean cheese, boolean lettuce, boolean tomato, boolean onions) {
        this.addOns = DEFAULT_ADDS;
        this.bread = bread;
        this.protein = protein;
        this.lettuce = lettuce;
        this.cheese = cheese;
        if(lettuce) addOns += 1;
        this.tomato = tomato;
        if(tomato) addOns += 1;
        this.onions = onions;
        if(onions) addOns += 1;

    }

    /**
     * Calculates the total price of the sandwich, including base price and add-ons.
     *
     * @return the total price of the sandwich
     */
    @Override
    public double price() {
        double basePrice = switch (protein) {
            case "Beef" -> BEEF_PRICE;
            case "Chicken" -> CHICKEN_PRICE;
            case "Fish" -> FISH_PRICE;
            default -> 0.0;
        };
        double addOnPrice = addOns*VEGGIE_ADD_ON_PRICE;
        if(cheese) addOnPrice += CHEESE_ADD_ON_PRICE;
        return basePrice + addOnPrice;
    }

    /**
     * Returns a string representation of the sandwich, including its ingredients and add-ons.
     *
     * @return a string detailing the sandwich's composition
     */
    @Override
    public String toString() {
        // Start with the basic sandwich details
        StringBuilder sb = new StringBuilder();
        sb.append("Bread: ").append(this.bread).append(", Protein: ").append(this.protein);

        // Add details about add-ons
        if (this.cheese) sb.append(", Cheese");
        if (this.lettuce) sb.append(", Lettuce");
        if (this.tomato) sb.append(", Tomato");
        if (this.onions) sb.append(", Onions");

        return sb.toString();
    }
    
}