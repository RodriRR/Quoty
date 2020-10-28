package com.represa.quoty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.ui.platform.setContent
import com.represa.quoty.data.database.QDatabase
import com.represa.quoty.ui.screen.Main
import com.represa.quoty.ui.theme.QuotyTheme
import com.represa.quoty.ui.viewmodel.MainViewModel
import com.represa.quoty.util.navigation.bottomnav.MultiBottomNavApp
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()
    private val bd : QDatabase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screen = "Home"

        /*setContent {
            QuotyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    when(screen){
                        "Home" -> Main(viewModel)
                    }
                }
            }
        }*/

        setContent {
            MultiBottomNavApp()
        }
    }

    override fun onDestroy() {
        bd.close()
        super.onDestroy()
    }
}

