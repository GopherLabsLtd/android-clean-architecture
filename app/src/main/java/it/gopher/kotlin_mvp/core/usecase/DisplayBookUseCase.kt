package it.gopher.kotlin_mvp.core.usecase

import it.gopher.kotlin_mvp.Result
import it.gopher.kotlin_mvp.core.entities.Book
import it.gopher.kotlin_mvp.core.gateway.BookGateway

typealias DisplayBooksUseCaseHandler = (books: Result<Array<Book>, Exception>) -> Unit

/**
 * Constructor for Delete Book Use Case.
 */
public interface DisplayBookUseCase {
    fun displayBooks(completion: DisplayBooksUseCaseHandler)
}

public class DisplayBookImplementation(val booksGateway: BookGateway) : DisplayBookUseCase {
    override fun displayBooks(completion: DisplayBooksUseCaseHandler) {
        booksGateway.fetchBooks { books ->
            completion(books)
        }
    }
}