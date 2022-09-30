package com.junwooyeom.booklistapplication.data.entity

import com.google.gson.annotations.SerializedName
import com.junwooyeom.booklistapplication.data.entity.BookDto

data class BookResponse(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("items")
    val items: List<BookDto>,
    @SerializedName("totalItems")
    val totalItems: Int,
)
