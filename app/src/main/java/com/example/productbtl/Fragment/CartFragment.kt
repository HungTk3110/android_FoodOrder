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
import com.example.productbtl.databinding.ActivityMainBinding
import com.example.productbtl.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private var rcvCart: RecyclerView? = null
    private var mainActivity: MainActivity? = null
    private lateinit var binding: FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater)
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        mainActivity = activity as MainActivity?
        rcvCart = view.findViewById(R.id.RecycView_cart)
        binding.RecycViewCart.setLayoutManager(LinearLayoutManager)
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
        return binding.root
    }
}