package com.example.pe_se161748.api;

public class SinhvienRepository {
    public static SinhvienService getStudentService(){
        return APIClient.getClient().create(SinhvienService.class);
    }
}
