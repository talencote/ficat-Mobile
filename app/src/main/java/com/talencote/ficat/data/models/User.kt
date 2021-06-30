package com.talencote.ficat.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: String,

    @SerializedName("username")
    var username: String,

    @SerializedName("email")
    var email: String
)
