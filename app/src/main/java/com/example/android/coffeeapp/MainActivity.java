package com.example.android.coffeeapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffee = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String finalOrder (){
        CheckBox chocolateCheckBox = findViewById(R.id.checkBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();


        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        int price = calculatePrice(numberOfCoffee, hasChocolate);
        return createOrderSummary(name, price, hasChocolate);

    }
    public void submitOrder(View view) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(finalOrder());

    }


    public void sendEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.order_summary_name));
        intent.putExtra(Intent.EXTRA_SUBJECT, finalOrder());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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