/**
 * The StoreOrders class implements the Customizable interface.
 * Stores orders in an arrayList and performs various order operations.
 * @author Udayan Rai, Garvit Gupta
 */
package com.example.rucafeandroid;

import java.util.ArrayList;

public class StoreOrders implements Customizable {

    private ArrayList<Order> orderList;
    private int numOrders;
    private int orderNumCounter;


    /**
     * Constructor for the StoreOrders class.
     * Creates an ArrayList to store orders
     */
    public StoreOrders(){
        this.orderList = new ArrayList<>();
        numOrders = 0;

    }

    /**
     * A getter method to access the Number of Orders in another class
     * @return numOrders
     */
    public int getNumOrders(){
        return numOrders;
    }

    /**
     * A method that searches and returns the index of a desired Order
     * @param orderNum, the Order Number
     * @return index if found, -1 otherwise
     */
    public int getorderIndex(int orderNum){
        for(int i=0; i<orderList.size(); i++){
            if(orderList.get(i).getOrderNumber()==orderNum) return i;
        }
        return -1;
    }

    /**
     * A getter method to access the Order List in other classes
     * @return orderList
     */
    public ArrayList<Order> getOrderList(){
        return orderList;
    }

    /**
     * A method that adds an Order to the Current Store Orders ArrayList
     * @return true if succesfully added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order){
            Order item = (Order) obj;
            orderList.add(item);
            numOrders++;
            orderNumCounter++;
            item.setOrderNumber(orderNumCounter);
            return true;
        }
        return false;
    }

    /**
     * A method that removes an Order to the Current Store Orders ArrayList
     * @return true if succesfully removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (numOrders > 0) {
            if (obj instanceof Order) {
                Order item = (Order) obj;
                orderList.remove(item);
                numOrders--;
                return true;
            }
        }
        return false;
    }
}
