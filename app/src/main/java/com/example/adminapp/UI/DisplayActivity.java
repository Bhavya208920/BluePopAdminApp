package com.example.adminapp.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.Model.Shoes;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayActivity extends AppCompatActivity {
    @BindView(R.id.textViewName)
    TextView txtName;

    @BindView(R.id.textViewPrice)
    TextView txtPrice;

    @BindView(R.id.textViewId)
    TextView txtId;

    @BindView(R.id.textViewColor)
    TextView txtColor;

    @BindView(R.id.textViewSize)
    TextView txtSize;

    @BindView(R.id.imageView)
    ImageView img;

    @BindView(R.id.buttonBuy)
    Button btnBuy;

    ArrayList<Shoes> list;
    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        getSupportActionBar().setTitle("Blue Pop");
        ButterKnife.bind(this);

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
                        } else {
                            Toast.makeText(DisplayActivity.this, "Some Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this,AddProductActivity.class);
                startActivity(intent);

            }
        });
    }
}
