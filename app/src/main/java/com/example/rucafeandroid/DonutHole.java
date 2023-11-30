/**
 * DonutHole class creates a cake donut object that extends Menuiten.
 * DonutHole Object holds the flavor, quantity, and price of a Donut Hole.
 *
 * @author Garvit Gupta, Udayan Rai
 */

package com.example.rucafeandroid;

public class DonutHole extends MenuItem {
    private String flavor;
    public static final double HOLE_PRICE = 0.39;

    /**
     Constructor for the DonutHole Class.
     @param flavor, the flavor of the Donut-Hole.
     @param quantity, the quantity of Donut-Hole
     */
    public DonutHole(String flavor, int quantity) {
        super(HOLE_PRICE, quantity);
        this.flavor = flavor;
    }


    /**
     * This method Calculates the total price of a Donut Hole factoring in quantity
     * @return itemPrice
     */
    @Override
    public double itemPrice() {
        return super.itemPrice();
    }

    /**
     * Method to return the Donut-Hole details in readable format.
     * @return output details of a Donut-Hole
     */
    @Override
    public String toString() {
        return flavor + " Donut-Hole " + super.toString();
    }

    /**
     * Method to check if a Donut Hole is equal to another Donut Hole.
     * @param obj Object that is to be compared
     * @return true if 2 Donut Holes are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DonutHole) {
            DonutHole item = (DonutHole) obj;
            return (super.equals(item) && this.flavor.equals(item.flavor));
        }
        return false;
    }

}
