package com.example.pe_se161748.api;

import com.example.pe_se161748.model.Sinhvien;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SinhvienService {
    String STUDENTS = "Sinhvien";
    @GET(STUDENTS)
    Call<List<Sinhvien>> getAllTrainees();
    @GET(STUDENTS + "/{id}")
    Call<Sinhvien> getAllStudents(@Path("id") Object id);
    @POST(STUDENTS)
    Call<Sinhvien> createStudents(@Body Sinhvien sinhvien);
    @PUT(STUDENTS + "/{id}")
    Call<Sinhvien> updateStudents(@Path("id")Object id, @Body Sinhvien sinhvien);
    @DELETE(STUDENTS + "/{id}")
    Call<Sinhvien> deleteStudents(@Path("id")Object id);
}
