package com.represa.quoty.data

import com.represa.quoty.data.model.LoginStatus
import com.represa.quoty.data.model.User
import com.represa.quoty.data.model.UserParameters
import com.represa.quoty.data.network.FavQsApi

object Repository {

    private val token = "Token token=\"4661b9eaf0ecc8c6df21036b10531423\""

    suspend fun login(user : String, password : String) : LoginStatus{
        val userCredentials = User(UserParameters(user, password))
        try {
            var response = FavQsApi.RETROFIT_SERVICE.createSession(token, userCredentials)
            return LoginStatus.LOGIN_SUCCESS
        }catch (e : Exception){
            return LoginStatus.LOGIN_ERROR
        }

    }
}