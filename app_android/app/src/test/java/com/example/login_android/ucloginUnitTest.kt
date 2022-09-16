package com.example.login_android

import android.widget.EditText
import com.example.login_android.aplication.uclogin
import com.example.login_android.domain.User
import com.example.login_android.presentation.viewModel
import org.junit.Assert
import org.junit.Test

class ucloginUnitTest {

    @Test
    fun `when string to data input`(){
        val uc = uclogin()
        val user = User()
        val us = "edumar111"
        val pass = "123456"

        user.setUsername("edumar1111")
        user.setPassword("123456")

        val us1 = user.getUsername()
        val us2 = user.getPassword()


        Assert.assertEquals(user.setLogin(us1,us2),uc.loguear(us,pass))

    }
}