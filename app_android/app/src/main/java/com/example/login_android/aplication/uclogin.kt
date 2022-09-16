package com.example.login_android.aplication

import com.example.login_android.domain.User

class uclogin {
    var user = User()

    fun registrar( us : String, pass:String, confpass : String, ema : String){
        user.setRegistro(us,pass,confpass,ema)
    }

    fun loguear( us : String, pass:String){
        user.setLogin(us,pass)
    }
}