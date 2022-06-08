package com.example.productbtl.Adapter

import com.example.productbtl.myinterface.imageClickListenner
import androidx.recyclerview.widget.RecyclerView
import com.example.productbtl.Adapter.cart_Adapter.cart_AdapterHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.productbtl.R
import com.example.productbtl.Activity.MainActivity
import com.bumptech.glide.Glide
import com.example.productbtl.Adapter.menu_Adapter.clickItemMenu
import com.example.productbtl.Adapter.menu_Adapter.menu_AdapterHolder
import com.example.productbtl.Adapter.detail_Adapter.detail_AdapterHolder
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.example.productbtl.Adapter.product_Adapter.product_AdapterHolder
import androidx.appcompat.app.AppCompatActivity
import com.example.productbtl.Fragment.OrderFragment
import com.example.productbtl.myinterface.buttonClickListenner
import com.example.productbtl.Adapter.receipt_Adapter.receipt_Adapterholder
import com.example.productbtl.Adapter.category_Adapter.categoryViewHolder
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productbtl.Adapter.rcvCategory_Adapter
import androidx.viewpager.widget.PagerAdapter
import com.example.productbtl.Adapter.rcvCategory_Adapter.rcvCategory_AdapterHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.productbtl.Fragment.HomeFragment
import com.example.productbtl.Fragment.ReceiptFragment
import com.example.productbtl.Fragment.ProfileFragment
import android.os.Bundle
import com.google.android.material.navigation.NavigationBarView
import com.example.productbtl.Fragment.ProductFragment
import com.example.productbtl.Fragment.CartFragment
import com.example.productbtl.Fragment.DetailCartFragment
import android.app.ProgressDialog
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.view.View
import android.widget.*
import com.example.productbtl.Activity.RegesterActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.example.productbtl.Activity.LoginActivity
import com.example.productbtl.Adapter.cart_Adapter
import androidx.viewpager.widget.ViewPager
import me.relex.circleindicator.CircleIndicator
import com.example.productbtl.Adapter.category_Adapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productbtl.Adapter.menu_Adapter
import com.example.productbtl.Adapter.slideshow_Adapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.example.productbtl.Adapter.product_Adapter
import com.example.productbtl.Adapter.receipt_Adapter
import com.example.productbtl.Adapter.detail_Adapter
import com.example.productbtl.Object.*

class menu_Adapter(private val list: List<menu>?, private val clickItemMenu: clickItemMenu) :
    RecyclerView.Adapter<menu_AdapterHolder>() {
    interface clickItemMenu {
        fun onClickItemMenu(menu: menu?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): menu_AdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_items, parent, false)
        return menu_AdapterHolder(view)
    }

    override fun onBindViewHolder(holder: menu_AdapterHolder, position: Int) {
        val menu = list!![position] ?: return
        holder.textView.text = menu.name
        holder.imageView.setImageResource(menu.img)
        holder.layout.setOnClickListener { clickItemMenu.onClickItemMenu(menu) }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class menu_AdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        val imageView: ImageView
        val layout: LinearLayout

        init {
            textView = itemView.findViewById(R.id.tv_menu_item)
            imageView = itemView.findViewById(R.id.img_menu_item)
            layout = itemView.findViewById(R.id.layout_menu)
        }
    }
}