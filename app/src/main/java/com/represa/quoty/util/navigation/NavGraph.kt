package com.represa.quoty.util.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.represa.quoty.util.navigation.bottomnav.MultiBottomNavApp

class NavGraph : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiBottomNavApp()
        }
    }
}