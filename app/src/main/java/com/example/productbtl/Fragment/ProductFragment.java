package com.example.productbtl.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.productbtl.Adapter.product_Adapter;
import com.example.productbtl.Activity.MainActivity;
import com.example.productbtl.Object.menu;
import com.example.productbtl.Object.product;
import com.example.productbtl.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ProductFragment extends Fragment {
    public static final String TAG = ProductFragment.class.getName();


    private ImageView img_back ,img_go_to_cart;
    private TextView tv ;
    private RecyclerView recyclerView;
    private product_Adapter adapter;
    private MainActivity mainActivity ;
    private menu obj_menu;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        img_back = view.findViewById(R.id.click_back);
        tv = view.findViewById(R.id.tv_menu);
        img_go_to_cart = view.findViewById(R.id.img_go_to_cart);

        mainActivity =  (MainActivity) getActivity();
        mainActivity.bottomNavigationView.setVisibility(view.GONE);

        Bundle bundle = getArguments();
        if(bundle != null)
        {
            obj_menu = (menu) bundle.get("object_menu");
            if(obj_menu != null)
            {
                tv.setText(obj_menu.getName());
                recyclerView = (RecyclerView) view.findViewById(R.id.rcv_product);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

                FirebaseRecyclerOptions<product> options =
                        new FirebaseRecyclerOptions.Builder<product>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child(obj_menu.getName()), product.class)
                                .build();

                adapter = new product_Adapter(options);
                recyclerView.setAdapter(adapter);
            }
        }
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.bottomNavigationView.setVisibility(view.VISIBLE);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        img_go_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.goToCartFragment();
            }
        });

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}