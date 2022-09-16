package com.example.login_android.infraestructure.service

import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit: Retrofit
        private get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient : OkHttpClient = OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build()


            return Retrofit.Builder()
                .baseUrl("http://192.168.0.10:8095/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    val userService: UserService
        get() = retrofit.create(UserService::class.java)
}