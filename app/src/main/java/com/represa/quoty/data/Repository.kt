package com.represa.quoty.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.represa.quoty.data.model.LoginStatus
import com.represa.quoty.data.model.User
import com.represa.quoty.data.model.UserParameters
import com.represa.quoty.data.network.ApiService
import com.represa.quoty.data.network.FavQsApi

object Repository {

    private val token = "Token token=\"7323d928a0f5b7bd5f93df432a7d2947\""

    private var _login = MutableLiveData(LoginStatus.NOT_LOGGED)
    val login: LiveData<LoginStatus> = _login



    suspend fun login(user : String, password : String){
        val user = User(UserParameters(user,password))
        _login.value = LoginStatus.LOGIN_SUCCESS
    }
}