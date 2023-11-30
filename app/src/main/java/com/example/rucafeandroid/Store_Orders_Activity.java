/**
 * Store_Orders_Activity class is the activity for the Store Orders page GUI
 * Houses functions to cancel and view orders
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Store_Orders_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayList<String> orderNumbers= new ArrayList<String>();
    private ArrayAdapter<String> orderNumAdapter;
    private Spinner ordersSpinner;
    private ArrayList<String> items= new ArrayList<String>();
    private ArrayAdapter<String> itemsAdapter;
    private TextView totalCostDisplay;
    private ListView orderDisplay;
    public static final String Empty_Cost = "$0.00";


    /**
     * Initializes the Store Store Orders page GUI and the required buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        totalCostDisplay=findViewById(R.id.TotalDisplayer);
        orderDisplay=findViewById(R.id.orderDisplay);
        ordersSpinner=findViewById(R.id.orderSelectorSpinner);

        repopulate();
        orderNumAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,orderNumbers);
        ordersSpinner.setAdapter(orderNumAdapter);
        ordersSpinner.setOnItemSelectedListener(this);

        itemsAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        orderDisplay.setAdapter(itemsAdapter);

        totalCostDisplay.setText(Empty_Cost);


    }
    /**
     * A helper method that repopulates the Order Numbers Spinner
     */
    private void repopulate(){
        int size= MainActivity.ordersList.getOrderList().size();
        for(int i=0; i<size; i++){
            orderNumbers.add(""+MainActivity.ordersList.getOrderList().get(i).getOrderNumber());

        }
    }
    /**
     * Displays the Total of your Order
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        DecimalFormat dFormatter = new DecimalFormat("$" + "##,##0.00");
        int orderNumber= Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        items.clear();
        itemsAdapter.notifyDataSetChanged();

        int index= MainActivity.ordersList.getorderIndex(orderNumber);
        Order orderToShow= MainActivity.ordersList.getOrderList().get(index);

        for(int j=0; j<orderToShow.getItemList().size(); j++){
            items.add(orderToShow.getItemList().get(j).toString());
            itemsAdapter.notifyDataSetChanged();
        }
        double tax= orderToShow.calculateTax();
        totalCostDisplay.setText(dFormatter.format(orderToShow.getTotalCost(tax)));

    }

    /**
     * Method that handles what occurs the event where nothing is selected
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    /**
     * Cancels the selected Order that the user selects in the listView
     * @param view when the user clicks on the "Cancel Order" button
     */
    public void cancelOrder(View view){
        try{
            if(MainActivity.ordersList.getOrderList().size()==0) throw new IllegalArgumentException();
            AlertDialog.Builder alert= new AlertDialog.Builder(this);
            alert.setTitle(getResources().getString(R.string.cancelOrderTitle));
            alert.setMessage(getResources().getString(R.string.cancelOrderMessage));
            alert.setPositiveButton(getResources().getString(R.string.alert_yes), new DialogInterface.OnClickListener() {
                /**
                 * When the User clicks "yes", order is canceled
                 * @param dialogInterface
                 * @param i
                 */
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    cancelHelper();
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
            Toast.makeText(this,getResources().getString(R.string.cancel_order_error),Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Helper method that helps reset values after canceling an order
     */
    private void cancelHelper(){
        int orderNumber= Integer.parseInt(ordersSpinner.getSelectedItem().toString());
        int index= MainActivity.ordersList.getorderIndex(orderNumber);
        Order orderToCancel= MainActivity.ordersList.getOrderList().get(index);
        MainActivity.ordersList.remove(orderToCancel);
        totalCostDisplay.setText(Empty_Cost);
        items.clear();
        itemsAdapter.notifyDataSetChanged();
        orderNumbers.clear();
        repopulate();
        orderNumAdapter.notifyDataSetChanged();
        Toast.makeText(this,getResources().getString(R.string.cancelOrderConfirmation), Toast.LENGTH_SHORT).show();
    }
}