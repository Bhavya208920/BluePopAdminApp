package com.example.adminapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adminapp.R;

public class ViewOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        getSupportActionBar().setTitle("View Orders");

    }
}
