/**
 * This interface contains the methods add and remove that will be implemented in other classes
 *
 * @author Garvit Gupta, Udayan Rai
 */

package com.example.rucafeandroid;

public interface Customizable {

    /**
     * This method adds an object in the context of the implemented parameter and class
     * @param obj object
     * @return true is added successfully, otherwise false
     */
    boolean add(Object obj);

    /**
     * This method removes an object in the context of the implemented parameter and class
     * @param obj object
     * @return true is removed successfully, otherwise false
     */
    boolean remove(Object obj);
}
