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

class receipt_Adapter(
    private var list: List<receipt?>?,
    private val listenner: buttonClickListenner
) : RecyclerView.Adapter<receipt_Adapterholder>() {
    fun setData(list: List<receipt?>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): receipt_Adapterholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.receipt_items, parent, false)
        return receipt_Adapterholder(view)
    }

    override fun onBindViewHolder(holder: receipt_Adapterholder, position: Int) {
        val receipt = list!![position] ?: return
        holder.tv_address.text = receipt.address
        holder.tv_date.text = receipt.date
        holder.tv_name.text = receipt.name
        holder.tv_id.text = receipt.id
        holder.tv_phonenumber.text = receipt.phonenumber
        holder.tv_price.text = receipt.price
        holder.tv_product.text = receipt.product
        holder.tv_status.text = receipt.status
        holder.btn_delete.setOnClickListener { listenner.onClickDeleteItem(receipt) }
    }

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    inner class receipt_Adapterholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name: TextView
        val tv_date: TextView
        val tv_address: TextView
        val tv_id: TextView
        val tv_phonenumber: TextView
        val tv_status: TextView
        val tv_product: TextView
        val tv_price: TextView
        val btn_delete: Button

        init {
            tv_address = itemView.findViewById(R.id.address_of_receipt)
            tv_date = itemView.findViewById(R.id.date_of_receipt)
            tv_name = itemView.findViewById(R.id.name_of_receipt)
            tv_id = itemView.findViewById(R.id.id_of_receipt)
            tv_phonenumber = itemView.findViewById(R.id.phone_of_receipt)
            tv_price = itemView.findViewById(R.id.price_of_receipt)
            tv_product = itemView.findViewById(R.id.product_of_receipt)
            tv_status = itemView.findViewById(R.id.status_of_receipt)
            btn_delete = itemView.findViewById(R.id.btn_delete_receipt)
        }
    }
}