package com.example.productbtl.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.productbtl.Activity.MainActivity;
import com.example.productbtl.Object.cart;
import com.example.productbtl.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class OrderFragment extends Fragment {

    private TextView tv_name  , tv_price , tv_composition , tv_name_title;
    private ImageView img_order , img_cart;
    private LinearLayout btn_add_to_cart;
    private String img,  name,  information ;
    private Long price;
    BottomNavigationView nav;

    private MainActivity mainActivity ;

    public OrderFragment(String img, String name, Long price, String information) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.information = information;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        mainActivity = ((MainActivity) getActivity()) ;

        tv_name = view.findViewById(R.id.tv_name_order);
        tv_price = view.findViewById(R.id.tv_price_order);
        tv_composition = view.findViewById(R.id.tv_composition_order);
        img_order = view.findViewById(R.id.img_order);
        tv_name_title = view.findViewById(R.id.tv_name_product);
        btn_add_to_cart = view.findViewById(R.id.btn_add_to_cart);
        img_cart = view.findViewById(R.id.img_cart2);

        tv_name_title.setText(name);
        tv_name.setText(name);
        tv_price.setText(mainActivity.formatNumber(price));
        tv_composition.setText(information);
        Glide.with(getContext()).load(img)
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(img_order);

        ImageView img_back_to_product = view.findViewById(R.id.click_Back_To_Product);
        img_back_to_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart cart = new cart(name ,img , price ,"1" );
                mainActivity.addToListCart(cart);
            }
        });

        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.goToCartFragment();
            }
        });

        return view;
    }

}