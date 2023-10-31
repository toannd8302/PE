package com.example.pe_se161748.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.pe_se161748.R;
import com.example.pe_se161748.adapter.ListChuyenNganhAdapter;
import com.example.pe_se161748.api.ChuyennganhRepository;
import com.example.pe_se161748.api.ChuyennganhService;
import com.example.pe_se161748.model.Chuyennganh;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChuyennganhListActivity extends AppCompatActivity {
    ArrayList<Chuyennganh> listcn;
    ChuyennganhService cnService;
    ListView lvcn;
    Button btnAdd, btnShowList;
    ListChuyenNganhAdapter listChuyenNganhAdapter;
    private BroadcastReceiver updateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyennganhlist);
        listcn = new ArrayList<>();
        cnService = ChuyennganhRepository.getChuyennganhService();
        lvcn = findViewById(R.id.lvcn);
        btnAdd = findViewById(R.id.btnAdd);
        btnShowList = findViewById(R.id.btnShowList);
        listChuyenNganhAdapter = new ListChuyenNganhAdapter(listcn);

        lvcn.setAdapter(listChuyenNganhAdapter);

        // Call the API to get all trainees
        getAllcns();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChuyennganhListActivity.this, AddChuyennganhActivity.class);
                startActivity(intent);
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChuyennganhListActivity.this, SinhvienListActivity.class);
                startActivity(intent);
            }
        });
        // Set a click listener for each item in the list
        lvcn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chuyennganh selectedcn = listcn.get(position);

                // Start TraineeDetailActivity and pass the selected trainee's details
                Intent intent = new Intent(ChuyennganhListActivity.this, ChuyennganhDetailActivity.class);
                intent.putExtra("cn", selectedcn);
                startActivity(intent);
            }
        });
        // Inside onCreate or onResume
        updateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if ("cn_updated".equals(intent.getAction())) {
                    // Refresh your trainee list here
                    // Call the method to retrieve the updated list or update the existing list
                    refreshTraineeList();
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(updateReceiver, new IntentFilter("cn_updated"));
    }
    private void getAllcns() {
        ChuyennganhService traineeService = ChuyennganhRepository.getChuyennganhService();

        Call<List<Chuyennganh>> call = traineeService.getAllCNs();
        call.enqueue(new Callback<List<Chuyennganh>>() {
            @Override
            public void onResponse(Call<List<Chuyennganh>> call, Response<List<Chuyennganh>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listcn.clear();
                    listcn.addAll(response.body());
                    listChuyenNganhAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Chuyennganh>> call, Throwable t) {
                Log.e("ChuyennganhListActivity", "Failed to retrieve chuyen nganh", t);
            }
        });
    }
    private void refreshTraineeList() {
        getAllcns();
    }

}