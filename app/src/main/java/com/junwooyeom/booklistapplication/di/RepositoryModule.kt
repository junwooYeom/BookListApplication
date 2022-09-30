package com.junwooyeom.booklistapplication.di

import com.junwooyeom.booklistapplication.data.BookRepositoryImpl
import com.junwooyeom.booklistapplication.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsBooksRepository(impl: BookRepositoryImpl): BookRepository
}
