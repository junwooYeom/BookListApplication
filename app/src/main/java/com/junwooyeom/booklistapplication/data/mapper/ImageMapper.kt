package com.junwooyeom.booklistapplication.data.mapper

import com.junwooyeom.booklistapplication.data.entity.ImageDto
import com.junwooyeom.booklistapplication.domain.model.Image

fun ImageDto.toImage(): Image =
    Image(
        smallThumbnail = smallThumbnail,
        thumbnail = thumbnail
    )
