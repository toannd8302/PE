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
import com.example.pe_se161748.api.SinhvienRepository;
import com.example.pe_se161748.api.SinhvienService;
import com.example.pe_se161748.model.Sinhvien;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SinhvienDetailActivity extends AppCompatActivity {
    Button btnUpdate, btnDelete;
    SinhvienService sinhvienService;
    Sinhvien sinhvien;
    EditText etStudentName, etStudentDate, etStudentGender, etStudentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdetail);

        // Get the selected trainee's details from the intent
        sinhvien = getIntent().getParcelableExtra("sinhvien");

        // Display the trainee's details in the layout
        etStudentName = findViewById(R.id.etStudentName);
        etStudentDate = findViewById(R.id.etStudentDate);
        etStudentGender = findViewById(R.id.etStudentGender);
        etStudentAddress = findViewById(R.id.etStudentAddress);
        sinhvienService = SinhvienRepository.getStudentService();
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        etStudentName.setText(sinhvien.getName());
        etStudentDate.setText((CharSequence) sinhvien.getDob());
        etStudentGender.setText(sinhvien.getGender());
        etStudentAddress.setText(sinhvien.getAddress());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });
    }

    private void updateStudent() {
        // Get the updated values from the EditText fields
        String updatedName = etStudentName.getText().toString();
        String updatedDOB = etStudentDate.getText().toString();
        String updatedGender = etStudentGender.getText().toString();
        String updatedAddress = etStudentAddress.getText().toString();

        // Update the Trainee object with the new values
        sinhvien.setName(updatedName);
        sinhvien.setDob(updatedDOB);
        sinhvien.setGender(updatedGender);
        sinhvien.setAddress(updatedAddress);

        // Call the updateTrainees method from your TraineeService
        Call<Sinhvien> call = sinhvienService.updateStudents(sinhvien.getId(), sinhvien);

        call.enqueue(new Callback<Sinhvien>() {
            @Override
            public void onResponse(Call<Sinhvien> call, Response<Sinhvien> response) {
                if (response.isSuccessful()) {
                    // Update was successful
                    Toast.makeText(SinhvienDetailActivity.this, "Student updated successfully", Toast.LENGTH_LONG).show();
                    // Notify the TraineeListActivity to refresh the list
                    sendBroadcastToRefreshList();
                    // Optionally, navigate back to the previous screen
                    finish();
                } else {
                    // Update failed
                    Toast.makeText(SinhvienDetailActivity.this, "Update failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Sinhvien> call, Throwable t) {
                // Network or API call failure
                Toast.makeText(SinhvienDetailActivity.this, "Update failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void deleteStudent() {
        Call<Sinhvien> call = sinhvienService.deleteStudents(sinhvien.getId());

        call.enqueue(new Callback<Sinhvien>() {
            @Override
            public void onResponse(Call<Sinhvien> call, Response<Sinhvien> response) {
                if (response.isSuccessful()) {
                    // Deletion was successful
                    Toast.makeText(SinhvienDetailActivity.this, "Student deleted successfully", Toast.LENGTH_LONG).show();
                    // Notify the TraineeListActivity to refresh the list
                    sendBroadcastToRefreshList();
                    finish();
                } else {
                    // Deletion failed
                    Toast.makeText(SinhvienDetailActivity.this, "Deletion failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Sinhvien> call, Throwable t) {
                // Network or API call failure
                Toast.makeText(SinhvienDetailActivity.this, "Deletion failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendBroadcastToRefreshList() {
        Intent intent = new Intent("sinhvien_updated");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
