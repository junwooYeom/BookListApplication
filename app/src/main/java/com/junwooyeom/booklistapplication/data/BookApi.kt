package com.junwooyeom.booklistapplication.data

import com.junwooyeom.booklistapplication.data.entity.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET("volumes")
    suspend fun getBookList(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int
    ): BookResponse
}
