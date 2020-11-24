package com.represa.quoty.data.database

import androidx.room.*
import com.represa.quoty.data.model.database.QuoteDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Defines methods for using the ToDoDatabase class with Room.
 */
@Dao
interface QDatabaseDao {

    @Query("SELECT * FROM quotes WHERE id = :id")
    suspend fun getQuote(id : Int): QuoteDatabase

    @Query("SELECT * FROM quotes WHERE body LIKE '%' || :search || '%' ")
    fun getQuoteFiltered(search : String): Flow<List<QuoteDatabase>>

    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): Flow<List<QuoteDatabase>>

    @Query("SELECT * FROM quotes WHERE favourite = 1 ")
    fun getFavouritesQuotes(): List<QuoteDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuote(quote: QuoteDatabase)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertListOfQuotes(quote: List<QuoteDatabase>)

}