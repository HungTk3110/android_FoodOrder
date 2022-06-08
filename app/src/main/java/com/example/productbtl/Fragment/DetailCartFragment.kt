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
import java.sql.Date
import java.util.HashMap

class DetailCartFragment : Fragment() {
    private var rcv_detail_cart: RecyclerView? = null
    private var mainActivity: MainActivity? = null
    private var tv_price: TextView? = null
    private var tv_name: TextView? = null
    private var tv_address: TextView? = null
    private var tv_phoneNumber: TextView? = null
    private var img_back_to_cart: ImageView? = null
    private var order_cart: LinearLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_cart, container, false)
        mainActivity = activity as MainActivity?
        rcv_detail_cart = view.findViewById(R.id.rcv_detail_cart)
        tv_price = view.findViewById(R.id.tv_sum_price_detail)
        img_back_to_cart = view.findViewById(R.id.back_to_cart)
        order_cart = view.findViewById(R.id.order_cart)
        tv_address = view.findViewById(R.id.tv_Address_detail)
        tv_name = view.findViewById(R.id.tv_name_detail)
        tv_phoneNumber = view.findViewById(R.id.tv_phoneNumber_detail)
        val linearLayoutManager = LinearLayoutManager(mainActivity)
        rcv_detail_cart.setLayoutManager(linearLayoutManager)
        val detail_adapter = detail_Adapter(MainActivity.Companion.getListCart())
        rcv_detail_cart.setAdapter(detail_adapter)
        tv_price.setText(mainActivity!!.sumPrice(MainActivity.Companion.getListCart()))
        img_back_to_cart.setOnClickListener(View.OnClickListener { mainActivity!!.supportFragmentManager.popBackStack() })
        order_cart.setOnClickListener(View.OnClickListener {
            putDataToFireBase()
            mainActivity!!.bottomNavigationView!!.visibility = View.VISIBLE
        })
        return view
    }

    private fun putDataToFireBase() {
        val database = FirebaseDatabase.getInstance()
        val uer = FirebaseAuth.getInstance().currentUser!!.uid
        val myRef = database.getReference("Data_User:$uer")
        val map: MutableMap<String, String?> = HashMap()
        val odrKey = myRef.push().key
        val date = Date(System.currentTimeMillis())
        map["id"] = odrKey
        map["price"] = mainActivity!!.sumPrice(MainActivity.Companion.getListCart())
        map["date"] = date.toString()
        map["name"] = tv_name!!.text.toString().trim { it <= ' ' }
        map["address"] = tv_address!!.text.toString().trim { it <= ' ' }
        map["phonenumber"] = tv_phoneNumber!!.text.toString().trim { it <= ' ' }
        map["status"] = "Đang chuẩn bị hàng"
        map["product"] = mainActivity.getNameListCart()
        myRef.child(odrKey!!).setValue(map)
        mainActivity!!.goToReceiptFragment()
        MainActivity.Companion.clearCart()
    }
}