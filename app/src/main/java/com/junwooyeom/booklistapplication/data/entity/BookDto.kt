package com.junwooyeom.booklistapplication.data.entity

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("etag")
    val eTag: String,
    @SerializedName("selfLink")
    val selfLink: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeDto,
)
