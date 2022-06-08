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

class RegesterActivity : AppCompatActivity() {
    private var edt_email: EditText? = null
    private var edt_password: EditText? = null
    private var edt_password_again: EditText? = null
    private var go_To_Login: TextView? = null
    private var btn_regester: Button? = null
    private var progressDialog: ProgressDialog? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regester)
        AnhXa()
        btn_regester!!.setOnClickListener { regesterUser() }
        go_To_Login!!.setOnClickListener {
            startActivity(
                Intent(
                    this@RegesterActivity,
                    LoginActivity::class.java
                )
            )
        }
    }

    private fun regesterUser() {
        val email = edt_email!!.text.toString().trim { it <= ' ' }
        val password = edt_password!!.text.toString().trim { it <= ' ' }
        if (email.indexOf("@") == -1) {
            edt_email!!.error = "Vui lòng nhập email đúng định dạng"
            edt_email!!.requestFocus()
            return
        }
        if (email.isEmpty()) {
            edt_email!!.error = "Email không được để trống"
            edt_email!!.requestFocus()
            return
        }
        if (password.length < 6) {
            edt_password!!.error = "mật khẩu phải từ 6 ký tự trở lên"
            edt_password!!.requestFocus()
            return
        }
        if (edt_password_again!!.text.toString().trim { it <= ' ' } == password == false) {
            edt_password_again!!.error = "Mật khẩu không trùng"
            edt_password_again!!.requestFocus()
            return
        }
        mAuth = FirebaseAuth.getInstance()
        progressDialog!!.show()
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressDialog!!.dismiss()
                    Toast.makeText(this@RegesterActivity, "Đăng Ký Thành Công", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@RegesterActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    progressDialog!!.dismiss()
                    Toast.makeText(
                        this@RegesterActivity,
                        "Email đã được đăng ký",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun AnhXa() {
        edt_email = findViewById<View>(R.id.edt_Email_regester) as EditText
        edt_password = findViewById<View>(R.id.edt_password_regester) as EditText
        btn_regester = findViewById<View>(R.id.bt_regester) as Button
        edt_password_again = findViewById<View>(R.id.edt_password_regester_again) as EditText
        progressDialog = ProgressDialog(this)
        go_To_Login = findViewById(R.id.go_To_Login)
    }
}