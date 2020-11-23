package com.represa.quoty.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.represa.quoty.data.model.database.QuoteDatabase

/**
 * A database that stores Tas and Categories information.
 * And a global method to get access to the database.
 */
@Database(entities = [QuoteDatabase::class], version = 4, exportSchema = false)
public abstract class QDatabase : RoomDatabase() {

    abstract fun quoteDao(): QDatabaseDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: QDatabase? = null

        fun getDatabase(context: Context): QDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QDatabase::class.java,
                    "quote_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}