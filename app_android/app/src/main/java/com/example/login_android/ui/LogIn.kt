package com.example.login_android.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.example.login_android.R
import com.example.login_android.presentation.viewModel


open class LogIn : AppCompatActivity() {

    private lateinit var txtuserin: EditText
    private lateinit var txtpasswordin : EditText
    private lateinit var txtmensajein : TextView
    private var validatelogin = AwesomeValidation(ValidationStyle.BASIC)
    private var msj = ""
    private var viewModel = viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        txtuserin = findViewById(R.id.txtuserin)
        txtpasswordin = findViewById(R.id.txtpasswordin)
        txtmensajein =  findViewById(R.id.txtmensajein)

        //validar datos del formulario y agregarlos a la clase
        validatelogin.addValidation(this@LogIn, R.id.txtuserin, RegexTemplate.NOT_EMPTY,R.string.invalid_name)
        validatelogin.addValidation(this@LogIn, R.id.txtpasswordin, ".{6,8}", R.string.invalid_password)
    }

    fun clicBtnLogear(view: View) {
        if (validatelogin.validate()){
            viewModel.vmlogin(txtuserin,txtpasswordin)
        }else{
            Toast.makeText(this@LogIn, "Ingrese los datos requeridos", Toast.LENGTH_SHORT).show()
        }

    }

    fun pantallaSignup(view: View) {
        val siguiente = Intent(this, SignUp::class.java)
        startActivity(siguiente)
    }

    fun mostrarMensaje(men : String){
        Toast.makeText(this@LogIn, men, Toast.LENGTH_SHORT).show()
    }



}