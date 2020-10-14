package com.represa.quoty.data.model.network

data class QuoteNetwork(
    var id: Int,
    var dialogue: Boolean,
    var private: Boolean,
    var tags: List<String>,
    var url: String,
    var author_permalink: String,
    var favorites_count: Int,
    var upvotes_count: Int,
    var downvotes_count: Int,
    var author: String,
    var body: String
)

data class ResponseQuotes(
    var page: Int,
    var last_page: Boolean,
    var quotes: List<QuoteNetwork>
)
