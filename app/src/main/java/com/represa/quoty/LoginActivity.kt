package com.represa.quoty

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.Observer
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.LoginStatus
import com.represa.quoty.ui.screen.Home
import com.represa.quoty.ui.screen.Login
import com.represa.quoty.ui.screen.Main
import com.represa.quoty.ui.theme.QuotyTheme
import com.represa.quoty.ui.viewmodel.LoginViewModel
import androidx.activity.viewModels

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screen = "Login"

        startObservers()

        setContent {
            QuotyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    when(screen){
                        "Login" -> Login(viewModel)
                        "Home" -> Main()
                    }
                }
            }
        }
    }

    fun startObservers(){
        viewModel.login.observe(this, Observer {
            when (it) {
                //LoginStatus.LOGIN_LOAD -> setContent { CircularProgressIndicator() }
                LoginStatus.LOGIN_SUCCESS -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                LoginStatus.LOGIN_ERROR -> Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

