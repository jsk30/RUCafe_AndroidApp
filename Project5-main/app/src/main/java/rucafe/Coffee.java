package rucafe;

/**
 * Represents a coffee item in the cafe, allowing customization of size and add-ins.
 * @author Jay,Andres
 */
public class Coffee extends MenuItem {
    private String size;
    private int addInsCount;
    private boolean sC;
    private boolean fV;
    private boolean iC;
    private boolean m;
    private boolean c;
    private int quant;
    private static final double BASE_PRICE = 1.99;
    private static final double SIZE_UPGRADE_PRICE = 0.50;
    private static final double ADD_IN_PRICE = 0.30;

    /**
     * Constructs a Coffee object with specified parameters.
     *
     * @param size The size of the coffee.
     * @param sC Indicates if Sweet Cream is added.
     * @param fV Indicates if French Vanilla is added.
     * @param iC Indicates if Irish Cream is added.
     * @param m Indicates if Mocha is added.
     * @param c Indicates if Caramel is added.
     * @param quant The quantity of coffee.
     */
    public Coffee(String size, boolean sC, boolean fV, boolean iC, boolean m, boolean c, int quant) {
        this.size = size;
        this.sC = sC;
        if(this.sC) addInsCount += 1;
        this.fV = fV;
        if (this.fV) addInsCount += 1;
        this.iC = iC;
        if (this.iC) addInsCount += 1;
        this.m = m;
        if (this.m) addInsCount += 1;
        this.c = c;
        if (this.c) addInsCount += 1;
        this.quant = quant;
    }

    /**
     * Calculates the price of the coffee based on its size, add-ins, and quantity.
     *
     * @return The total price of the coffee.
     */
    @Override
    public double price() {
        int sizeIndex = switch (size) {
            case "Tall" -> 1;
            case "Grande" -> 2;
            case "Venti" -> 3;
            default -> 0; // Short
        };
        return quant*(BASE_PRICE + (SIZE_UPGRADE_PRICE * sizeIndex) + (ADD_IN_PRICE * addInsCount));
    }

    /**
     * Returns a string representation of the coffee order, including size, quantity, and add-ins.
     *
     * @return A string detailing the coffee order.
     */
    public String toString(){
        int index = 0;
        String[] adds = new String[5];
        String addsFinal = "";
        if (this.c) {
            adds[index] = "Caramel";
            index++;
        }
        if (this.m) {
            adds[index] = "Mocha";
            index ++;
        }
        if (this.iC) {
            adds[index] = "Irish Cream";
            index ++;
        }
        if (this.fV) {
            adds[index] = "French Vanilla";
            index++;
        }
        if(this.sC) {
            adds[index] = "Sweet Cream";
        }
       while(index>0){
            if(index-1 == 0){
                addsFinal += adds[index-1];
            }else{
                addsFinal += adds[index-1] + ", ";
            }index--;
        }

        if(addsFinal.equals("null")){ return "Coffee(" + quant + ") " + size; }
        else{   return "Coffee(" + quant + ") " + size + "[" + addsFinal + "]"; }
    }
}
