package com.example.pe_se161748.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pe_se161748.R;
import com.example.pe_se161748.api.SinhvienRepository;
import com.example.pe_se161748.api.SinhvienService;
import com.example.pe_se161748.model.Sinhvien;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    SinhvienService sinhvienService;
    EditText etStudentName, etStudentDate, etStudentGender, etStudentAddress;
    Button btnSave, btnShowList, btnCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        btnSave.setOnClickListener(this);
        btnShowList.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
        sinhvienService = SinhvienRepository.getStudentService();

    }

    public void initview(){
        etStudentName = (EditText) findViewById(R.id.etStudentName);
        etStudentDate = (EditText) findViewById(R.id.etStudentDate);
        etStudentGender = (EditText) findViewById(R.id.etStudentGender);
        etStudentAddress = (EditText) findViewById(R.id.etStudentAddress);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShowList = (Button) findViewById(R.id.btnShowList);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
    }
    public void save(){
        String name = etStudentName.getText().toString();
        String date = etStudentDate.getText().toString();
        String gender = etStudentGender.getText().toString();
        String address = etStudentAddress.getText().toString();

        Sinhvien sinhvien = new Sinhvien(name, date, gender, address);
        try{
            Call<Sinhvien> call = sinhvienService.createStudents(sinhvien);
            call.enqueue(new Callback<Sinhvien>() {
                @Override
                public void onResponse(Call<Sinhvien> call, Response<Sinhvien> response) {
                    if(response.body() !=null){
                        Toast.makeText(MainActivity.this, "Save successfully",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Sinhvien> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save fail", Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSave){
            save();
        }
        if(v.getId()==R.id.btnShowList){
            Intent intent = new Intent(this, SinhvienListActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.btnCreateAccount){
            System.out.println("Hello");
            Intent intent = new Intent(this, CreateAccountSinhvien.class);
            startActivity(intent);
        }
    }
}