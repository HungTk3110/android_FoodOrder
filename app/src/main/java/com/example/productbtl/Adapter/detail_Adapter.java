package com.example.productbtl.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.productbtl.Activity.MainActivity;
import com.example.productbtl.Object.cart;
import com.example.productbtl.R;

import java.util.List;

public class detail_Adapter extends RecyclerView.Adapter<detail_Adapter.detail_AdapterHolder>{
    private List<cart> list;

    public detail_Adapter(List<cart> list) {
        this.list = list;
    }

    public void setData(List<cart> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public detail_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_cart_items,parent,false);
        return new detail_AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull detail_AdapterHolder holder, int position) {
        final cart cart = list.get(position);
        if(cart == null){
            return;
        }
        holder.tv_name.setText(cart.getName());
        holder.tv_price.setText(MainActivity.formatNumber(cart.getPrice()));
        holder.tv_amount.setText("x "+cart.getAmount());
        Glide.with(holder.img.getContext())
                .load(cart.getImg())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        else
            return 0;
    }

    public class detail_AdapterHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price, tv_amount ;
        private ImageView img;

        public detail_AdapterHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name_detail);
            tv_price = itemView.findViewById(R.id.tv_price_detail);
            tv_amount = itemView.findViewById(R.id.tv_amount_detail);

            img = itemView.findViewById(R.id.img_detail);
        }
    }
}
