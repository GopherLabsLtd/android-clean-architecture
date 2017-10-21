package it.gopher.kotlin_mvp.core.usecase

import it.gopher.kotlin_mvp.Result
import it.gopher.kotlin_mvp.core.entities.Book
import it.gopher.kotlin_mvp.core.gateway.BookGateway
import it.gopher.kotlin_mvp.core.gateway.DeleteBookEntityGatewayHandler

typealias DeleteBookUseCaseHandler = (books: Result<Unit, Exception>) -> Unit

/**
 * Used to hold a notifications. (NOT YET IMPLEMENTED)
 */
public class DeleteBookUseCaseNotification {
    companion object {
        @JvmStatic
        val didDeleteBook = "didDeleteBookNotification"
    }
}

/**
 * Constructor for Delete Book Use Case.
 */
public interface DeleteBookUseCase {
    fun delete(book: Book, completion: DeleteBookUseCaseHandler)
}

/**
 * Implementation of Delete Book Use Case.
 */
public class DeleteBookUseCaseImplementation(val booksGateway: BookGateway) : DeleteBookUseCase {
    override fun delete(book: Book, completion: DeleteBookUseCaseHandler) {
        booksGateway.delete(book) { result ->
            when (result) {
                is Result.success<*> -> completion(result)
                is Result.error<*> -> completion(result)
            }
        }
    }
}