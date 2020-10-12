package com.example.android.coffeeapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

import static com.example.android.coffeeapp.R.id.name_field;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffee = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        // show the final price
        //displayPrice(calculatePrice(numberOfCoffee, hasChocolate));

        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        int price = calculatePrice(numberOfCoffee, hasChocolate);

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(createOrderSummary(name, price, hasChocolate));
//        // Figure out if the user wants chocolate topping
//        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkbox);
//        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price


        // Display the order summary on the screen
       // String message = createOrderSummary(name, price, hasChocolate);


    }
    public void increment(View view){
        numberOfCoffee++;
        display(numberOfCoffee);
    }
    public void decrement(View view) {
        if (numberOfCoffee > 0) {
            numberOfCoffee--;
            display(numberOfCoffee);
        }
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//   }
    private int calculatePrice(int numberOfCoffee, boolean hasChocolate) {
        // First calculate the price of one cup of coffee
        int basePrice = 5;

        // If the user wants chocolate, add $2 per cup
       if (hasChocolate) basePrice = basePrice + 2;

        // Calculate the total order price by multiplying by the quantity
        return numberOfCoffee * basePrice;
    }

    private String createOrderSummary(String name, int price, boolean addChocolate) {
        String priceMessage = getString(R.string.order_summary_name) + " " + name;
        priceMessage += "\n" + getString(R.string.order_summary_chocolate) + " " + addChocolate;
        priceMessage += "\n" + getString(R.string.order_summary_quantity) + " " + numberOfCoffee;
        priceMessage += "\n" + getString(R.string.order_summary_price) + " " + price;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }
}