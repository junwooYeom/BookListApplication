package com.junwooyeom.booklistapplication.domain.usecase

import com.junwooyeom.booklistapplication.domain.repository.BookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadMoreBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(index: Int) = repository.loadMore(index)
}
