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
import com.example.pe_se161748.api.ChuyennganhRepository;
import com.example.pe_se161748.api.ChuyennganhService;
import com.example.pe_se161748.model.Chuyennganh;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChuyennganhActivity extends AppCompatActivity implements View.OnClickListener{
    ChuyennganhService chuyennganhService;
    EditText etcnName;
    Button btnSave, btnShowList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcn);
        initview();
        btnSave.setOnClickListener(this);
        btnShowList.setOnClickListener(this);
        chuyennganhService = ChuyennganhRepository.getChuyennganhService();

    }

    public void initview(){
        etcnName = (EditText) findViewById(R.id.etcnName);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShowList = (Button) findViewById(R.id.btnShowList);
    }
    public void save(){
        String name = etcnName.getText().toString();

        Chuyennganh chuyennganh = new Chuyennganh(name);
        try{
            Call<Chuyennganh> call = chuyennganhService.createCN(chuyennganh);
            call.enqueue(new Callback<Chuyennganh>() {
                @Override
                public void onResponse(Call<Chuyennganh> call, Response<Chuyennganh> response) {
                    if(response.body() !=null){
                        Toast.makeText(AddChuyennganhActivity.this, "Save successfully",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Chuyennganh> call, Throwable t) {
                    Toast.makeText(AddChuyennganhActivity.this, "Save fail", Toast.LENGTH_LONG).show();
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
            Intent intent = new Intent(this, ChuyennganhListActivity.class);
            startActivity(intent);
        }
    }
}