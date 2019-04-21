package com.example.adminapp.UI;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.adminapp.Adapter.CustomerAdapter;
import com.example.adminapp.Listener.OnRecyclerCusItemClickListener;
import com.example.adminapp.Listener.OnRecyclerItemClickListener;
import com.example.adminapp.Model.Customers;
import com.example.adminapp.Model.Util;
import com.example.adminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewCustomersActivity extends AppCompatActivity implements OnRecyclerCusItemClickListener {
    RecyclerView recyclerViewCus;
    ArrayList<Customers> list;

    Customers customer;
    int position;
    CustomerAdapter customerAdapter;
    RelativeLayout relativeLayout;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);
        getSupportActionBar().setTitle("View Customers");

        relativeLayout = findViewById(R.id.relative);
        recyclerViewCus = findViewById(R.id.recyclerViewCus);
        list = new ArrayList<Customers>();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        if (Util.isInternetConnected(this)) {
            fetchCustomersFromFirebase();
        } else {
            Toast.makeText(ViewCustomersActivity.this, "Please Connect to Internet and Try Again", Toast.LENGTH_LONG).show();
        }
    }

    void fetchCustomersFromFirebase() {

        db.collection("Customers").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {

                            list = new ArrayList<Customers>();

                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();

                            for (DocumentSnapshot snapshot : documentSnapshots) {
                                String docId = snapshot.getId();
                                Customers customer = snapshot.toObject(Customers.class);
                                customer.docId = docId;
                                list.add(customer);
                            }

                            getSupportActionBar().setTitle("Total Customers");

                            customerAdapter = new CustomerAdapter(ViewCustomersActivity.this, R.layout.customer_item, list);
                            //customerAdapter.setOnRecyclerCusItemClickListener(ViewCustomersActivity.this);
                            //customerAdapter.setOnRecyclerItemClickListener((OnRecyclerItemClickListener) ViewCustomersActivity.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewCustomersActivity.this);
                            recyclerViewCus.setLayoutManager(linearLayoutManager);
                            recyclerViewCus.setAdapter(customerAdapter);

                        } else {
                            Toast.makeText(ViewCustomersActivity.this, "Some Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



        @Override
    public void ItemClick(int position) {
        this.position = position ;
        customer = list.get(position);

    }
}
