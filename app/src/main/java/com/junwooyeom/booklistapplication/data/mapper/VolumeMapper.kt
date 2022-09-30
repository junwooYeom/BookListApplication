package com.junwooyeom.booklistapplication.data.mapper

import com.junwooyeom.booklistapplication.data.entity.VolumeDto
import com.junwooyeom.booklistapplication.domain.model.Volume

fun VolumeDto.toVolume(): Volume = Volume(
    title = title,
    subTitle = subTitle,
    authors = authors ?: listOf(),
    publisher = publisher,
    published = published,
    categories = categories,
    links = links?.toImage(),
)
