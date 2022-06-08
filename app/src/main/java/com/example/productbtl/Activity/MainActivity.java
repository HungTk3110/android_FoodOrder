package com.example.productbtl.Activity;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.productbtl.Fragment.CartFragment;
import com.example.productbtl.Fragment.DetailCartFragment;
import com.example.productbtl.Fragment.HomeFragment;
import com.example.productbtl.Fragment.OrderFragment;
import com.example.productbtl.Fragment.ProductFragment;
import com.example.productbtl.Fragment.ProfileFragment;
import com.example.productbtl.Fragment.ReceiptFragment;
import com.example.productbtl.Object.cart;
import com.example.productbtl.Object.menu;
import com.example.productbtl.Object.product;
import com.example.productbtl.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private ReceiptFragment receiptFragment = new ReceiptFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private static List<cart> listCart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
                        return true ;
                    case R.id.nav_receipt:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,receiptFragment).commit();
                        return true;
                    case R.id.nav_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,profileFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }


    public void goToProductFragment(menu menu) {
        ProductFragment productFragment = new ProductFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_menu", menu);
        productFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,productFragment).addToBackStack(ProductFragment.TAG).commit();

    }

    public void goToCartFragment(){
        CartFragment cartFragment = new CartFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,cartFragment).addToBackStack(null).commit();
    }

    public static List<cart> getListCart() {
        return listCart;
    }

    public void addToListCart(cart cart){
       if(ckeckAddToCart(cart) == false)
        this.listCart.add(cart);
    }

    public static boolean ckeckAddToCart(cart cart){
        for (int i =0 ; i<listCart.size();i++){
            if(listCart.get(i).getName().equals( cart.getName())){
                Long temp = listCart.get(i).getPrice() / Long.parseLong(listCart.get(i).getAmount());
                listCart.get(i).setPrice( listCart.get(i).getPrice() +temp);
                int amount = Integer.parseInt(listCart.get(i).getAmount()) +1;
                listCart.get(i).setAmount( String.valueOf(amount));
                return true;
            }
        }
        return false;
    }
    public static void removeToListCart(){
        for(int i=0 ; i< listCart.size(); i++){
            if( Integer.parseInt(listCart.get(i).getAmount()) < 1 ){
                listCart.remove(i);
            }
        }
    }
    public static void clearCart(){getListCart().clear();}

    public static String formatNumber(Long number){
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        String str = en.format(number)+"đ";
        return str;
    }

    public static void addAmount(cart cart, Long price){
        cart.setAmount( String.valueOf(Integer.parseInt(cart.getAmount())+1) );
        cart.setPrice( price + cart.getPrice());
    }
    public static void ruleAmount(cart cart , Long price){
        cart.setAmount( String.valueOf(Integer.parseInt(cart.getAmount())-1) );
            removeToListCart();
            cart.setPrice(cart.getPrice()-price);
    }

    public String sumPrice(List<cart> list){
        long sum = 0;
        for(int i=0 ; i<list.size() ; i++){
            sum += list.get(i).getPrice();
        }
        return formatNumber(sum);
    }
    public String getNameListCart(){
        String str = "";
        for(int i=0 ; i<listCart.size() ; i++){
            str += listCart.get(i).getName() + " x"+ String.valueOf(listCart.get(i).getAmount()+", ");
        }
        return str;
    }

    public void goToDetailFragment(){
        DetailCartFragment detailCartFragment = new DetailCartFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,detailCartFragment).addToBackStack(null).commit();
    }
    public void goToReceiptFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,receiptFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_receipt);
    }
}