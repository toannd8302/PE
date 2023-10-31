package com.example.pe_se161748.api;

public class ChuyennganhRepository {
    public static ChuyennganhService getChuyennganhService(){
        return APIClient.getClient().create(ChuyennganhService.class);
    }
}
