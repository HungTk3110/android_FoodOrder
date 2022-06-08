package com.example.productbtl.Fragment

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
import com.example.productbtl.Object.*

class ProductFragment : Fragment() {
    private var img_back: ImageView? = null
    private var img_go_to_cart: ImageView? = null
    private var tv: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: product_Adapter? = null
    private var mainActivity: MainActivity? = null
    private var obj_menu: menu? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        img_back = view.findViewById(R.id.click_back)
        tv = view.findViewById(R.id.tv_menu)
        img_go_to_cart = view.findViewById(R.id.img_go_to_cart)
        mainActivity = activity as MainActivity?
        mainActivity!!.bottomNavigationView!!.visibility = View.GONE
        val bundle = arguments
        if (bundle != null) {
            obj_menu = bundle["object_menu"] as menu?
            if (obj_menu != null) {
                tv.setText(obj_menu.getName())
                recyclerView = view.findViewById<View>(R.id.rcv_product) as RecyclerView
                recyclerView!!.layoutManager = GridLayoutManager(context, 2)
                val options = FirebaseRecyclerOptions.Builder<product>()
                    .setQuery(
                        FirebaseDatabase.getInstance().reference.child(obj_menu.getName()),
                        product::class.java
                    )
                    .build()
                adapter = product_Adapter(options)
                recyclerView!!.adapter = adapter
            }
        }
        img_back.setOnClickListener(View.OnClickListener {
            mainActivity!!.bottomNavigationView!!.visibility = View.VISIBLE
            activity!!.supportFragmentManager.popBackStack()
        })
        img_go_to_cart.setOnClickListener(View.OnClickListener { mainActivity!!.goToCartFragment() })
        return view
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }

    companion object {
        val TAG = ProductFragment::class.java.name
    }
}