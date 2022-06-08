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
import com.example.productbtl.myinterface.imageClickListenner;

import java.util.List;

public class cart_Adapter extends RecyclerView.Adapter<cart_Adapter.cart_AdapterHolder> {
    private List<cart> list;
    private imageClickListenner listenner;

    public cart_Adapter(List<cart> list , imageClickListenner listenner) {
        this.list = list;
        this.listenner = listenner;
    }
    public void setData(List<cart> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public cart_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new cart_AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cart_AdapterHolder holder, int position) {
        final cart cart = list.get(position);
        Long price = cart.getPrice() / Long.parseLong(cart.getAmount());
        if(cart == null){
            return;
        }
        holder.tv_name.setText(cart.getName());
        holder.tv_price.setText(MainActivity.formatNumber(cart.getPrice()));
        holder.tv_amount.setText(cart.getAmount());
        Glide.with(holder.imageView.getContext())
                .load(cart.getImg())
                .into(holder.imageView);
        holder.add_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.addAmount(cart,price);
                holder.tv_amount.setText(cart.getAmount());
                holder.tv_price.setText(MainActivity.formatNumber(cart.getPrice()));
                setData(MainActivity.getListCart());
                listenner.onImageClickAdd(cart);
            }
        });
        holder.rule_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ruleAmount(cart,price);
                holder.tv_amount.setText(cart.getAmount());
                holder.tv_price.setText(MainActivity.formatNumber(cart.getPrice()));
                setData(MainActivity.getListCart());
                listenner.onImageClickRule(cart);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        else
        return 0;
    }

    public class cart_AdapterHolder extends RecyclerView.ViewHolder  {

        private TextView tv_name, tv_price, tv_amount ;
        private ImageView imageView, add_amount, rule_amount;

        public cart_AdapterHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name_cart);
            tv_price = itemView.findViewById(R.id.tv_price_cart);
            tv_amount = itemView.findViewById(R.id.tv_amount_cart);

            imageView = itemView.findViewById(R.id.img_cart);
            add_amount =(ImageView) itemView.findViewById(R.id.add_amount_cart);
            rule_amount =(ImageView) itemView.findViewById(R.id.rule_amount_cart);

        }

    }

}
