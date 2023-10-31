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
import com.example.pe_se161748.adapter.ListStudentAdapter;
import com.example.pe_se161748.api.SinhvienRepository;
import com.example.pe_se161748.api.SinhvienService;
import com.example.pe_se161748.model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SinhvienListActivity extends AppCompatActivity {
    ArrayList<Sinhvien> listStudent;
    SinhvienService sinhvienService;
    ListView lvStudent;
    Button btnAdd, btnShowList;
    ListStudentAdapter listStudentAdapter;
    private BroadcastReceiver updateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);
        listStudent = new ArrayList<>();
        sinhvienService = SinhvienRepository.getStudentService();
        lvStudent = findViewById(R.id.lvStudent);
        btnAdd = findViewById(R.id.btnAdd);
        btnShowList = findViewById(R.id.btnShowList);
        listStudentAdapter = new ListStudentAdapter(listStudent);

        lvStudent.setAdapter(listStudentAdapter);

        // Call the API to get all trainees
        getAllTrainees();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinhvienListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinhvienListActivity.this, ChuyennganhListActivity.class);
                startActivity(intent);
            }
        });
        // Set a click listener for each item in the list
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sinhvien selectedTrainee = listStudent.get(position);

                // Start TraineeDetailActivity and pass the selected trainee's details
                Intent intent = new Intent(SinhvienListActivity.this, SinhvienDetailActivity.class);
                intent.putExtra("sinhvien", selectedTrainee);
                startActivity(intent);
            }
        });
        // Inside onCreate or onResume
        updateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if ("sinhvien_updated".equals(intent.getAction())) {
                    // Refresh your trainee list here
                    // Call the method to retrieve the updated list or update the existing list
                    refreshTraineeList();
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(updateReceiver, new IntentFilter("sinhvien_updated"));
    }
    private void getAllTrainees() {
        SinhvienService traineeService = SinhvienRepository.getStudentService();

        Call<List<Sinhvien>> call = traineeService.getAllTrainees();
        call.enqueue(new Callback<List<Sinhvien>>() {
            @Override
            public void onResponse(Call<List<Sinhvien>> call, Response<List<Sinhvien>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listStudent.clear();
                    listStudent.addAll(response.body());
                    listStudentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Sinhvien>> call, Throwable t) {
                Log.e("SinhvienListActivity", "Failed to retrieve students", t);
            }
        });
    }
    private void refreshTraineeList() {
        getAllTrainees();
    }

}