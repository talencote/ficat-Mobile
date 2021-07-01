package com.talencote.ficat.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Long,

    @SerializedName("username")
    var username: String?,

    @SerializedName("email")
    var email: String?
)
