package com.junwooyeom.booklistapplication.data.mapper

import com.junwooyeom.booklistapplication.data.entity.BookDto
import com.junwooyeom.booklistapplication.domain.model.Book

fun BookDto.toBook(): Book =
     Book(
         kind = kind,
         id = id,
         eTag = eTag,
         selfLink = selfLink,
         volumeInfo = volumeInfo.toVolume()
     )
