package com.example.login_android.presentation

import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.login_android.aplication.uclogin

class viewModel {
    var useslogin = uclogin()


    fun vmregistro(txtuser : EditText, txtpass : EditText, txtconfpass : EditText, txtem : EditText){
        val user = aString(txtuser)
        val pass = aString(txtpass)
        val confpass = aString(txtconfpass)
        val email = aString(txtem)
        useslogin.registrar(user,pass,confpass,email)
    }

    fun vmlogin(txtuser : EditText, txtpass : EditText,){
        val user = aString(txtuser)
        val pass = aString(txtpass)
        useslogin.loguear(user,pass)
    }



    fun aString(dato : EditText):String{
        return dato.text.toString()
    }

}