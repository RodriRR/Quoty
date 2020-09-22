package com.represa.quoty.data.database

import androidx.room.*
import com.represa.quoty.data.model.database.QuoteDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Defines methods for using the ToDoDatabase class with Room.
 */
@Dao
interface QDatabaseDao {

    @Query("SELECT * FROM quotes")
    fun getCategories(): Flow<List<QuoteDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewTask(quote: QuoteDatabase)

}