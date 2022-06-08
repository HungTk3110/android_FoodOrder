package com.example.productbtl.Activity

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
import android.util.Patterns
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

class LoginActivity : AppCompatActivity() {
    private var tv_regester: TextView? = null
    private var btn_login: Button? = null
    private var edt_email: EditText? = null
    private var edt_password: EditText? = null
    private var progressDialog: ProgressDialog? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        anhXa()
        btn_login!!.setOnClickListener { userLogin() }
        tv_regester!!.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegesterActivity::class.java
                )
            )
        }
    }

    private fun userLogin() {
        val email = edt_email!!.text.toString().trim { it <= ' ' }
        val password = edt_password!!.text.toString().trim { it <= ' ' }
        if (email.isEmpty()) {
            edt_email!!.error = "Email không đưuọc để rỗng"
            edt_email!!.requestFocus()
            return
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        }
        progressDialog!!.show()
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressDialog!!.dismiss()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            } else {
                progressDialog!!.dismiss()
                Toast.makeText(
                    this@LoginActivity,
                    "Email hoặc mật khẩu không chính xác",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun anhXa() {
        btn_login = findViewById<View>(R.id.btn_login) as Button
        edt_email = findViewById<View>(R.id.edt_Email_login) as EditText
        edt_password = findViewById(R.id.edt_password_login)
        progressDialog = ProgressDialog(this)
        tv_regester = findViewById(R.id.tv_Regester)
    }
}