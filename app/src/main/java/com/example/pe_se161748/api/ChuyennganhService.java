package com.example.pe_se161748.api;

import com.example.pe_se161748.model.Chuyennganh;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChuyennganhService {
    String CHUYENNGANH = "Chuyennganh";
    @GET(CHUYENNGANH)
    Call<List<Chuyennganh>> getAllCNs();
    @GET(CHUYENNGANH + "/{id}")
    Call<Chuyennganh> getAllCNs(@Path("id") Object id);
    @POST(CHUYENNGANH)
    Call<Chuyennganh> createCN(@Body Chuyennganh cn);
    @PUT(CHUYENNGANH + "/{id}")
    Call<Chuyennganh> updateCN(@Path("id")Object id, @Body Chuyennganh cn);
    @DELETE(CHUYENNGANH + "/{id}")
    Call<Chuyennganh> deleteCN(@Path("id")Object id);
}
