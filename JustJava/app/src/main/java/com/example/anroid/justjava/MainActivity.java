package com.example.anroid.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increaseOne(View view) {
        if (quantity < 100) {
            quantity++;
            display();
        }
    }

    public void decreaseOne(View view) {
        if (quantity > 1) {
            quantity--;
            display();
        }
    }

    public void display() {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice();
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice() {
        int price = 5;
        int number = quantity * price;

        CheckBox box = (CheckBox) findViewById(R.id.checkbox);
        boolean hasWhippedCream = box.isChecked();
        if (hasWhippedCream) number += 1*quantity;

        CheckBox box2 = (CheckBox) findViewById(R.id.checkbox2);
        boolean hasChocolate = box2.isChecked();
        if (hasChocolate) number += 2*quantity;

        EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString().trim();
        Log.v("MainActivity", "The price is " + number);
        String str = "Name: "+name;
        str += "\nAdd whipped cream? " + hasWhippedCream;
        str += "\nAdd chocolate? " + hasChocolate;
        str += "\nQuantity: " + quantity;
        str += "\nTotal: $" + number;
        str += "\nThank you!";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        Uri uri = Uri.parse("mailto:loiralae@gmail.com"+
                "?subject="+Uri.encode("JustJava order for " + name)+
                "&body="+Uri.encode(str));
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}