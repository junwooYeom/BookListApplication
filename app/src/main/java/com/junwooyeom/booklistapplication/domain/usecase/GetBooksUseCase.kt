package com.junwooyeom.booklistapplication.domain.usecase

import com.junwooyeom.booklistapplication.domain.repository.BookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(query: String) = repository.getBooks(query)
}
