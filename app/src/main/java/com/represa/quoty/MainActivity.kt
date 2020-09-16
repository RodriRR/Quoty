package com.represa.quoty

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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val screen = "Home"

        /*Repository.login.observe(this, Observer {
            when(it){
                LoginStatus.LOGIN_SUCCESS -> Toast.makeText(this, "SUCESS", Toast.LENGTH_SHORT).show()
                LoginStatus.LOGIN_ERROR -> Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
            }
        })*/

        setContent {
            QuotyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    when(screen){
                        "Home" -> Main()
                    }
                }
            }
        }
    }
}

