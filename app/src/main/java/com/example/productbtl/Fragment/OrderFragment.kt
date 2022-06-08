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

class OrderFragment(
    private val img: String?,
    private val name: String?,
    private val price: Long?,
    private val information: String?
) : Fragment() {
    private var tv_name: TextView? = null
    private var tv_price: TextView? = null
    private var tv_composition: TextView? = null
    private var tv_name_title: TextView? = null
    private var img_order: ImageView? = null
    private var img_cart: ImageView? = null
    private var btn_add_to_cart: LinearLayout? = null
    var nav: BottomNavigationView? = null
    private var mainActivity: MainActivity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        mainActivity = activity as MainActivity?
        tv_name = view.findViewById(R.id.tv_name_order)
        tv_price = view.findViewById(R.id.tv_price_order)
        tv_composition = view.findViewById(R.id.tv_composition_order)
        img_order = view.findViewById(R.id.img_order)
        tv_name_title = view.findViewById(R.id.tv_name_product)
        btn_add_to_cart = view.findViewById(R.id.btn_add_to_cart)
        img_cart = view.findViewById(R.id.img_cart2)
        tv_name_title.setText(name)
        tv_name.setText(name)
        tv_price.setText(MainActivity.Companion.formatNumber(price))
        tv_composition.setText(information)
        Glide.with(context!!).load(img)
            .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
            .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
            .into(img_order)
        val img_back_to_product = view.findViewById<ImageView>(R.id.click_Back_To_Product)
        img_back_to_product.setOnClickListener { activity!!.supportFragmentManager.popBackStack() }
        btn_add_to_cart.setOnClickListener(View.OnClickListener {
            val cart = cart(name, img, price, "1")
            mainActivity!!.addToListCart(cart)
        })
        img_cart.setOnClickListener(View.OnClickListener { mainActivity!!.goToCartFragment() })
        return view
    }
}