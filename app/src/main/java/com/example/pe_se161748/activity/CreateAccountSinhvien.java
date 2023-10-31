package com.example.pe_se161748.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pe_se161748.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class CreateAccountSinhvien extends AppCompatActivity {
private EditText extEmail, extPassword;
private Button btnReigs;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_sinhvien);
        mAuth = FirebaseAuth.getInstance();
        extEmail = findViewById(R.id.email);
        extPassword = findViewById(R.id.password);
        btnReigs = findViewById(R.id.btnRegis);
        btnReigs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void register(){
        String email,password;
        email = extEmail.getText().toString();
        password = extPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui lòng nhập email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Vui lòng nhập password",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Tạo thành công tài khoản",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateAccountSinhvien.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"Trùng tài khoản!Hãy thử lại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}