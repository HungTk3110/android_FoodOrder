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

import java.util.List;

public class rcvCategory_Adapter extends RecyclerView.Adapter<rcvCategory_Adapter.rcvCategory_AdapterHolder>{

    private List<product> list;



    public void setData(List<product> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public rcvCategory_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcvcategory_items,parent,false);
        return new rcvCategory_AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rcvCategory_AdapterHolder holder, int position) {
        product product = list.get(position);
        if(product == null)
            return;

        Glide.with(holder.img_rcvCategory.getContext())
                .load(product.getImg())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img_rcvCategory);
        holder.tv_name.setText(product.getName());
        holder.tv_price.setText(MainActivity.formatNumber(product.getPrice()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,new OrderFragment(product.getImg(),product.getName(),product.getPrice(),product.getInformation()))
                        .addToBackStack(null).commit();
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

    public class rcvCategory_AdapterHolder extends RecyclerView.ViewHolder {

        private ImageView img_rcvCategory;
        private TextView tv_name, tv_price;
        private LinearLayout layout;

        public rcvCategory_AdapterHolder(@NonNull View itemView) {
            super(itemView);

            img_rcvCategory = itemView.findViewById(R.id.img_rcvCategory);
            tv_name = itemView.findViewById(R.id.tv_rcvCategory_name);
            tv_price = itemView.findViewById(R.id.tv_rcvCategory_price);
            layout = itemView.findViewById(R.id.layout_rcv_category);
        }
    }
}
