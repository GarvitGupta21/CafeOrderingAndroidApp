/**
 * Selected_Donuts_activity class is the activity for the Ordering Donuts GUI
 * Houses functions to order a Donut and choose quantity
 *
 * @author Udayan Rai, Garvit Gupta
 */
package com.example.rucafeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Selected_Donuts_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button addButton;
    private TextView donut_displayer;
    private TextView donut_Price;
    private Spinner quantitySelector;
    private ImageView img;
    private String type;
    private String flavor;
    private String[] quantities;
    private ArrayAdapter<String> adapter;
    private int quantity;
    public static final int DOZEN = 12;

    /**
     * Initializes the Ordering Donut GUI and the required buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_donuts);

        addButton=findViewById(R.id.addDonutToOrderButton);
        donut_displayer=findViewById(R.id.DonutFlavor);
        quantitySelector=findViewById(R.id.quantitySpinner);
        img=findViewById(R.id.donut_img);
        donut_Price=findViewById(R.id.priceDisplayerDonuts);
        quantities=quantitySetter();
        adapter=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantities);
        quantitySelector.setAdapter(adapter);
        quantitySelector.setOnItemSelectedListener(this);

        Intent intent= getIntent();
        type=intent.getStringExtra("TYPE");
        flavor=intent.getStringExtra("FLAVOR");
        String display= flavor+" "+type+ " Donut";
        donut_displayer.setText(display);

        img.setImageResource(R.drawable.shoppingcart);
    }

    /**
     * Helper method to create values for the Quantities Spinner
     * @return arr, the String Array of the possible quantities for the user to select
     */
    private String[] quantitySetter(){
        String[] arr= new String[DOZEN];
        for(int i=0; i<DOZEN; i++){
            arr[i]=""+(i+1);
        }
        return arr;
    }

    /**
     * Displays the price of the Donut when you select a value from the quantity spinner
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        DecimalFormat dFormatter = new DecimalFormat("$" + "##,##0.00");

        quantity= Integer.parseInt(adapterView.getSelectedItem().toString());
        if(type.equals("Yeast")){
            MenuItem donut=new YeastDonut(flavor,quantity);
            String cost= dFormatter.format(donut.itemPrice());
            donut_Price.setText(cost);
        }
        if(type.equals("Cake")){
            MenuItem donut=new CakeDonut(flavor,quantity);
            String cost= dFormatter.format(donut.itemPrice());
            donut_Price.setText(cost);
        }
        if(type.equals("Hole")){
            MenuItem donut=new DonutHole(flavor,quantity);
            String cost= dFormatter.format(donut.itemPrice());
            donut_Price.setText(cost);
        }
    }

    /**
     * Method that handles what occurs the event where nothing is selected
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    /**
     * Adds a donut to the order based on their selected preferences
     * @param view when the user hits the "Add" Button
     */
    public void addDonut(View view){
        if(type.equals("Yeast")){
            MenuItem donut= new YeastDonut(flavor,quantity);
            boolean isAdd= MainActivity.order.add(donut);
            String donutString= donut.toString() +getResources().getString(R.string.addedOrder);
            if(isAdd){
                Your_Order_Activity.ordersString.add(donut.toString());
                Toast.makeText(this,donutString,Toast.LENGTH_SHORT).show();
            }
        }
        if(type.equals("Cake")){
            MenuItem donut= new CakeDonut(flavor,quantity);
            boolean isAdd= MainActivity.order.add(donut);
            String donutString= donut.toString() +getResources().getString(R.string.addedOrder);
            if(isAdd){
                Your_Order_Activity.ordersString.add(donut.toString());
                Toast.makeText(this,donutString,Toast.LENGTH_SHORT).show();
            }
        }
        if(type.equals("Hole")){
            MenuItem donut= new DonutHole(flavor,quantity);
            boolean isAdd= MainActivity.order.add(donut);
            String donutString= donut.toString() +getResources().getString(R.string.addedOrder);
            if(isAdd){
                Your_Order_Activity.ordersString.add(donut.toString());
                Toast.makeText(this,donutString,Toast.LENGTH_SHORT).show();
            }
        }
    }
}