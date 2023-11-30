/**
 * This is an adapter class to be used to instantiate an adapter for the RecyclerView
 * Extends RecyclerView.Adapter<Ordering_Donuts_Adapter.DonutsHolder
 * @author Udayan Rai, Garvit Gupta
 */

package com.example.rucafeandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Ordering_Donuts_Adapter extends RecyclerView.Adapter<Ordering_Donuts_Adapter.DonutsHolder> {
    private ArrayList<String> items;
    private Context context;

    public static final String Yeast_Price = "$1.59";
    public static final String Cake_Price = "$1.79";
    public static final String Hole_Price = "$0.39";


    /**
     * Constructor for the Adapter
     * @param context context of the application
     * @param items list of all donuts
     */
    public Ordering_Donuts_Adapter(Context context, ArrayList<String> items){
        this.context=context;
        this.items=items;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DonutsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.donuts_row_view, parent, false);
        return new DonutsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull DonutsHolder holder, int position) {
        holder.donut_flavor.setText(items.get(position));
        if(items.get(position).contains("Yeast")) holder.donut_price.setText(Yeast_Price);
        if(items.get(position).contains("Cake")) holder.donut_price.setText(Cake_Price);
        if(items.get(position).contains("Hole")) holder.donut_price.setText(Hole_Price);
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * DonutsHolder Class gets the views from the row layout file
     */
    public class DonutsHolder extends RecyclerView.ViewHolder {
        private TextView donut_flavor, donut_price;
        private ConstraintLayout parentLayout;

        /**
         * Constructor for the DonutsHolder Class
         * @param itemView
         */
        public DonutsHolder(@NonNull View itemView){
            super(itemView);
            donut_flavor= itemView.findViewById(R.id.donut_flavor);
            donut_price= itemView.findViewById(R.id.donut_price);

            parentLayout= itemView.findViewById(R.id.rowLayout);
            parentLayout.setOnClickListener(new View.OnClickListener() {

                /**
                 * Method that creates an intent and takes user to the Selected_Donuts_actvity
                 * @param view
                 */
                @Override
                public void onClick(View view) {
                    String flavorString= (String)donut_flavor.getText();
                    Intent intent= new Intent(itemView.getContext(), Selected_Donuts_Activity.class);
                    if(flavorString.contains("Yeast")) intent.putExtra("TYPE","Yeast");
                    if(flavorString.contains("Cake")) intent.putExtra("TYPE","Cake");
                    if(flavorString.contains("Hole")) intent.putExtra("TYPE","Hole");

                    if(flavorString.contains("Chocolate")) intent.putExtra("FLAVOR","Chocolate");
                    if(flavorString.contains("Boston")) intent.putExtra("FLAVOR","Boston Cream");
                    if(flavorString.contains("Jelly")) intent.putExtra("FLAVOR","Jelly");
                    if(flavorString.contains("Strawberry")) intent.putExtra("FLAVOR","Strawberry");

                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
