/**
 * YeastDonut class creates a cake donut object that extends Menuiten.
 * YeastDonut Object holds the flavor, quantity, and price of a Yeast Donut.
 *
 * @author Garvit Gupta, Udayan Rai
 */
package com.example.rucafeandroid;

public class YeastDonut extends MenuItem {
    private String flavor;
    public static final double YEAST_PRICE = 1.59;

    /**
     Constructor for the YeastDonut Class.
     @param flavor, the flavor of the Yeast-Donuts.
     @param quantity, the quantity of Yeast-Donuts
     */
    public YeastDonut(String flavor, int quantity) {
        super(YEAST_PRICE, quantity);
        this.flavor = flavor;
    }


    /**
     * This method Calculates the total price of a Yeast Donut factoring in quantity
     * @return itemPrice
     */
    @Override
    public double itemPrice() {
        return super.itemPrice();
    }


    /**
     * Method to return the Yeast-Donut details in readable format.
     * @return output details of a Yeast-Donut
     */
    @Override
    public String toString() {
        return flavor + " Yeast-Donut " + super.toString();
    }

    /**
     * Method to check if a Yeast-Donut is equal to another cake donut.
     * @param obj Object that is to be compared
     * @return true if 2 Yeast-Donuts are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof YeastDonut) {
            YeastDonut item = (YeastDonut) obj;
            return (super.equals(item) && this.flavor.equals(item.flavor));
        }
        return false;
    }
}
