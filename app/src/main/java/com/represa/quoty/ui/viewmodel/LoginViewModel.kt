package com.represa.quoty.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.network.quote.LoginStatus
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginViewModel(private val repository : Repository) : ViewModel() {

    var prueba by mutableStateOf(LoginStatus.NOT_LOGGED)
        private set

    private var _login = MutableLiveData(LoginStatus.NOT_LOGGED)
    val login: LiveData<LoginStatus> = _login

    fun login(user : String, password : String){
        prueba = LoginStatus.LOGIN_LOAD
        viewModelScope.launch {
            var response = repository.login(user,password)
            prueba = response
            _login.value = response
        }
    }
}