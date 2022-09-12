package com.example.localkamcom.API;

import com.example.localkamcom.Hire_People.hir_model;
import com.example.localkamcom.SignUp.model_login;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface api {
    @FormUrlEncoded
    @POST("register.php")
    Call<model_login> adddata(@Field("mobile")String mobile,
                              @Field("name")String name);
    @FormUrlEncoded
    @POST("hirdata.php")
    Call<hir_model> hirData(@Field("name")String name,
                            @Field("phone")String phone,
                            @Field("age")String age,
                            @Field("amount")String amount,
                            @Field("expertise")String expertise,
                            @Field("address")String address);

    @GET("get_job.php")
    Call<List<hir_model>> getdata();

//    @Multipart
//    @POST("upload")
//    Call<RequestBody> uploadImage(@Part MultipartBody.Part part, @Part("somedata")RequestBody requestBody);

}
