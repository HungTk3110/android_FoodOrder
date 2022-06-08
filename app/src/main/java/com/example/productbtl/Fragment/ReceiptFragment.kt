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
import android.widget.EditText
import android.app.ProgressDialog
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.view.View
import com.example.productbtl.Activity.RegesterActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import android.widget.Toast
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
import java.util.*

class ReceiptFragment : Fragment() {
    private var rcv_receipt: RecyclerView? = null
    private var mainActivity: MainActivity? = null
    private var receipt_adapter: receipt_Adapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_receipt, container, false)
        rcv_receipt = view.findViewById(R.id.rcv_receipt)
        mainActivity = activity as MainActivity?
        val linearLayoutManager = LinearLayoutManager(mainActivity)
        rcv_receipt.setLayoutManager(linearLayoutManager)
        receipt_adapter = receipt_Adapter(listDataFromFireBase) { receipt -> deleteItem(receipt) }
        rcv_receipt.setAdapter(receipt_adapter)
        return view
    }

    private fun deleteItem(receipt: receipt) {
        val database = FirebaseDatabase.getInstance()
        val uer = FirebaseAuth.getInstance().currentUser!!.uid
        val myRef = database.getReference("Data_User:" + uer + "/" + receipt.id)
        myRef.removeValue()
        receipt_adapter!!.setData(listDataFromFireBase)
    }

    // đảo ngược list
    private val listDataFromFireBase: List<receipt?>
        private get() {
            val list: MutableList<receipt?> = ArrayList()
            val database = FirebaseDatabase.getInstance()
            val uer = FirebaseAuth.getInstance().currentUser!!.uid
            val myRef = database.getReference("Data_User:$uer")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnapshot in snapshot.children) {
                        val mreceipt = dataSnapshot.getValue(receipt::class.java)
                        list.add(mreceipt)
                    }
                    // đảo ngược list
                    Collections.reverse(list)
                    receipt_adapter!!.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, "Lỗi", Toast.LENGTH_LONG).show()
                }
            })
            return list
        }
}