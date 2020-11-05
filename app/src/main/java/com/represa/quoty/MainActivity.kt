package com.represa.quoty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.represa.quoty.data.database.QDatabase
import com.represa.quoty.ui.MultiBottomNavApp
import com.represa.quoty.ui.theme.QuotyTheme
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val bd : QDatabase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuotyTheme() {
                MultiBottomNavApp()
            }
        }
    }

    override fun onDestroy() {
        bd.close()
        super.onDestroy()
    }
}

