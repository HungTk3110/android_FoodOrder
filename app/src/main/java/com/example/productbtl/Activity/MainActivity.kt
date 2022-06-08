package com.example.productbtl.Activity

import com.example.productbtl.myinterface.imageClickListenner
import androidx.recyclerview.widget.RecyclerView
import com.example.productbtl.Adapter.cart_Adapter.cart_AdapterHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.productbtl.R
import com.example.productbtl.Activity.MainActivity
import com.bumptech.glide.Glide
import android.widget.TextView
import com.example.productbtl.Adapter.menu_Adapter.clickItemMenu
import com.example.productbtl.Adapter.menu_Adapter.menu_AdapterHolder
import android.widget.LinearLayout
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
import android.widget.EditText
import android.app.ProgressDialog
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.example.productbtl.Activity.RegesterActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import android.widget.Toast
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
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null
    private val homeFragment = HomeFragment()
    private val receiptFragment = ReceiptFragment()
    private val profileFragment = ProfileFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, homeFragment).commit()
        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, homeFragment).commit()
                    return@OnItemSelectedListener true
                }
                R.id.nav_receipt -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, receiptFragment).commit()
                    return@OnItemSelectedListener true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, profileFragment).commit()
                    return@OnItemSelectedListener true
                }
            }
            false
        })
    }

    fun goToProductFragment(menu: menu?) {
        val productFragment = ProductFragment()
        val bundle = Bundle()
        bundle.putSerializable("object_menu", menu)
        productFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, productFragment)
            .addToBackStack(ProductFragment.Companion.TAG).commit()
    }

    fun goToCartFragment() {
        val cartFragment = CartFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, cartFragment)
            .addToBackStack(null).commit()
    }

    fun addToListCart(cart: cart) {
        if (ckeckAddToCart(cart) == false) listCart.add(cart)
    }

    fun sumPrice(list: List<cart>): String {
        var sum: Long = 0
        for (i in list.indices) {
            sum += list[i].price
        }
        return formatNumber(sum)
    }

    val nameListCart: String
        get() {
            var str = ""
            for (i in listCart.indices) {
                str += listCart[i].name + " x" + (listCart[i].amount + ", ")
            }
            return str
        }

    fun goToDetailFragment() {
        val detailCartFragment = DetailCartFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, detailCartFragment)
            .addToBackStack(null).commit()
    }

    fun goToReceiptFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, receiptFragment)
            .commit()
        bottomNavigationView!!.selectedItemId = R.id.nav_receipt
    }

    companion object {
        val listCart: MutableList<cart> = ArrayList()
        fun ckeckAddToCart(cart: cart): Boolean {
            for (i in listCart.indices) {
                if (listCart[i].name == cart.name) {
                    val temp = listCart[i].price / listCart[i].amount.toLong()
                    listCart[i].price = listCart[i].price + temp
                    val amount = listCart[i].amount.toInt() + 1
                    listCart[i].amount = amount.toString()
                    return true
                }
            }
            return false
        }

        fun removeToListCart() {
            for (i in listCart.indices) {
                if (listCart[i].amount.toInt() < 1) {
                    listCart.removeAt(i)
                }
            }
        }

        fun clearCart() {
            listCart.clear()
        }

        fun formatNumber(number: Long?): String {
            val localeEN = Locale("en", "EN")
            val en = NumberFormat.getInstance(localeEN)
            return en.format(number) + "đ"
        }

        fun addAmount(cart: cart, price: Long) {
            cart.amount = (cart.amount.toInt() + 1).toString()
            cart.price = price + cart.price
        }

        fun ruleAmount(cart: cart, price: Long) {
            cart.amount = (cart.amount.toInt() - 1).toString()
            removeToListCart()
            cart.price = cart.price - price
        }
    }
}