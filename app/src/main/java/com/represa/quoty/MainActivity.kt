package com.represa.quoty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.ui.platform.setContent
import com.represa.quoty.ui.screen.Login
import com.represa.quoty.ui.theme.QuotyTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val screen = "Login"

        setContent {
            QuotyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    when(screen){
                        "Login" -> Login()
                    }
                }
            }
        }
    }
}

