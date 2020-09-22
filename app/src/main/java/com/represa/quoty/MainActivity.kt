package com.represa.quoty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.ui.platform.setContent
import com.represa.quoty.ui.screen.Main
import com.represa.quoty.ui.theme.QuotyTheme
import com.represa.quoty.ui.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screen = "Home"

        setContent {
            QuotyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    when(screen){
                        "Home" -> Main(viewModel)
                    }
                }
            }
        }
    }
}

