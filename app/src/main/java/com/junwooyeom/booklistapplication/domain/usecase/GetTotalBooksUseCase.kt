package com.junwooyeom.booklistapplication.domain.usecase

import com.junwooyeom.booklistapplication.domain.repository.BookRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetTotalBooksUseCase @Inject constructor(
    private val repository: BookRepository
){
    operator fun invoke(): StateFlow<Int> = repository.maxCountFlow
}
