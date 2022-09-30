package com.junwooyeom.booklistapplication.data

import com.junwooyeom.booklistapplication.domain.model.NetworkResult
import com.junwooyeom.booklistapplication.data.mapper.toBook
import com.junwooyeom.booklistapplication.domain.model.Book
import com.junwooyeom.booklistapplication.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi
) : BookRepository {
    private val queryString: MutableStateFlow<String> = MutableStateFlow("")
    override val maxCountFlow: MutableStateFlow<Int> = MutableStateFlow(0)

    override fun getBooks(query: String, index: Int): Flow<NetworkResult<List<Book>>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            if (query.isNotEmpty()) {
                val data = api.getBookList(query, index)
                emit(NetworkResult.Success(data.items.map { it.toBook() })).also {
                    queryString.emit(query)
                    maxCountFlow.emit(data.totalItems)
                }
            } else {
                emit(NetworkResult.Loading(false))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e))
        }
    }

    override fun loadMore(index: Int): Flow<NetworkResult<List<Book>>> = getBooks(queryString.value, index + 1)
}
