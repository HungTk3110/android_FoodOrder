package com.example.productbtl.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.productbtl.Object.slideshow;
import com.example.productbtl.R;

import java.util.List;

public class slideshow_Adapter extends PagerAdapter {
    List<slideshow> list;

    public slideshow_Adapter(List<slideshow> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slideshow_items,container,false);
        ImageView imageView = view.findViewById(R.id.img_slideshow);

        slideshow  opj_ss = list.get(position);
        imageView.setImageResource(opj_ss.getId());

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(list !=null)
            return list.size();
        else
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
