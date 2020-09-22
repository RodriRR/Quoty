package com.represa.quoty.data.model.network

data class User(
        var user: UserParameters
)

data class UserParameters(
        var login: String,
        var password: String
)