package com.example.login_android.domain

import com.example.login_android.aplication.dataApi

class User {
    var dat = dataApi()
    private lateinit var username : String
    private lateinit var password : String
    private lateinit var confirmpassword : String
    private lateinit var email : String

    fun setRegistro(us : String, pass : String, confpass : String, em : String){
        setUsername(us)
        setPassword(pass)
        setConfirmpassword(confpass)
        setEmail(em)
        regUser()
    }

    fun regUser(){
        val nameup = getUsername()
        val paswordup = getPassword()
        val confirmup = getConfirmpassword()
        val emailup = getEmail()
        dat.requestRegistrar(nameup,paswordup,confirmup,emailup)
    }

    fun setLogin(us : String, pass : String){
        setUsername(us)
        setPassword(pass)
        logUser()
    }

    fun logUser(){
        val namein = getUsername()
        val pasword = getPassword()
        dat.requestLoguear(namein,pasword)
    }

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



}