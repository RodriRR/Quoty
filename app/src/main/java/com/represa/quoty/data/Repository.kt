package com.represa.quoty.data

import com.represa.quoty.data.database.QDatabase
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.data.model.network.images.Image
import com.represa.quoty.data.model.network.quote.LoginStatus
import com.represa.quoty.data.model.network.quote.QuoteNetwork
import com.represa.quoty.data.model.network.quote.User
import com.represa.quoty.data.model.network.quote.UserParameters
import com.represa.quoty.data.network.QuoteApi
import com.represa.quoty.data.network.ImageApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(private val QDatabase: QDatabase) {

    private val token = "Token token=\"4661b9eaf0ecc8c6df21036b10531423\""

    private val tokenImage = "Client-ID ybXJuGl66vW0oUXIzsuK1JY7DLJ-Im9Wk0cjnX_nOak"

    suspend fun login(user: String, password: String): LoginStatus {
        val userCredentials = User(UserParameters(user, password))
        return try {
            var response = QuoteApi.QUOTE_SERVICE.createSession(token, userCredentials)
            LoginStatus.LOGIN_SUCCESS
        } catch (e: Exception) {
            LoginStatus.LOGIN_ERROR
        }
    }

    suspend fun fetchQuotes(search: String): List<QuoteNetwork> {
        return QuoteApi.QUOTE_SERVICE.getQuotes(token, search).quotes
    }

    fun searchQuotes(search: String): Flow<List<QuoteDatabase>> {
        var s = search
        if (search.isEmpty()) {
            return flow { emit(emptyList<QuoteDatabase>()) }
        } else {
            return QDatabase.quoteDao().getPrueba(s)
        }
    }

    fun insert(quote: QuoteDatabase) {
        QDatabase.quoteDao().insertQuote(quote)
    }

    fun insertListOfQuotes(quotes: List<QuoteDatabase>) {
        QDatabase.quoteDao().insertListOfQuotes(quotes)
    }

    fun clear() {
        QDatabase.clearAllTables()
    }

    suspend fun getRandomImages(query: String, quantity: Int): List<Image> {
        return ImageApi.IMAGE_SERVICE.getRandomPhoto(tokenImage, query, quantity)
    }
}