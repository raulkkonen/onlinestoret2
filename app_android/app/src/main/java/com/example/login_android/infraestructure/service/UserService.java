package com.example.login_android.infraestructure.service;


import com.example.login_android.infraestructure.modelData.UserRequest;
import com.example.login_android.infraestructure.modelData.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {


@POST("security/users")
Call<UserResponse> saveUser(@Body UserRequest userRequest);

@POST("security/login")
Call<UserResponse> loginUser(@Body UserRequest userRequest);
}
