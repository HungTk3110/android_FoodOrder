package com.example.productbtl.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.productbtl.Adapter.category_Adapter;
import com.example.productbtl.Adapter.menu_Adapter;
import com.example.productbtl.Activity.MainActivity;
import com.example.productbtl.Adapter.slideshow_Adapter;
import com.example.productbtl.Object.category;
import com.example.productbtl.Object.menu;
import com.example.productbtl.Object.product;
import com.example.productbtl.Object.slideshow;
import com.example.productbtl.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    private RecyclerView rcvMenu , rcvCategory;
    private MainActivity mainActivity ;
    private List<slideshow> list_slideShow;
    private ViewPager viewPager_slideshow;
    private CircleIndicator circleIndicator;
    private Handler handler_slideShow;
    private Runnable runnable_slideShow;
    private category_Adapter category_adapter;
    private ImageView img_cart;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mainActivity = (MainActivity) getActivity();
        rcvMenu = view.findViewById(R.id.RecycView_menu);
        viewPager_slideshow = view.findViewById(R.id.view_pager_slideshow);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        rcvCategory = view.findViewById(R.id.rcv_category);
        img_cart = view.findViewById(R.id.img_cart_home);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,4);;
        rcvMenu.setLayoutManager(gridLayoutManager);
        menu_Adapter menu_adapter = new menu_Adapter(getListMenu(), new menu_Adapter.clickItemMenu() {
            @Override
            public void onClickItemMenu(menu menu) {
                mainActivity.goToProductFragment(menu);
            }
        });
        rcvMenu.setAdapter(menu_adapter);

        // slide show
        list_slideShow = getListSlideShow();
        slideshow_Adapter slideShow_Adapter = new slideshow_Adapter(list_slideShow);
        viewPager_slideshow.setAdapter(slideShow_Adapter);
        circleIndicator.setViewPager(viewPager_slideshow);
        handler_slideShow = new Handler();
        runnable_slideShow = new Runnable() {
            @Override
            public void run() {
                if(viewPager_slideshow.getCurrentItem() == list_slideShow.size() -1){
                    viewPager_slideshow.setCurrentItem(0);
                }
                else
                    viewPager_slideshow.setCurrentItem(viewPager_slideshow.getCurrentItem() + 1);
            }
        };
        handler_slideShow.postDelayed(runnable_slideShow ,2000);
        viewPager_slideshow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                handler_slideShow.removeCallbacks(runnable_slideShow);
                handler_slideShow.postDelayed(runnable_slideShow ,3000);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // recycview horidetor
        category_adapter = new category_Adapter(mainActivity);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity ,RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        category_adapter.setData(getListCategory());
        rcvCategory.setAdapter(category_adapter);

        /// onclick go to cart
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.goToCartFragment();
            }
        });

        return view;
    }

    private List<category> getListCategory() {
        List<category> list_category= new ArrayList<>();
        List<product> list = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Trái Cây");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    product product = dataSnapshot.getValue(product.class);
                    list.add(product);
                }
            category_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(mainActivity, "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        list_category.add( new category("Flash Sale:",list));
        return list_category;
    }

    private List<slideshow> getListSlideShow() {
        List<slideshow> list = new ArrayList<>();
        list.add(new slideshow(R.drawable.slide1));
        list.add(new slideshow(R.drawable.slide2));
        list.add(new slideshow(R.drawable.slide3));
        return list;
    }

    private List<menu> getListMenu() {
        List<menu> list = new ArrayList<>();
        list.add(new menu("Trái Cây",R.drawable.traicay));
        list.add(new menu("Rau Củ",R.drawable.raucu));
        list.add(new menu("Thịt Heo",R.drawable.thitheo));
        list.add(new menu("Thịt Gà",R.drawable.thitga));
        list.add(new menu("Hải Sản",R.drawable.haisan));
        list.add(new menu("Lẩu",R.drawable.lau));
        list.add(new menu("Sữa",R.drawable.sua));
        list.add(new menu("Bánh",R.drawable.banh));
        return list;
    }
}