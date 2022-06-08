package com.example.productbtl.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.productbtl.Activity.MainActivity;
import com.example.productbtl.Adapter.cart_Adapter;
import com.example.productbtl.Adapter.detail_Adapter;
import com.example.productbtl.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;


public class DetailCartFragment extends Fragment {

    private RecyclerView rcv_detail_cart;
    private MainActivity mainActivity;
    private TextView tv_price , tv_name , tv_address , tv_phoneNumber;
    private ImageView img_back_to_cart;
    private LinearLayout order_cart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_cart, container, false);

        mainActivity = (MainActivity) getActivity();
        rcv_detail_cart = view.findViewById(R.id.rcv_detail_cart);
        tv_price = view.findViewById(R.id.tv_sum_price_detail);
        img_back_to_cart = view.findViewById(R.id.back_to_cart);
        order_cart = view.findViewById(R.id.order_cart);
        tv_address = view.findViewById(R.id.tv_Address_detail);
        tv_name = view.findViewById(R.id.tv_name_detail);
        tv_phoneNumber = view.findViewById(R.id.tv_phoneNumber_detail);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcv_detail_cart.setLayoutManager(linearLayoutManager);
        detail_Adapter detail_adapter = new detail_Adapter(mainActivity.getListCart());
        rcv_detail_cart.setAdapter(detail_adapter);

        tv_price.setText(mainActivity.sumPrice(mainActivity.getListCart()));

        img_back_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.getSupportFragmentManager().popBackStack(

                );
            }
        });
        order_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putDataToFireBase();
                mainActivity.bottomNavigationView.setVisibility(view.VISIBLE);

            }
        });

        return view;
    }

    private void putDataToFireBase() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uer = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference("Data_User:"+ uer);

        Map<String, String> map = new HashMap<>();
        String odrKey = myRef.push().getKey();

        Date date = new Date(System.currentTimeMillis());
        map.put("id",odrKey);
        map.put("price",mainActivity.sumPrice(mainActivity.getListCart()));
        map.put("date",date.toString());
        map.put("name",tv_name.getText().toString().trim());
        map.put("address",tv_address.getText().toString().trim());
        map.put("phonenumber",tv_phoneNumber.getText().toString().trim());
        map.put("status","Đang chuẩn bị hàng");
        map.put("product",mainActivity.getNameListCart());

        myRef.child(odrKey).setValue(map);
        mainActivity.goToReceiptFragment();
        mainActivity.clearCart();

    }
}