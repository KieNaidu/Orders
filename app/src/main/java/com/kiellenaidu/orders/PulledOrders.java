package com.kiellenaidu.orders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PulledOrders extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance(); //Getting instance of Firebase database
    DatabaseReference myRef = database.getReference("orders"); //Getting a reference of the firebase database
    List<String> orderslist;
    ArrayAdapter adapter;
    Orders orders;

    ListView OrdersLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulled_orders);

        OrdersLV = findViewById(R.id.LVOrders);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //The data that's been pushed to firebase that the user has entered.

                //Storing all the orders that are in firebase one child (record) at a time
                orders = new Orders(); //Object of orders class
                orderslist = new ArrayList<String>();
                for (DataSnapshot FirebaseOrders : snapshot.getChildren()) { //One of the records of the data stored in firebase
                    orders = FirebaseOrders.getValue(Orders.class);
                    orderslist.add(orders.ToString());
                }
                adapter = new ArrayAdapter(PulledOrders.this, android.R.layout.simple_list_item_1,orderslist);
                OrdersLV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PulledOrders.this, error.getMessage(), Toast.LENGTH_SHORT).show(); //Returns error message
            }
        });

    }
}