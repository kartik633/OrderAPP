package com.example.c1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int n = 2;

    public void submitOrder(View view) {
        CheckBox checkBox = findViewById(R.id.chec);
        CheckBox checkBox1 = findViewById(R.id.chocolate_checkbox);
        boolean hasWhipCream = checkBox.isChecked();
        boolean hasChocolate = checkBox1.isChecked();
        etName = findViewById(R.id.etName);
        String name = etName.getText().toString().trim();
        int price =0 ;
        if(hasChocolate && hasWhipCream)
        {
            price = 8*n;
        }
        else if(hasChocolate && !hasWhipCream)
        {
            price = 7*n;
        }
        else if(!hasChocolate && hasWhipCream)
        {
            price = 6*n;
        }
        else
        {
            price = 5*n;
        }

        String text = createOrderSummary(price,n,hasWhipCream,hasChocolate,name);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "9415kks@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "its "+name+" Your Order details");
        intent.putExtra(Intent.EXTRA_TEXT, text);

        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    private String createOrderSummary(int price,int n, boolean has, boolean has1,String name)
    {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        String text = "Name:" + name + "\nQuantity:"+ n+ "\nTotal:$"+ price+"\nThank you!"+"\nThe whipped cream is " + has+"\nThe Chocolate cream is " + has1;
//        orderSummaryTextView.setText(text);
        return text;
    }

    public void decrement(View view) {
        if(n>1)
        {
            n=n-1;
            display(n);
        }
        else
        {
            n =1;
            Toast.makeText(MainActivity.this,"Cannot be less than 1",Toast.LENGTH_LONG).show();
        }

    }

    public void increment(View view) {
        if(n<100)
        {
            n=n+1;
            display(n);
        }
        else
        {
            n = 100;
            Toast.makeText(MainActivity.this,"Cannot be greater than 100",Toast.LENGTH_LONG).show();
        }
    }
}