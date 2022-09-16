package com.example.login_android.infraestructure.modelData

class UserRequest {

    private lateinit var username : String
    private lateinit var password : String
    private lateinit var confirmpassword : String
    private lateinit var email : String
    private lateinit var token : String

    fun getUsername():String{
        return username
    }

    fun setUsername(us : String){
        this.username = us
    }

    fun getPassword(): String{
        return password
    }

    fun setPassword(pass : String){
        this.password = pass
    }

    fun getConfirmpassword() : String{
        return confirmpassword
    }

    fun setConfirmpassword(conpass : String){
        this.confirmpassword = conpass
    }

    fun getEmail(): String{
        return email
    }

    fun setEmail(em : String){
        this.email = em
    }

    fun getToken(): String{
        return token
    }

    fun setToken(tok : String){
        this.token = tok
    }
}