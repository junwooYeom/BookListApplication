package com.junwooyeom.booklistapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junwooyeom.booklistapplication.domain.usecase.GetBooksUseCase
import com.junwooyeom.booklistapplication.domain.usecase.GetTotalBooksUseCase
import com.junwooyeom.booklistapplication.domain.usecase.LoadMoreBooksUseCase
import com.junwooyeom.booklistapplication.domain.model.NetworkResult
import com.junwooyeom.booklistapplication.domain.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val loadMoreBooksUseCase: LoadMoreBooksUseCase,
    getTotalBooksUseCase: GetTotalBooksUseCase
) : ViewModel() {

    val totalBooks = getTotalBooksUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0
        )

    fun findBooksByKeyword(keyword: String): Flow<NetworkResult<List<Book>>> = getBooksUseCase(keyword)
    fun loadMoreBooks(index: Int): Flow<NetworkResult<List<Book>>> = loadMoreBooksUseCase(index)
}
