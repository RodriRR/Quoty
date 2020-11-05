package com.represa.quoty

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.Observer
import com.represa.quoty.data.model.network.quote.LoginStatus
import com.represa.quoty.ui.screen.Login
import com.represa.quoty.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val mainViewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startObservers()

        setContent {
            Login()
        }
    }

    private fun startObservers(){
        mainViewModel.login.observe(this, Observer {
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

