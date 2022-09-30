package com.junwooyeom.booklistapplication.data.entity

import com.google.gson.annotations.SerializedName
import com.junwooyeom.booklistapplication.data.entity.ImageDto

data class VolumeDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("subTitle")
    val subTitle: String?,
    @SerializedName("authors")
    val authors: List<String>?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("publishedDate")
    val published: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("categories")
    val categories: List<String>?,
    @SerializedName("imageLinks")
    val links: ImageDto?,
)
