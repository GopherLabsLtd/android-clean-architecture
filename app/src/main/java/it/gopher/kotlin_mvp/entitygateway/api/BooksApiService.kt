package it.gopher.kotlin_mvp.entitygateway.api

import it.gopher.kotlin_mvp.core.entities.Book
import it.gopher.kotlin_mvp.core.usecase.AddBookParameters
import retrofit2.Call
import retrofit2.http.*

interface BooksApiService {
    @POST("/book")
    fun addBook(@Body addBookParameters: AddBookParameters): Call<Book>

    @GET("/books")
    fun fetchBooks(): Call<Array<Book>>

    @DELETE("/books/{bookid}")
    fun deleteBook(@Path("bookid") bookId: String): Call<Unit>
}