package com.example.adminapp.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.adminapp.Adapter.RecyclerAdapter;
import com.example.adminapp.Listener.OnRecyclerItemClickListener;
import com.example.adminapp.Model.Shoes;
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

public class FirstActivity extends AppCompatActivity implements OnRecyclerItemClickListener {
    RecyclerView recyclerView;
    ArrayList<Shoes> list;

    RecyclerAdapter recyclerAdapter;
    RelativeLayout relativeLayout;

    Switch aSwitch;

    int position;
    Shoes shoes;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        getSupportActionBar().setTitle("Blue Pop");

        relativeLayout = findViewById(R.id.relative);
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        if(Util.isInternetConnected(this)) {
            fetchProductsFromFirebase();
        }else{
            Toast.makeText(FirstActivity.this,"Please Connect to Internet and Try Again",Toast.LENGTH_LONG).show();
        }
    }
    void fetchProductsFromFirebase() {
        db.collection("Products").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {

                            list = new ArrayList<>();

                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();

                            for (DocumentSnapshot snapshot : documentSnapshots) {
                                String docId = snapshot.getId();
                                Shoes shoes = snapshot.toObject(Shoes.class);
                                shoes.docId = docId;
                                list.add(shoes);
                            }

                            recyclerAdapter = new RecyclerAdapter(FirstActivity.this, R.layout.bluepop_item, list);

                            recyclerAdapter.setOnRecyclerItemClickListener(FirstActivity.this);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FirstActivity.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(recyclerAdapter);

                        } else {
                            Toast.makeText(FirstActivity.this, "Some Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    @Override
    public void onItemClick(int position) {
        this.position = position;
        shoes = list.get(position);
        Intent intent = new Intent(FirstActivity.this,DisplayActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.switchBu);
        item.setActionView(R.layout.switch_lay);

        aSwitch = item.getActionView().findViewById(R.id.switchForActionBar);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(FirstActivity.this,2);
                    recyclerView.setLayoutManager(gridLayoutManager);

                    recyclerView.setAdapter(recyclerAdapter);
                }else{
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FirstActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    recyclerView.setAdapter(recyclerAdapter);
                }
            }
        });
        return true;

    }
}
