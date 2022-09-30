package com.junwooyeom.booklistapplication.domain.model

import java.io.Serializable

data class Volume (
    val title: String,
    val subTitle: String?,
    val authors: List<String>,
    val publisher: String?,
    val published: String?,
    val categories: List<String>?,
    val links: Image?
): Serializable
