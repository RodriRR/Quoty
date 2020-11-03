package com.represa.quoty.data.model.network.quote

data class NewQuote(
    var quote : NewQuoteParams
)

data class NewQuoteParams(
    var author : String,
    var body : String
)