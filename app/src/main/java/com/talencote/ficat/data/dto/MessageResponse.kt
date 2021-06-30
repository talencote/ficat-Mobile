package com.talencote.ficat.data.dto

import com.google.gson.annotations.SerializedName

data class MessageResponse(
        @SerializedName("message")
        var message: String
)