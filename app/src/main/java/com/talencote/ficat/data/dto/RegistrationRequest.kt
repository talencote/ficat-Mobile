package com.talencote.ficat.data.dto

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
        @SerializedName("username")
        var username: String,

        @SerializedName("email")
        var email: String,

        @SerializedName("password")
        var password: String
)