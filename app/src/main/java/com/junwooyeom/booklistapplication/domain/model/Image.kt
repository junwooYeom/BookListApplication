package com.junwooyeom.booklistapplication.domain.model

import java.io.Serializable

data class Image(
    val smallThumbnail: String,
    val thumbnail: String
): Serializable
