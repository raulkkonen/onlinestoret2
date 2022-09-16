package com.example.login_android.presentation


import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.login_android.infraestructure.modelData.UserRequest
import com.example.login_android.infraestructure.modelData.UserResponse
import com.example.login_android.infraestructure.service.ApiClient
import com.example.login_android.ui.LogIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Adapter {

    fun saveUser(userRegRequest: UserRequest?) {
        var msjSave = ""
        val userResponseCall = ApiClient.userService.saveUser(userRegRequest)
        userResponseCall.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    msjSave = "Registro exitoso"
                    Log.i(TAG,msjSave)

                } else {
                    msjSave = "Error en el Registro"
                    Log.i(TAG,msjSave)
                }
            }
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                msjSave = "Request failed ${t.localizedMessage}"
                Log.i(TAG,msjSave)
            }
        })
    }

    fun loginUser(userLogRequest: UserRequest?) {
        var msjLog = ""
        val userResponseCall = ApiClient.userService.loginUser(userLogRequest)
        userResponseCall.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    msjLog = "BIENVENIDO ${userLogRequest?.getUsername()}"
                    Log.i(TAG,msjLog)
                } else {
                    msjLog = "Usuario o contraseña incorrecto"
                    Log.i(TAG,msjLog)
                }
            }
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                msjLog = "Request failed ${t.localizedMessage}"
                Log.i(TAG,msjLog)
            }
        })
    }




}