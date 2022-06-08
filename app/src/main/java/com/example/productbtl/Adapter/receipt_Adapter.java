package com.example.productbtl.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productbtl.Object.receipt;
import com.example.productbtl.R;
import com.example.productbtl.myinterface.buttonClickListenner;

import java.util.List;

public class receipt_Adapter extends RecyclerView.Adapter<receipt_Adapter.receipt_Adapterholder>{

    private List<receipt> list;
    private buttonClickListenner listenner;

    public receipt_Adapter(List<receipt> list , buttonClickListenner listenner) {
        this.list = list;
        this.listenner = listenner;
    }

    public void setData(List<receipt> list ){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public receipt_Adapterholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_items,parent,false);
        return new receipt_Adapterholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull receipt_Adapterholder holder, int position) {

        receipt receipt= list.get(position);
        if(receipt == null)
            return;
        holder.tv_address.setText(receipt.getAddress());
        holder.tv_date.setText(receipt.getDate());
        holder.tv_name.setText(receipt.getName());
        holder.tv_id.setText(receipt.getId());
        holder.tv_phonenumber.setText(receipt.getPhonenumber());
        holder.tv_price.setText(receipt.getPrice());
        holder.tv_product.setText(receipt.getProduct());
        holder.tv_status.setText(receipt.getStatus());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenner.onClickDeleteItem(receipt);
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

    public class receipt_Adapterholder extends RecyclerView.ViewHolder {
        private TextView tv_name , tv_date , tv_address,tv_id , tv_phonenumber , tv_status , tv_product , tv_price;
        private Button btn_delete;

        public receipt_Adapterholder(@NonNull View itemView) {
            super(itemView);
            tv_address = itemView.findViewById(R.id.address_of_receipt);
            tv_date = itemView.findViewById(R.id.date_of_receipt);
            tv_name = itemView.findViewById(R.id.name_of_receipt);
            tv_id = itemView.findViewById(R.id.id_of_receipt);
            tv_phonenumber = itemView.findViewById(R.id.phone_of_receipt);
            tv_price = itemView.findViewById(R.id.price_of_receipt);
            tv_product = itemView.findViewById(R.id.product_of_receipt);
            tv_status = itemView.findViewById(R.id.status_of_receipt);
            btn_delete = itemView.findViewById(R.id.btn_delete_receipt);

        }
    }
}
