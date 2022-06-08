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
import android.os.Handler
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
import java.util.ArrayList

class HomeFragment : Fragment() {
    private var rcvMenu: RecyclerView? = null
    private var rcvCategory: RecyclerView? = null
    private var mainActivity: MainActivity? = null
    private var list_slideShow: List<slideshow>? = null
    private var viewPager_slideshow: ViewPager? = null
    private var circleIndicator: CircleIndicator? = null
    private var handler_slideShow: Handler? = null
    private var runnable_slideShow: Runnable? = null
    private var category_adapter: category_Adapter? = null
    private var img_cart: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        mainActivity = activity as MainActivity?
        rcvMenu = view.findViewById(R.id.RecycView_menu)
        viewPager_slideshow = view.findViewById(R.id.view_pager_slideshow)
        circleIndicator = view.findViewById(R.id.circle_indicator)
        rcvCategory = view.findViewById(R.id.rcv_category)
        img_cart = view.findViewById(R.id.img_cart_home)
        val gridLayoutManager = GridLayoutManager(mainActivity, 4)
        rcvMenu.setLayoutManager(gridLayoutManager)
        val menu_adapter =
            menu_Adapter(listMenu) { menu -> mainActivity!!.goToProductFragment(menu) }
        rcvMenu.setAdapter(menu_adapter)

        // slide show
        list_slideShow = listSlideShow
        val slideShow_Adapter = slideshow_Adapter(list_slideShow)
        viewPager_slideshow.setAdapter(slideShow_Adapter)
        circleIndicator.setViewPager(viewPager_slideshow)
        handler_slideShow = Handler()
        runnable_slideShow = Runnable {
            if (viewPager_slideshow.getCurrentItem() == list_slideShow!!.size - 1) {
                viewPager_slideshow.setCurrentItem(0)
            } else viewPager_slideshow.setCurrentItem(viewPager_slideshow.getCurrentItem() + 1)
        }
        handler_slideShow!!.postDelayed(runnable_slideShow, 2000)
        viewPager_slideshow.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                handler_slideShow!!.removeCallbacks(runnable_slideShow)
                handler_slideShow!!.postDelayed(runnable_slideShow, 3000)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        // recycview horidetor
        category_adapter = category_Adapter(mainActivity)
        val linearLayoutManager = LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false)
        rcvCategory.setLayoutManager(linearLayoutManager)
        category_adapter!!.setData(listCategory)
        rcvCategory.setAdapter(category_adapter)

        /// onclick go to cart
        img_cart.setOnClickListener(View.OnClickListener { mainActivity!!.goToCartFragment() })
        return view
    }

    private val listCategory: List<category>
        private get() {
            val list_category: MutableList<category> = ArrayList()
            val list: MutableList<product?> = ArrayList()
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Trái Cây")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnapshot in snapshot.children) {
                        val product = dataSnapshot.getValue(product::class.java)
                        list.add(product)
                    }
                    category_adapter!!.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(mainActivity, "lỗi", Toast.LENGTH_SHORT).show()
                }
            })
            list_category.add(category("Flash Sale:", list))
            return list_category
        }
    private val listSlideShow: List<slideshow>
        private get() {
            val list: MutableList<slideshow> = ArrayList()
            list.add(slideshow(R.drawable.slide1))
            list.add(slideshow(R.drawable.slide2))
            list.add(slideshow(R.drawable.slide3))
            return list
        }
    private val listMenu: List<menu>
        private get() {
            val list: MutableList<menu> = ArrayList()
            list.add(menu("Trái Cây", R.drawable.traicay))
            list.add(menu("Rau Củ", R.drawable.raucu))
            list.add(menu("Thịt Heo", R.drawable.thitheo))
            list.add(menu("Thịt Gà", R.drawable.thitga))
            list.add(menu("Hải Sản", R.drawable.haisan))
            list.add(menu("Lẩu", R.drawable.lau))
            list.add(menu("Sữa", R.drawable.sua))
            list.add(menu("Bánh", R.drawable.banh))
            return list
        }
}