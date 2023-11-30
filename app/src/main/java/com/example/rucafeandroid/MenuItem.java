/**
 * The MenuItem class is the superclass of all menu items such as donuts and coffee.
 * Stores the price of the item and the quantity.
 *
 * @author Udayan Rai, Garvit Gupta
 */

package com.example.rucafeandroid;

import java.text.DecimalFormat;

public class MenuItem {
    private double priceOfItem;
    private int quantity;

    /**
     * Constructor for the MenuItem class.
     * Takes in price of item and quantity.
     * @param priceOfItem price of an item
     * @param quantity quantity desired
     */
    public MenuItem(double priceOfItem, int quantity) {
        this.priceOfItem = priceOfItem;
        this.quantity = quantity;
    }

    /**
     * Getter method for the priceOfItem
     * @return priceOfItem
     */
    public double getPriceOfItem() {
        return priceOfItem;
    }

    /**
     * Setter method for the price of a Menuitem
     */
    public void setPriceOfItem(double price) {
        this.priceOfItem = price;
    }

    /**
     * Add method for the price of a Menuitem
     */
    public void addPrice(double price) {
        this.priceOfItem = this.priceOfItem + price;
    }

    /**
     * Getter method for the quantity
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter method for the quantity of a Menuitem
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * This method Calculates the total price of an item factoring in quantity
     * @return itemPrice
     */
    public double itemPrice() {
        return quantity * priceOfItem;
    }

    /**
     * Method to return the Menu Item details in readable format.
     * @return output details of a  Menu Item
     */
    @Override
    public String toString() {
        DecimalFormat dFormatter = new DecimalFormat("$" + "##,##0.00");
        String output = "(" + quantity + ")" + "\t PRICE: " + dFormatter.format(itemPrice());
        return output;
    }

    /**
     * Method to check if a Menu Item is equal to another Menu Item.
     * @param obj Object that is to be compared
     * @return true if 2 Menu Items are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuItem) {
            MenuItem item = (MenuItem) obj;
            return (item.quantity == this.quantity && priceOfItem == item.priceOfItem);
        }
        return false;
    }
}
