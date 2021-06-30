package com.talencote.ficat.data.dto

import com.google.gson.annotations.SerializedName
import com.talencote.ficat.data.models.User

data class LoginResponse(
    @SerializedName("id")
    var id: Int,

    @SerializedName("username")
    var username: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("roles")
    var roles: List<String>,

    @SerializedName("accessToken")
    var authToken: String,

    @SerializedName("tokenType")
    var tokenType: String
)
