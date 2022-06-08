package com.example.productbtl.Adapter

import com.example.productbtl.Object.product
import com.example.productbtl.Object.cart
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
import com.example.productbtl.Object.receipt
import com.example.productbtl.myinterface.buttonClickListenner
import com.example.productbtl.Adapter.receipt_Adapter.receipt_Adapterholder
import com.example.productbtl.Adapter.category_Adapter.categoryViewHolder
import com.example.productbtl.Object.category
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productbtl.Adapter.rcvCategory_Adapter
import com.example.productbtl.Object.slideshow
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

class cart_Adapter(private var list: List<cart>?, private val listenner: imageClickListenner) :
    RecyclerView.Adapter<cart_AdapterHolder>() {
    fun setData(list: List<cart>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cart_AdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return cart_AdapterHolder(view)
    }

    override fun onBindViewHolder(holder: cart_AdapterHolder, position: Int) {
        val cart = list!![position]
        val price = cart.price / cart.amount.toLong()
        if (cart == null) {
            return
        }
        holder.tv_name.text = cart.name
        holder.tv_price.setText(MainActivity.Companion.formatNumber(cart.price))
        holder.tv_amount.text = cart.amount
        Glide.with(holder.imageView.context)
            .load(cart.img)
            .into(holder.imageView)
        holder.add_amount.setOnClickListener {
            MainActivity.Companion.addAmount(cart, price)
            holder.tv_amount.text = cart.amount
            holder.tv_price.setText(MainActivity.Companion.formatNumber(cart.price))
            setData(MainActivity.Companion.getListCart())
            listenner.onImageClickAdd(cart)
        }
        holder.rule_amount.setOnClickListener {
            MainActivity.Companion.ruleAmount(cart, price)
            holder.tv_amount.text = cart.amount
            holder.tv_price.setText(MainActivity.Companion.formatNumber(cart.price))
            setData(MainActivity.Companion.getListCart())
            listenner.onImageClickRule(cart)
        }
    }

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    inner class cart_AdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name: TextView
        val tv_price: TextView
        val tv_amount: TextView
        val imageView: ImageView
        val add_amount: ImageView
        val rule_amount: ImageView

        init {
            tv_name = itemView.findViewById(R.id.tv_name_cart)
            tv_price = itemView.findViewById(R.id.tv_price_cart)
            tv_amount = itemView.findViewById(R.id.tv_amount_cart)
            imageView = itemView.findViewById(R.id.img_cart)
            add_amount = itemView.findViewById<View>(R.id.add_amount_cart) as ImageView
            rule_amount = itemView.findViewById<View>(R.id.rule_amount_cart) as ImageView
        }
    }
}