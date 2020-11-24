package com.represa.quoty.data

import com.represa.quoty.data.database.QDatabase
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.data.model.network.images.Image
import com.represa.quoty.data.model.network.quote.*
import com.represa.quoty.data.network.QuoteApi
import com.represa.quoty.data.network.ImageApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(private val QDatabase: QDatabase) {

    private val token = "Token token=\"4661b9eaf0ecc8c6df21036b10531423\""

    private val tokenImage = "Client-ID ybXJuGl66vW0oUXIzsuK1JY7DLJ-Im9Wk0cjnX_nOak"

    private lateinit var sessionToken : Token

    suspend fun login(user: String, password: String): LoginStatus {
        val userCredentials = User(UserParameters(user, password))
        return try {
            sessionToken = QuoteApi.QUOTE_SERVICE.createSession(token, userCredentials)
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
            return QDatabase.quoteDao().getQuoteFiltered(s)
        }
    }

    fun insert(quote: QuoteDatabase) {
        QDatabase.quoteDao().insertQuote(quote)
    }

    fun insertListOfQuotes(quotes: List<QuoteDatabase>) {
        QDatabase.quoteDao().insertListOfQuotes(quotes)
    }

    suspend fun getQuote(id: Int) : QuoteDatabase {
        return QDatabase.quoteDao().getQuote(id)
    }

    suspend fun fetchFavouritesQuote() : List<QuoteNetwork>{
        val quotes = QuoteApi.QUOTE_SERVICE.getFavouriteQuotes(token,"rodrire","user")
        return quotes.quotes
    }

    fun getFavouritesQuote() : List<QuoteDatabase> {
        return QDatabase.quoteDao().getFavouritesQuotes()
    }

    suspend fun createNewQuote(quote : NewQuote) : QuoteNetwork{
        return QuoteApi.QUOTE_SERVICE.createQuote(token, sessionToken.userToken, quote)
    }

    suspend fun favQuote(id : Int){
        QuoteApi.QUOTE_SERVICE.favQuote(token, sessionToken.userToken, id)
    }

    suspend fun unFavQuote(id : Int){
        QuoteApi.QUOTE_SERVICE.unFavQuote(token, sessionToken.userToken, id)
    }

    fun clear() {
        QDatabase.clearAllTables()
    }

    suspend fun getRandomImages(query: String, quantity: Int): List<Image> {
        return ImageApi.IMAGE_SERVICE.getRandomPhoto(tokenImage, query, quantity)
    }
}