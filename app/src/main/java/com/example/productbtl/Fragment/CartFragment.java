package com.example.productbtl.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productbtl.Adapter.cart_Adapter;
import com.example.productbtl.Activity.MainActivity;
import com.example.productbtl.Object.cart;
import com.example.productbtl.R;
import com.example.productbtl.myinterface.imageClickListenner;

public class CartFragment extends Fragment {
    private RecyclerView rcvCart;
    private MainActivity mainActivity ;
    private TextView tv_sumPrice , tv_price, tv_amount , tv_clear_cart;
    private ImageView imageView_back;
    private LinearLayout goTo_Detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mainActivity = (MainActivity) getActivity();
        rcvCart = view.findViewById(R.id.RecycView_cart);
        tv_price = view.findViewById(R.id.tv_price_cart);
        tv_amount = view.findViewById(R.id.tv_amount_cart);
        imageView_back = view.findViewById(R.id.back);
        tv_clear_cart = view.findViewById(R.id.clear_cart);
        goTo_Detail = view.findViewById(R.id.goTo_Detail);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcvCart.setLayoutManager(linearLayoutManager);
        cart_Adapter cart_adapter = new cart_Adapter(mainActivity.getListCart(), new imageClickListenner() {
            @Override
            public void onImageClickAdd(cart cart) {
                tv_sumPrice.setText(mainActivity.sumPrice(mainActivity.getListCart()) + " Thanh Toán");
            }

            @Override
            public void onImageClickRule(cart cart) {
                tv_sumPrice.setText(mainActivity.sumPrice(mainActivity.getListCart())+ " Thanh Toán");
            }
        });
        rcvCart.setAdapter(cart_adapter);

        tv_sumPrice = view.findViewById(R.id.tv_sum_price_cart);
        tv_sumPrice.setText(mainActivity.sumPrice(mainActivity.getListCart()) + " Thanh Toán");

        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        tv_clear_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.clearCart();
                cart_adapter.setData(MainActivity.getListCart());
                tv_sumPrice.setText("0đ");
            }
        });

        goTo_Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.goToDetailFragment();
            }
        });
        return view;
    }
}