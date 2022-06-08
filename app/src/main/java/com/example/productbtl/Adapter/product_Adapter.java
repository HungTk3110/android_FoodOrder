package com.example.productbtl.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.productbtl.Activity.MainActivity;
import com.example.productbtl.Fragment.OrderFragment;
import com.example.productbtl.Object.product;
import com.example.productbtl.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.NumberFormat;
import java.util.Locale;

public class product_Adapter extends FirebaseRecyclerAdapter<product,product_Adapter.product_AdapterHolder> {


    public product_Adapter(@NonNull FirebaseRecyclerOptions<product> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull product_AdapterHolder holder, int position, @NonNull product model) {
        holder.Name.setText(model.getName());

        holder.Price.setText(MainActivity.formatNumber(model.getPrice()));
        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,new OrderFragment(model.getImg(),model.getName(),model.getPrice(),model.getInformation()))
                        .addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public product_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items,parent,false);
        return new product_AdapterHolder(view);
    }

    public class product_AdapterHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView Name ,Price ;
        LinearLayout layout;
        public product_AdapterHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.product_img);
            Name = itemView.findViewById(R.id.product_name);
            Price = itemView.findViewById(R.id.product_price);
            layout = itemView.findViewById(R.id.layout_product);
        }
    }
}
