package it.gopher.kotlin_mvp.entitygateway.api.gateways

import it.gopher.kotlin_mvp.Result
import it.gopher.kotlin_mvp.core.entities.Book
import it.gopher.kotlin_mvp.core.gateway.AddBookEntityGatewayHandler
import it.gopher.kotlin_mvp.core.gateway.BookGateway
import it.gopher.kotlin_mvp.core.gateway.DeleteBookEntityGatewayHandler
import it.gopher.kotlin_mvp.core.gateway.FetchBooksEntityGatewayHandler
import it.gopher.kotlin_mvp.core.usecase.AddBookParameters
import it.gopher.kotlin_mvp.entitygateway.api.BooksApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This interface is not really needed, but if we choose to have more methods, we can add them here.
 */
interface ApiBooksGateway : BookGateway {

}

public class ApiBooksGatewayImplementation(
        val apiService: BooksApiService) : ApiBooksGateway {


    override fun fetchBooks(completion: FetchBooksEntityGatewayHandler) {
        val call = apiService.fetchBooks()
        call.enqueue(object : Callback<Array<Book>> {
            override fun onResponse(call: Call<Array<Book>>?, response: Response<Array<Book>>?) {
                val unwrappedResponse = response?.let { it } ?: return
                if (unwrappedResponse.isSuccessful) {
                    val unwrappedResponseBody = response.body().let { it } ?: return
                    completion(Result.success<Array<Book>>(unwrappedResponseBody))
                } else {
                    completion(Result.error(Exception()))
                }
            }

            override fun onFailure(call: Call<Array<Book>>?, t: Throwable?) {
                completion(Result.error(Exception()))
            }

        })
    }

    override fun add(parameters: AddBookParameters, completion: AddBookEntityGatewayHandler) {
        val call = apiService.addBook(parameters)
        call.enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>?, response: Response<Book>?) {
                val unwrappedResponse = response?.let { it } ?: return
                if (unwrappedResponse.isSuccessful) {
                    val unwrappedResponseBody = response.body().let { it } ?: return
                    completion(Result.success<Book>(unwrappedResponseBody))
                } else {
                    completion(Result.error(Exception()))
                }
            }

            override fun onFailure(call: Call<Book>?, t: Throwable?) {
                completion(Result.error(Exception()))
            }
        })
    }

    override fun delete(book: Book, completion: DeleteBookEntityGatewayHandler) {
        val call = apiService.deleteBook(book.id)
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                val unwrappedResponse = response?.let { it } ?: return
                if (unwrappedResponse.isSuccessful) {
                    val unwrappedResponseBody = response.body().let { it } ?: return
                    completion(Result.success<Unit>(unwrappedResponseBody))
                } else {
                    completion(Result.error(Exception()))
                }
            }

            override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                completion(Result.error(Exception()))
            }
        })
    }
}

