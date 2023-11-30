/**
 * The Order class implements the Customizable interface.
 * Stores Menu-Items in an arrayList and performs various order operations.
 *
 * @author Udayan Rai, Garvit Gupta
 */

package com.example.rucafeandroid;

import java.util.ArrayList;


public class Order implements Customizable {

    private ArrayList<MenuItem> itemList;
    private int numItems;
    private double orderCost;
    private int orderNumber;
    public static final double NJTAX= 0.06625;

    /**
     * Constructor for the Order class.
     * Creates an ArrayList to store MenuItems
     */
    public Order() {
        this.itemList = new ArrayList<>();
        this.numItems = 0;
        this.orderCost = 0;
        this.orderNumber = 0;
    }

    /**
     * A getter method to access the Item list in other classes
     * @return itemlist
     */
    public ArrayList<MenuItem> getItemList() {
        return itemList;
    }

    /**
     * A setter method to modify the Order Number of a particular MenuItem in other classes
     */
    public void setOrderNumber(int num) {
        this.orderNumber = num;
    }

    /**
     * A getter method to access the Order Cost in other classes
     * @return orderCost
     */
    public double getOrderCost() {
        return orderCost;
    }

    /**
     * A getter method to access the Order Number in other classes
     * @return OrderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * A method that searches and returns the index of a desired MenuItem
     * @param item, the Menu Item that you want the index of
     * @return index if found, -1 otherwise
     */
    private int find(MenuItem item) {
        for (MenuItem i : itemList) {
            if (i.equals(item)) return itemList.indexOf(i);
        }
        return -1;
    }


    /**
     * A method that adds the calculated tax to the total order
     * @param tax, the tax to be added
     * @return the total cost, including the tax
     */
    public double getTotalCost(double tax) {
        return this.orderCost + tax;
    }

    /**
     * A method that calculates tax based on the total Order Cost
     * @return calculated tax
     */
    public double calculateTax() {
        return NJTAX * this.orderCost;
    }

    /**
     * A method that Adds a Menu Item to the Current Orders ArrayList
     * @return true if succesfully added, false otherwise
     */
    @Override
    public boolean add(Object obj) {

        if (obj instanceof MenuItem) {
            MenuItem item = (MenuItem) obj;

            //maybe delete later
            if (itemList.contains(item)) {
                int index = itemList.indexOf(item);
                itemList.get(index).setQuantity(itemList.get(index).getQuantity() + item.getQuantity());
                orderCost += item.itemPrice();
                return false;
            }
            itemList.add(item);
            numItems++;
            orderCost += item.itemPrice();
            return true;
        }
        return false;
    }

    /**
     * A method that removes a Menu Item to the Current Orders ArrayList
     * @return true if succesfully removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (numItems > 0) {
            if (obj instanceof MenuItem) {
                MenuItem item = (MenuItem) obj;
                int index = find(item);
                itemList.remove(index);
                numItems--;
                orderCost -= item.itemPrice();
                return true;
            }
        }
        return false;
    }

    /**
     * Method to return the Order details in readable format.
     * @return output details of a Order
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < itemList.size(); i++) {
            output += itemList.get(i).toString() + "\n";
        }
        return output;
    }
}
