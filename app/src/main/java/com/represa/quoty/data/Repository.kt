package com.represa.quoty.data

import com.represa.quoty.data.database.QDatabase
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.data.model.network.LoginStatus
import com.represa.quoty.data.model.network.QuoteNetwork
import com.represa.quoty.data.model.network.User
import com.represa.quoty.data.model.network.UserParameters
import com.represa.quoty.data.network.FavQsApi
import kotlinx.coroutines.flow.Flow

class Repository(private val QDatabase : QDatabase) {

    private val token = "Token token=\"4661b9eaf0ecc8c6df21036b10531423\""

    suspend fun login(user : String, password : String) : LoginStatus {
        val userCredentials = User(UserParameters(user, password))
        try {
            var response = FavQsApi.RETROFIT_SERVICE.createSession(token, userCredentials)
            return LoginStatus.LOGIN_SUCCESS
        }catch (e : Exception){
            return LoginStatus.LOGIN_ERROR
        }
    }

    suspend fun fetchQuotes() : List<QuoteNetwork> {
        return FavQsApi.RETROFIT_SERVICE.getQuotes(token, "god").quotes
    }

    fun insert(quote : QuoteDatabase){
        QDatabase.quoteDao().insertNewTask(quote)
    }

    fun prueba() : Flow<List<QuoteDatabase>>{
        return QDatabase.quoteDao().getCategories()
    }

    fun clear(){
        QDatabase.clearAllTables()
    }

    /*fun getQuotes() : Flow<List<Quote>>{
        //QuoteDatabase.getDatabase().quoteDao().getCategories()
    }*/
}