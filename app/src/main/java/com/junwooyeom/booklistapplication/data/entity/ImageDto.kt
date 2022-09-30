package com.junwooyeom.booklistapplication.data.entity

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("smallThumbnail")
    val smallThumbnail: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
)
