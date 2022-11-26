package com.example.dbsqlapps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList id, name, price;
    int position;

    public CustomAdapter(Context mContext, ArrayList id, ArrayList name, ArrayList price) {
        this.mContext = mContext;
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(id.get(position)));
        holder.txtName.setText(String.valueOf(name.get(position)));
        holder.txtPrice.setText(String.valueOf(price.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtName, txtPrice;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.id_product);
            txtName = itemView.findViewById(R.id.nama_product);
            txtPrice = itemView.findViewById(R.id.harga_product);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
