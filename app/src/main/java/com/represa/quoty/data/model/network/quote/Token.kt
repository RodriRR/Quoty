package com.represa.quoty.data.model.network.quote

import com.squareup.moshi.Json

data class Token(
    @Json(name = "User-Token")
    var userToken: String,
    var login: String,
    var email: String
)