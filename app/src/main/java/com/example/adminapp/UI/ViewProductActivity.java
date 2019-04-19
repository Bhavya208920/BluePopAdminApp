package com.example.adminapp.UI;

import android.content.Intent;
import android.support.v7.app.ActionBar;
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

import com.example.adminapp.Adapter.RecyclerAdapter;
import com.example.adminapp.Listener.OnRecyclerItemClickListener;
import com.example.adminapp.Model.Shoes;
import com.example.adminapp.R;

import java.util.ArrayList;

public class ViewProductActivity extends AppCompatActivity implements OnRecyclerItemClickListener {

    RecyclerView recyclerView;
    ArrayList<Shoes> list;

    RecyclerAdapter recyclerAdapter;
    RelativeLayout relativeLayout;

    Switch aSwitch;

    int position;
    Shoes shoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        getSupportActionBar().setTitle("View Products");

        relativeLayout = findViewById(R.id.relative);
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);


        Shoes shoes = new Shoes(R.drawable.ic_launcher_background, "Casual", "4335", "Rs.400", "Black", "6");
        Shoes shoes1 = new Shoes(R.drawable.ic_launcher_background, "Slippers", "566", "Rs.250", "Brown", "6");

        list.add(shoes);
        list.add(shoes1);

        recyclerAdapter = new RecyclerAdapter(ViewProductActivity.this, R.layout.list_item, list);
        recyclerAdapter.setOnRecyclerItemClickListener((OnRecyclerItemClickListener) this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewProductActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {
        this.position = position;
        shoes = list.get(position);
        Intent intent = new Intent(ViewProductActivity.this,DesActivity.class);
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
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ViewProductActivity.this,2);
                    recyclerView.setLayoutManager(gridLayoutManager);

                    recyclerView.setAdapter(recyclerAdapter);
                }else{
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewProductActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    recyclerView.setAdapter(recyclerAdapter);
                }
            }
        });
        return true;
    }
}
