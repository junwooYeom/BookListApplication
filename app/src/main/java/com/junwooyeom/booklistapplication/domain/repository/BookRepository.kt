package com.junwooyeom.booklistapplication.domain.repository

import com.junwooyeom.booklistapplication.domain.model.NetworkResult
import com.junwooyeom.booklistapplication.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface BookRepository {
    val maxCountFlow: MutableStateFlow<Int>

    fun getBooks(query: String, index: Int = 0): Flow<NetworkResult<List<Book>>>
    fun loadMore(index: Int): Flow<NetworkResult<List<Book>>>
}
