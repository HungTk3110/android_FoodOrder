package com.example.productbtl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productbtl.Object.category;
import com.example.productbtl.R;

import java.util.List;

public class category_Adapter extends RecyclerView.Adapter<category_Adapter.categoryViewHolder>{
    private Context context;
    private List<category> list;

    public category_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<category> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_items,parent,false);
        return new categoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewHolder holder, int position) {
        category category = list.get(position);
        if(category == null)
            return;

        holder.textView.setText(category.getName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);

        rcvCategory_Adapter adapter = new rcvCategory_Adapter();
        adapter.setData(category.getList());

        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        else return 0;
    }

    public class categoryViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private RecyclerView recyclerView;

        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_name_category);
            recyclerView = itemView.findViewById(R.id.recycview_category);
        }
    }
}
