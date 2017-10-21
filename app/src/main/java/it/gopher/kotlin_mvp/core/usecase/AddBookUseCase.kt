package it.gopher.kotlin_mvp.core.usecase

import it.gopher.kotlin_mvp.Result
import it.gopher.kotlin_mvp.core.entities.Book
import it.gopher.kotlin_mvp.core.gateway.AddBookEntityGatewayHandler
import it.gopher.kotlin_mvp.core.gateway.BookGateway
import java.util.*

typealias AddBookUseCaseHandler = (books: Result<Array<Book>, Exception>) -> Unit

/**
 * Contract for Add Book Use Case.
 */
public interface AddBookUseCase {
    fun add(parameters: AddBookParameters, completion: AddBookUseCaseHandler)
}

/**
 * This class is used across all layers - Core, UI and Network
 * It's not violating any dependency rules.
 * However it might make sense for each layer do define it's own input parameters so it can be used independently of
 * the other layers.
 */
data class AddBookParameters(val isbn: String,
                             val title: String,
                             val author: String,
                             val releaseDate: Date?,
                             val pages: Int
)

/**
 * Implementation of the Add Book Use Case.
 */
public class AddBookImplementation(val booksGateway: BookGateway) : AddBookUseCase {

    /**
     * Adds a book to the Gateway.
     */
    override fun add(parameters: AddBookParameters, completion: AddBookUseCaseHandler) {
        booksGateway.add(parameters) { books ->
            // Do any additional processing & after that call the completion handler
            completion(books)
        }
    }
}