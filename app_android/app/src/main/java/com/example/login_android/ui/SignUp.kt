package com.example.login_android.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.example.login_android.R
import com.example.login_android.presentation.viewModel

class SignUp : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var email: EditText
    private var viewModel = viewModel()
    private var msj = ""
    private var validatesignup = AwesomeValidation(ValidationStyle.BASIC)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        username = findViewById(R.id.txtuserup)
        password = findViewById(R.id.txtpasswordup)
        confirmPassword = findViewById(R.id.txtconfirmpasswordup)
        email = findViewById(R.id.txtemailup)

        //validar datos del formulario y agregarlos a la clase
        validatesignup.addValidation(this@SignUp, R.id.txtuserup,RegexTemplate.NOT_EMPTY,R.string.invalid_name)
        validatesignup.addValidation(this@SignUp, R.id.txtpasswordup, ".{6,8}", R.string.invalid_password)
        validatesignup.addValidation(this@SignUp, R.id.txtconfirmpasswordup, R.id.txtpasswordup, R.string.invalid_confirm_password)
        validatesignup.addValidation(this@SignUp, R.id.txtemailup, Patterns.EMAIL_ADDRESS, R.string.invalid_email)
    }

    fun clicBtnRegistrar(view: View){

        if (validatesignup.validate()){
            viewModel.vmregistro(username,password,confirmPassword,email)
            pantallaLogin(view)
        }else{
            Toast.makeText(this@SignUp, "Ingrese los datos requeridos", Toast.LENGTH_SHORT).show()
        }
    }

    fun pantallaLogin(view: View) {
            val inicio = Intent(this, LogIn::class.java)
            startActivity(inicio)
    }


}