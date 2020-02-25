package com.galhope.toastmap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NetworkApi {
    @Headers("Content-Type: application/json")
    @POST("maps/v3.0/appkeys/eNk4hnsnzctcFy8T/route-normal")
    Call<ResModel> getNavigation(@Body ReqModel reqModel);
}
