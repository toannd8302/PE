package com.example.pe_se161748.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.pe_se161748.R;
import com.example.pe_se161748.api.ChuyennganhRepository;
import com.example.pe_se161748.api.ChuyennganhService;
import com.example.pe_se161748.model.Chuyennganh;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChuyennganhDetailActivity extends AppCompatActivity {
    Button btnUpdate, btnDelete;
    ChuyennganhService chuyennganhService;
    Chuyennganh chuyennganh;
    EditText etcnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyennganhdetail);

        // Get the selected trainee's details from the intent
        chuyennganh = getIntent().getParcelableExtra("cn");

        // Display the trainee's details in the layout
        etcnName = findViewById(R.id.etcnName);
        chuyennganhService = ChuyennganhRepository.getChuyennganhService();
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        etcnName.setText(chuyennganh.getCnName());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCN();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCN();
            }
        });
    }

    private void updateCN() {
        // Get the updated values from the EditText fields
        String updatedName = etcnName.getText().toString();

        // Update the Trainee object with the new values
        chuyennganh.setCnName(updatedName);


        // Call the updateTrainees method from your TraineeService
        Call<Chuyennganh> call = chuyennganhService.updateCN(chuyennganh.getId(), chuyennganh);

        call.enqueue(new Callback<Chuyennganh>() {
            @Override
            public void onResponse(Call<Chuyennganh> call, Response<Chuyennganh> response) {
                if (response.isSuccessful()) {
                    // Update was successful
                    Toast.makeText(ChuyennganhDetailActivity.this, "Chuyen nganh updated successfully", Toast.LENGTH_LONG).show();
                    // Notify the TraineeListActivity to refresh the list
                    sendBroadcastToRefreshList();
                    // Optionally, navigate back to the previous screen
                    finish();
                } else {
                    // Update failed
                    Toast.makeText(ChuyennganhDetailActivity.this, "Update failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Chuyennganh> call, Throwable t) {
                // Network or API call failure
                Toast.makeText(ChuyennganhDetailActivity.this, "Update failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void deleteCN() {
        Call<Chuyennganh> call = chuyennganhService.deleteCN(chuyennganh.getId());

        call.enqueue(new Callback<Chuyennganh>() {
            @Override
            public void onResponse(Call<Chuyennganh> call, Response<Chuyennganh> response) {
                if (response.isSuccessful()) {
                    // Deletion was successful
                    Toast.makeText(ChuyennganhDetailActivity.this, "Chuyen nganh deleted successfully", Toast.LENGTH_LONG).show();
                    // Notify the TraineeListActivity to refresh the list
                    sendBroadcastToRefreshList();
                    finish();
                } else {
                    // Deletion failed
                    Toast.makeText(ChuyennganhDetailActivity.this, "Deletion failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Chuyennganh> call, Throwable t) {
                // Network or API call failure
                Toast.makeText(ChuyennganhDetailActivity.this, "Deletion failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendBroadcastToRefreshList() {
        Intent intent = new Intent("cn_updated");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
