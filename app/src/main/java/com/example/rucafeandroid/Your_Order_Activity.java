/**
 * Your_Order_activity class is the activity for the Your Orders page GUI
 * Houses functions to cancel menu items and place orders
 *
 * @author Udayan Rai, Garvit Gupta
 */
package com.example.rucafeandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Your_Order_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView orderListView;
    private Button placeOrderButton;
    private TextView subtotalOutput;
    private TextView salesTaxOutput;
    private TextView totalCostOutput;
    public static ArrayList<String> ordersString= new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    public static final String EMPTY_COST = "$0.00";

    /**
     * Initializes the Your Store Orders page GUI and the required buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);

        placeOrderButton=findViewById(R.id.addtoStoreOrdersButton);
        subtotalOutput=findViewById(R.id.subtotalDisplay);
        salesTaxOutput=findViewById(R.id.salesTaxDisplay);
        totalCostOutput=findViewById(R.id.totalCostDisplay);
        orderListView=findViewById(R.id.menuItemsListView);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ordersString);
        orderListView.setAdapter(adapter);
        orderListView.setOnItemClickListener(this);

        updatePrices();
    }

    /**
     * Start method is called whenever the user clicks on your Orders Screen again
     */
    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }


    /**
     * Removes the selected MenuItem that the user selects in the listView
     * @param itemRemove the MenuItem to be removed
     */
    private void removeOrderItem(String itemRemove) {
        for(int i=0; i<MainActivity.order.getItemList().size();i++){
            if(MainActivity.order.getItemList().get(i).toString().equals(itemRemove)){
                boolean isRemove=MainActivity.order.remove(MainActivity.order.getItemList().get(i));
                if(isRemove){
                    updatePrices();
                    ordersString.remove(itemRemove);
                    adapter.notifyDataSetChanged();
                    String confirm= getResources().getString(R.string.removeObjectItemConfirmation);
                    Toast.makeText(this,confirm,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    /**
     * Gives the option to remove a menuItem when you click an element in the listview
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String itemRemove= ordersString.get(i);
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.cancelItemTitle));
        alert.setMessage(getResources().getString(R.string.cancelItemMessage)+itemRemove);
        alert.setPositiveButton(getResources().getString(R.string.alert_yes), new DialogInterface.OnClickListener() {

            /**
             * When the User clicks "yes", MenuItem is canceled
             * @param dialogInterface
             * @param i
             */
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                removeOrderItem(itemRemove);
            }
        }).setNegativeButton(getResources().getString(R.string.alert_no), new DialogInterface.OnClickListener() {

            /**
             * When the User clicks "No", Nothing occurs
             * @param dialogInterface
             * @param i
             */
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog= alert.create();
        dialog.show();
    }
    /**
     * Updates the Subtotal, Sales-Tax, and Total textfields based on the Order details
     */
    private void updatePrices(){
        DecimalFormat dFormatter = new DecimalFormat("$"+ "##,##0.00");
        if(MainActivity.order.getItemList().size()==0){
            String emptyCost=EMPTY_COST;
            subtotalOutput.setText(emptyCost);
            salesTaxOutput.setText(emptyCost);
            totalCostOutput.setText(emptyCost);
            return;
        }
        subtotalOutput.setText(dFormatter.format(MainActivity.order.getOrderCost()));
        double salesTax=MainActivity.order.calculateTax();
        double total= MainActivity.order.getTotalCost(salesTax);
        salesTaxOutput.setText(dFormatter.format(salesTax));
        totalCostOutput.setText(dFormatter.format(total));
    }
    /**
     * Places the order and adds the order to Store Orders Arraylist
     * @param view when the user clicks on the "Place Order" button
     */
    public void placeOrder(View view){
        try{
            if(MainActivity.order.getItemList().size()==0) throw new IllegalArgumentException();
            AlertDialog.Builder alert= new AlertDialog.Builder(this);
            alert.setTitle(getResources().getString(R.string.placeOrderTitle));
            alert.setMessage(getResources().getString(R.string.placeOrderMessage));
            alert.setPositiveButton(getResources().getString(R.string.alert_yes), new DialogInterface.OnClickListener() {

                /**
                 * When the User clicks "yes", Order is Placed
                 * @param dialogInterface
                 * @param i
                 */
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean isPlace= MainActivity.ordersList.add(MainActivity.order);
                    if(isPlace){
                        MainActivity.order=new Order();
                        updatePrices();
                        ordersString.clear();
                        adapter.notifyDataSetChanged();
                    }
                }
            }).setNegativeButton(getResources().getString(R.string.alert_no), new DialogInterface.OnClickListener() {

                /**
                 * When the User clicks "No", Nothing occurs
                 * @param dialogInterface
                 * @param i
                 */
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog= alert.create();
            dialog.show();
        }catch(IllegalArgumentException e){
            Toast.makeText(this, getResources().getString(R.string.place_order_error_when_order_empty), Toast.LENGTH_SHORT).show();
        }
    }
}