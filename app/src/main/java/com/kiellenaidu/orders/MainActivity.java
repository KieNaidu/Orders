package com.kiellenaidu.orders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance(); //Getting instance of Firebase database
    DatabaseReference myRef = database.getReference("orders"); //Getting a reference of the firebase database

    EditText TXTOrder, TXTQuantity;
    Button BTNPush, BTNPull;
    String enteredProductName;
    String enteredQuantity;
    Orders orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TXTOrder = findViewById(R.id.TXTOrder);
        TXTQuantity = findViewById(R.id.TXTQuantity);
        BTNPush = findViewById(R.id.BTNPush);
        BTNPull = findViewById(R.id.BTNPull);

       BTNPush.setOnClickListener(new View.OnClickListener() {



           @Override
           public void onClick(View view) {

               enteredProductName = TXTOrder.getText().toString().trim();
               enteredQuantity = TXTQuantity.getText().toString().trim();
               orders = new Orders(enteredProductName, enteredQuantity);


               myRef.push().setValue(orders).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       Toast.makeText(MainActivity.this, "Records saved successfully to database", Toast.LENGTH_SHORT).show();
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });
           }
       });

       BTNPull.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent openOrders = new Intent(MainActivity.this, PulledOrders.class);
               startActivity(openOrders);
           }
       });
    }
}