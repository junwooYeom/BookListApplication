package com.junwooyeom.booklistapplication.di

import com.junwooyeom.booklistapplication.data.BookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun providesBookInfraService(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)

    private companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}
