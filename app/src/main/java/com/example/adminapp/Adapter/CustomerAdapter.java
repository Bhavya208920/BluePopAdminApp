package com.example.adminapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adminapp.Listener.OnRecyclerItemClickListener;
import com.example.adminapp.Model.Customers;
import com.example.adminapp.R;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    Context context;
    int resource;
    ArrayList<Customers> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;

    }

    public CustomerAdapter(Context context, int resource, ArrayList<Customers> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        final CustomerAdapter.ViewHolder holder = new CustomerAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());

            }
        });
        return holder;

    }
    @Override
    public void onBindViewHolder(CustomerAdapter.ViewHolder holder, int position) {
        Customers customer = objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item
        holder.txtTitle.setText(customer.name);
        holder.txtUrl.setText(customer.phone);
        holder.txtEmail.setText(customer.email);

        holder.txtPassword.setText(customer.password);
        holder.txtAddress.setText(customer.address);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Attributes of ViewHolder
        TextView txtTitle;
        TextView txtUrl;
        TextView txtEmail;
        TextView txtPassword;
        TextView txtAddress;


        public ViewHolder(View itemView) {
            super(itemView);

           txtTitle = itemView.findViewById(R.id.textViewTitle);
            txtUrl = itemView.findViewById(R.id.textViewURL);
            txtEmail = itemView.findViewById(R.id.textViewEmail);
            txtPassword = itemView.findViewById(R.id.txtPassword);
            txtAddress = itemView.findViewById(R.id.txtAddress);

        }

}
}
