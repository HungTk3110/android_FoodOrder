package com.example.productbtl.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productbtl.Object.menu;
import com.example.productbtl.R;

import java.util.List;

public class menu_Adapter extends RecyclerView.Adapter<menu_Adapter.menu_AdapterHolder> {

    private List<menu> list;
    private clickItemMenu clickItemMenu;

    public interface clickItemMenu{
       void onClickItemMenu(menu menu);
    }


    public menu_Adapter(List<menu> list , clickItemMenu listener) {
        this.list = list;
        this.clickItemMenu = listener;
    }

    @NonNull
    @Override
    public menu_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_items,parent,false);
        return new menu_AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull menu_AdapterHolder holder, int position) {
        menu menu = list.get(position);
        if(menu == null){
            return;
        }
        holder.textView.setText(menu.getName());
        holder.imageView.setImageResource(menu.getImg());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemMenu.onClickItemMenu(menu);
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

    public class menu_AdapterHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;
        private LinearLayout layout;

        public menu_AdapterHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_menu_item);
            imageView = itemView.findViewById(R.id.img_menu_item);
            layout = itemView.findViewById(R.id.layout_menu);
        }
    }
}
