package com.example.asterisk.maps;

import com.example.asterisk.maps.Remote.IGoogleAPIService;
import com.example.asterisk.maps.Remote.RetrofitClient;

import retrofit2.Retrofit;

public class Common {
    private static final String GOOGLE_API_URL = "https://maps.googleapis.com/";
    public static IGoogleAPIService getGoogleAPIService() {
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }
}
