/**
 * Ordering_Donuts_Activity class is the activity for the Ordering Donuts GUI
 * Houses a Recycle View with all flavors
 * Allows user to navigate to Selected_Donuts_Activity
 * @author Udayan Rai, Garvit Gupta
 */

package com.example.rucafeandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Ordering_Donuts_Activity extends AppCompatActivity {

    private ArrayList<String> donuts = new ArrayList<>();

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_donuts);

        RecyclerView donutsRV=findViewById(R.id.donutFlavorsRV);
        setUpDonuts();
        Ordering_Donuts_Adapter adapter = new Ordering_Donuts_Adapter(this, donuts);
        donutsRV.setAdapter(adapter);
        donutsRV.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * Helper Method to set up the data (Model of the MVC)
     */
    private void setUpDonuts(){
        String[] donutNames = getResources().getStringArray(R.array.Donut_Flavors);

        for (int i = 0; i< donutNames.length;i++){
            donuts.add(donutNames[i]);
        }

    }
}