package com.talencote.ficat.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FanficDto(
    @SerializedName("id")
    var id: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("content")
    var content: String,

    @SerializedName("fandom")
    var fandom: String,

    @SerializedName("imageUrl")
    var imageUrl: String,

    @SerializedName("tags")
    var tags: Set<String>,

    @SerializedName("author")
    var author: String
):Parcelable
