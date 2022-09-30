package com.junwooyeom.booklistapplication.domain.model

import java.io.Serializable

data class Book(
    val kind: String,
    val id: String,
    val eTag: String,
    val selfLink: String,
    val volumeInfo: Volume
): Serializable
