package com.example.productbtl.Fragment

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
import androidx.fragment.app.Fragment
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

class CartFragment : Fragment() {
    private var rcvCart: RecyclerView? = null
    private var mainActivity: MainActivity? = null
    private var tv_sumPrice: TextView? = null
    private var tv_price: TextView? = null
    private var tv_amount: TextView? = null
    private var tv_clear_cart: TextView? = null
    private var imageView_back: ImageView? = null
    private var goTo_Detail: LinearLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        mainActivity = activity as MainActivity?
        rcvCart = view.findViewById(R.id.RecycView_cart)
        tv_price = view.findViewById(R.id.tv_price_cart)
        tv_amount = view.findViewById(R.id.tv_amount_cart)
        imageView_back = view.findViewById(R.id.back)
        tv_clear_cart = view.findViewById(R.id.clear_cart)
        goTo_Detail = view.findViewById(R.id.goTo_Detail)
        val linearLayoutManager = LinearLayoutManager(mainActivity)
        rcvCart.setLayoutManager(linearLayoutManager)
        val cart_adapter =
            cart_Adapter(MainActivity.Companion.getListCart(), object : imageClickListenner {
                override fun onImageClickAdd(cart: cart?) {
                    tv_sumPrice!!.text =
                        mainActivity!!.sumPrice(MainActivity.Companion.getListCart()) + " Thanh Toán"
                }

                override fun onImageClickRule(cart: cart?) {
                    tv_sumPrice!!.text =
                        mainActivity!!.sumPrice(MainActivity.Companion.getListCart()) + " Thanh Toán"
                }
            })
        rcvCart.setAdapter(cart_adapter)
        tv_sumPrice = view.findViewById(R.id.tv_sum_price_cart)
        tv_sumPrice.setText(mainActivity!!.sumPrice(MainActivity.Companion.getListCart()) + " Thanh Toán")
        imageView_back.setOnClickListener(View.OnClickListener { activity!!.supportFragmentManager.popBackStack() })
        tv_clear_cart.setOnClickListener(View.OnClickListener {
            MainActivity.Companion.clearCart()
            cart_adapter.setData(MainActivity.Companion.getListCart())
            tv_sumPrice.setText("0đ")
        })
        goTo_Detail.setOnClickListener(View.OnClickListener { mainActivity!!.goToDetailFragment() })
        return view
    }
}