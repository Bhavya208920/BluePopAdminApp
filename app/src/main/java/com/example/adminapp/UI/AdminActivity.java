package com.example.adminapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adminapp.MainActivity;
import com.example.adminapp.R;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddProduct,buttonViewProduct,buttonViewOrders,buttonViewCustomers;

    void initViews()
    {
        buttonAddProduct = findViewById(R.id.buttonAddProduct);
        buttonViewProduct = findViewById(R.id.buttonViewProduct);
        buttonViewOrders = findViewById(R.id.buttonViewOrders);
        buttonViewCustomers = findViewById(R.id.buttonViewCustomers);

        buttonAddProduct.setOnClickListener(this);
        buttonViewProduct.setOnClickListener(this);
        buttonViewOrders.setOnClickListener(this);
        buttonViewCustomers.setOnClickListener(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);
        getSupportActionBar().setTitle("Blue Pop");
        initViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.buttonAddProduct:
                AddProduct();
                Toast.makeText(this,"Add Product",Toast.LENGTH_LONG).show();
                break;

            case R.id.buttonViewProduct:
                ViewProduct();
                Toast.makeText(this,"View Product",Toast.LENGTH_LONG).show();
                break;

            case R.id.buttonViewCustomers:
                ViewCustomers();
                Toast.makeText(this,"View Customers",Toast.LENGTH_LONG).show();
                break;

            case R.id.buttonViewOrders:
                ViewOrders();
                Toast.makeText(this,"View Orders",Toast.LENGTH_LONG).show();
                break;
        }
    }

    void AddProduct()
    {
        Intent intent1 = new Intent(AdminActivity.this, AddProductActivity.class);
        startActivity(intent1);
    }

    void ViewProduct()
    {
        Intent intent2 = new Intent(AdminActivity.this, ViewProductActivity.class);
        startActivity(intent2);
    }

    void ViewCustomers()
    {
        Intent intent3 = new Intent(AdminActivity.this, ViewCustomersActivity.class);
        startActivity(intent3);
    }

    void ViewOrders()
    {
        Intent intent4 = new Intent(AdminActivity.this, ViewOrdersActivity.class);
        startActivity(intent4);
    }
}

