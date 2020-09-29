package com.represa.quoty.data.database

import androidx.room.*
import com.represa.quoty.data.model.database.QuoteDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Defines methods for using the ToDoDatabase class with Room.
 */
@Dao
interface QDatabaseDao {

    @Query("SELECT * FROM quotes WHERE body LIKE '%' || :search || '%' ")
    fun getPrueba(search : String): Flow<List<QuoteDatabase>>

    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): Flow<List<QuoteDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuote(quote: QuoteDatabase)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListOfQuotes(quote: List<QuoteDatabase>)

}