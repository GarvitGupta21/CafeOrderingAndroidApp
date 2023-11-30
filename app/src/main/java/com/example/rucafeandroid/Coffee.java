/**
 * Coffee class creates a Coffee object that extends Menuiten and implements CUstomizable interface.
 * Coffee Object holds the flavor, quantity, price, and add-ons of a Coffee.
 *
 * @author Garvit Gupta, Udayan Rai
 */
package com.example.rucafeandroid;

public class Coffee extends MenuItem implements Customizable {
    private int size;
    private boolean cream;
    private boolean syrup;
    private boolean milk;
    private boolean caramel;
    private boolean whippedCream;
    public static final double SHORT_PRICE = 1.69;
    public static final double SIZE_PRICE_INCREASE = 0.40;
    public static final double ADD_ON_PRICE = 0.30;
    public static final double SHORT_SIZE = 0;
    public static final double TALL_SIZE = 1;
    public static final double VENTI_SIZE = 2;
    public static final double GRANDE_SIZE = 3;

    /**
     Constructor for the Coffee Class.
     @param size the size of the Coffee.
     @param cream the cream add-on for a Coffee Order
     @param syrup the syrup add-on for a Coffee Order
     @param milk the milk add-on for a Coffee Order
     @param caramel the caramel add-on for a Coffee Order
     @param whippedCream the whipped Cream add-on for a Coffee Order
     @param quantity the quantity of Coffees Ordered
     */
    public Coffee(int size, boolean cream, boolean syrup, boolean milk, boolean caramel, boolean whippedCream, int quantity) {
        super(SHORT_PRICE, quantity);
        this.size = size;
        this.cream = cream;
        this.syrup = syrup;
        this.milk = milk;
        this.caramel = caramel;
        this.whippedCream = whippedCream;
    }

    /**
     * A method to add Add-Ons to the current Coffee Menu-Item
     * @param obj, the Add-On wished to be added to the Coffee
     * @return true if Add-On has been added to Coffee, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof String) {
            if (obj.equals("cream")) {
                cream = true;
                return true;
            }
            if (obj.equals("syrup")) {
                syrup = true;
                return true;
            }
            if (obj.equals("milk")) {
                milk = true;
                return true;
            }
            if (obj.equals("caramel")) {
                caramel = true;
                return true;
            }
            if (obj.equals("whipped cream")) {
                whippedCream = true;
                return true;
            }
        }
        return false;
    }

    /**
     * A method to remove Add-Ons to the current Coffee Menu-Item
     * @param obj, the Add-On wished to be removed to the Coffee
     * @return true if Add-On has been removed from Coffee, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof String) {
            if (obj.equals("cream")) {
                cream = false;
                return true;
            }
            if (obj.equals("syrup")) {
                syrup = false;
                return true;
            }
            if (obj.equals("milk")) {
                milk = false;
                return true;
            }
            if (obj.equals("caramel")) {
                caramel = false;
                return true;
            }
            if (obj.equals("whipped cream")) {
                whippedCream = false;
                return true;
            }
        }
        return false;
    }

    /**
     * This method Calculates the total price of a Coffee  factoring in quantity and Add-Ons
     * @return itemPrice
     */
    @Override
    public double itemPrice() {
        this.setPriceOfItem(SHORT_PRICE + (size * SIZE_PRICE_INCREASE));
        if (cream) this.addPrice(ADD_ON_PRICE);
        if (syrup) this.addPrice(ADD_ON_PRICE);
        if (milk) this.addPrice(ADD_ON_PRICE);
        if (caramel) this.addPrice(ADD_ON_PRICE);
        if (whippedCream) this.addPrice(ADD_ON_PRICE);
        return this.getPriceOfItem() * getQuantity();
    }

    /**
     * Method to return the Coffee details in readable format.
     * @return output details of a Coffee
     */
    @Override
    public String toString() {
        String output = "";
        if (size == SHORT_SIZE) output += "Short Coffee ";
        if (size == TALL_SIZE) output += "Tall Coffee ";
        if (size == VENTI_SIZE) output += "Venti Coffee ";
        if (size == GRANDE_SIZE) output += "Grande Coffee ";

        if (!cream && !syrup && !milk && !caramel && !whippedCream) return output + super.toString();

        output += ": ADD-ONS: ";
        if (cream) output += "cream ";
        if (syrup) output += "syrup ";
        if (milk) output += "milk ";
        if (caramel) output += "caramel ";
        if (whippedCream) output += "whipped-cream ";

        return output + super.toString();
    }
}
