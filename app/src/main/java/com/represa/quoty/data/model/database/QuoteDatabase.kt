package com.represa.quoty.data.model.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quotes")
data class QuoteDatabase(
    @PrimaryKey
    @NonNull
    var id: Int,
    var dialogue: Boolean,
    var hidde: Boolean,
    var tags: String,
    var url: String,
    var favorites_count: Int,
    var upvotes_count: Int,
    var downvotes_count: Int,
    var author: String,
    var body: String,
    var image: String,
    var favourite: Boolean
)

