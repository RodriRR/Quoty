package com.represa.quoty.data.model

import com.squareup.moshi.Json

data class Token(
    @Json(name = "User-Token")
    var token: String,
    var login: String,
    var email: String
)