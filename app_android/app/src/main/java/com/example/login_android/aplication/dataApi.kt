package com.example.login_android.aplication

import com.example.login_android.presentation.Adapter
import com.example.login_android.infraestructure.modelData.UserRequest

class dataApi {
    var adap = Adapter()

    fun requestRegistrar(us : String, pass : String, conPass : String, em : String){
        val userRegRequest = UserRequest()
        userRegRequest.setUsername(us)
        userRegRequest.setPassword(pass)
        userRegRequest.setConfirmpassword(conPass)
        userRegRequest.setEmail(em)
        adap.saveUser(userRegRequest)
    }

    fun requestLoguear(us : String, pass : String){
        val userRegRequest = UserRequest()
        userRegRequest.setUsername(us)
        userRegRequest.setPassword(pass)
        adap.loginUser(userRegRequest)
    }

}