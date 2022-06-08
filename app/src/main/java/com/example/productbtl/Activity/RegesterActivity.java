package com.example.productbtl.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.productbtl.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegesterActivity extends AppCompatActivity {

    private EditText  edt_email  , edt_password ,edt_password_again;
    private TextView go_To_Login;
    private Button btn_regester;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);

        AnhXa();
        btn_regester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regesterUser();
            }
        });
        go_To_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegesterActivity.this,LoginActivity.class));
            }
        });
    }

    private void regesterUser() {
        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        if(email.indexOf("@") == -1)
        {
            edt_email.setError("Vui lòng nhập email đúng định dạng");
            edt_email.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            edt_email.setError("Email không được để trống");
            edt_email.requestFocus();
            return;
        }

        if(password.length() < 6 )
        {
            edt_password.setError("mật khẩu phải từ 6 ký tự trở lên");
            edt_password.requestFocus();
            return;
        }
        if(edt_password_again.getText().toString().trim().equals(password) == false)
        {
            edt_password_again.setError("Mật khẩu không trùng");
            edt_password_again.requestFocus();
            return;
        }
        mAuth = FirebaseAuth.getInstance();

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(RegesterActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegesterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(RegesterActivity.this,"Email đã được đăng ký" , Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void AnhXa() {
        edt_email = (EditText) findViewById(R.id.edt_Email_regester);
        edt_password = (EditText) findViewById(R.id.edt_password_regester);
        btn_regester = (Button) findViewById(R.id.bt_regester);
        edt_password_again = (EditText) findViewById(R.id.edt_password_regester_again);
        progressDialog = new ProgressDialog(this);
        go_To_Login = findViewById(R.id.go_To_Login);
    }
}