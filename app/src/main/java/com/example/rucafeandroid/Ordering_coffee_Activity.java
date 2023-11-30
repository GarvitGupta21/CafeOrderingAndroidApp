/**
 * Ordering_coffee_Activity class is the activity for the Ordering Coffee GUI
 * Houses functions to order a Coffee and customize a Coffee
 *
 * @author Udayan Rai, Garvit Gupta
 */
package com.example.rucafeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Ordering_coffee_Activity extends AppCompatActivity{
    public static final int SMALL_SIZE = 0;
    public static final int TALL_SIZE = 1;
    public static final int VENTI_SIZE = 2;
    public static final int GRANDE_SIZE = 3;
    public static final int DOZEN = 12;
    private ImageView coffeeImage;
    private CheckBox milk;
    private CheckBox cream;
    private CheckBox whippedCream;
    private CheckBox syrup;
    private CheckBox caramel;
    private Spinner sizeSpinner;
    private Spinner quantitySpinner;
    private TextView price;
    private int quantity;
    private String[] quantities;
    private String[] sizes;
    private ArrayAdapter<String> adapterForQuantities;
    private ArrayAdapter<String> adapterforSizes;


    /**
     * Initializes the Ordering Coffee GUI and the required buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_coffee);

        coffeeImage=findViewById(R.id.CoffeeImage);
        milk = findViewById(R.id.MilkChecker);
        cream = findViewById(R.id.CreamChecker);
        whippedCream = findViewById(R.id.WhippedCreamChecker);
        syrup = findViewById(R.id.SyrupChecker);
        caramel = findViewById(R.id.CaramelChecker);
        sizeSpinner = findViewById(R.id.SizeCoffeeSpinner);
        quantitySpinner = findViewById(R.id.QuantityCoffeeSpinner);
        quantities = quantitySetter();
        sizes = getResources().getStringArray(R.array.Coffee_Sizes);
        coffeeImage.setImageResource(R.drawable.shoppingcart);
        price=findViewById(R.id.PriceDisplayer);

        adapterForQuantities=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantities);
        adapterforSizes=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);

        quantitySpinner.setAdapter(adapterForQuantities);
        sizeSpinner.setAdapter(adapterforSizes);

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Displays the price of the Coffee when you select a value from the sizes spinner
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                displayPrice();
            }

            /**
             * Method that handles what occurs the event where nothing is selected
             * @param adapterView
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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
     * Updates the Carmel Add-On price to the current Coffee Order price
     * @param view when the user checks the "Caramel" Checkbox
     */
    public void updateCaramelPrice(View view){
        displayPrice();
    }

    /**
     * Updates the Milk Add-On price to the current Coffee Order price
     * @param view when the user checks the "milk" Checkbox
     */
    public void updateMilkPrice(View view){
        displayPrice();
    }

    /**
     * Updates the Cream Add-On price to the current Coffee Order price
     * @param view when the user checks the "Cream" Checkbox
     */
    public void updateCreamPrice(View view){
        displayPrice();
    }

    /**
     * Updates the Whipped Cream Add-On price to the current Coffee Order price
     * @param view when the user checks the "Whipped Cream" Checkbox
     */
    public void updateWhippedPrice(View view){
        displayPrice();
    }

    /**
     * Updates the Syrup Add-On price to the current Coffee Order price
     * @param view when the user checks the "Syrup" Checkbox
     */
    public void updateSyrupPrice(View view){
        displayPrice();
    }

    /**
     * Adds a Coffee to the order based on their selected preferences
     * @param view when the user hits the "Add" Button
     */

    public void addCoffee(View view){
        quantity= Integer.parseInt(quantitySpinner.getSelectedItem().toString());
        MenuItem coffee=displayHelper(quantity);
        boolean isAdd= MainActivity.order.add(coffee);
        String coffeeString= coffee.toString()+getResources().getString(R.string.addedOrder);
        if(isAdd){
            Your_Order_Activity.ordersString.add(coffee.toString());
            Toast.makeText(this,coffeeString,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Displays the price of the current Coffee with all their preferences in the TextView
     */
    private void displayPrice(){
        DecimalFormat dFormatter = new DecimalFormat("$"+ "##,##0.00");
        MenuItem coffee = displayHelper(1);
        String cost=""+dFormatter.format(coffee.itemPrice());
        price.setText(cost);
    }

    /**
     * Helper method to display the price of the Coffee with all the wanted preferences and size
     */
    private MenuItem displayHelper(int quantity){
        boolean milk= this.milk.isChecked();
        boolean cream = this.cream.isChecked();
        boolean syrup = this.syrup.isChecked();
        boolean caramel = this.caramel.isChecked();
        boolean whipped = this.whippedCream.isChecked();

        int size=SMALL_SIZE;
        if(sizeSpinner.getSelectedItem().toString().equals("Tall")) size=TALL_SIZE;
        if(sizeSpinner.getSelectedItem().toString().equals("Venti")) size=VENTI_SIZE;
        if(sizeSpinner.getSelectedItem().toString().equals("Grande")) size=GRANDE_SIZE;

        MenuItem coffee= new Coffee(size,cream,syrup,milk,caramel,whipped,quantity);
        return coffee;
    }

}