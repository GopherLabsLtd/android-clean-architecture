package it.gopher.kotlin_mvp.core.gateway

import it.gopher.kotlin_mvp.Result
import it.gopher.kotlin_mvp.core.entities.Book
import it.gopher.kotlin_mvp.core.usecase.AddBookParameters

typealias FetchBooksEntityGatewayHandler = (books: Result<Array<Book>, Exception>) -> Unit
typealias AddBookEntityGatewayHandler = (books: Result<Book, Exception>) -> Unit
typealias DeleteBookEntityGatewayHandler = (books: Result<Unit, Exception>) -> Unit

interface BookGateway {
    fun fetchBooks(completion: FetchBooksEntityGatewayHandler)
    fun add(parameters: AddBookParameters, completion: AddBookEntityGatewayHandler)
    fun delete(book: Book, completion: DeleteBookEntityGatewayHandler)
}