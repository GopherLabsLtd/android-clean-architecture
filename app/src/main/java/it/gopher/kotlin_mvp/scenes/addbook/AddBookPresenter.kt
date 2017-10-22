package it.gopher.kotlin_mvp.scenes.addbook

import it.gopher.kotlin_mvp.Result
import it.gopher.kotlin_mvp.core.entities.Book
import it.gopher.kotlin_mvp.core.usecase.AddBookParameters
import it.gopher.kotlin_mvp.core.usecase.AddBookUseCase
import java.util.*

public interface AddBookPresenter {
    val maximumRelaseDate: Date
    fun addButtonPressed(parameters: AddBookParameters)
    fun cancelButtonPressed()
}

//In the most simple cases (like this one) the delegate wouldn't be needed
// We added it just to highlight how two presenters would communicate
// Most of the time it's fine for the view controller to dimiss itself
public interface AddBookPresenterDelegate {
    fun addBookPresenter(presenter: AddBookPresenter, didAddBook: Book)
    fun addBookPresenterDidCancel(presenter: AddBookPresenter)
}

public interface AddBookView {
    fun updateAddButtonState(isEnabled: Boolean)
    fun updateCancelButtonState(isEnabled: Boolean)
    fun displayBookError(title: String, message: String)
}

public class AddBookPresenterImplementation(
        val view: AddBookView? = null,
        val addBookUseCase: AddBookUseCase,
        val delegate: AddBookPresenterDelegate? = null
) : AddBookPresenter {

    override val maximumRelaseDate: Date = Date()

    override fun cancelButtonPressed() {
        delegate?.addBookPresenterDidCancel(this)
    }

    override fun addButtonPressed(parameters: AddBookParameters) {
        updateNavigationState(false)
        addBookUseCase.add(parameters) { books ->
            when (books) {
                is Result.success<*> -> handleBookAdded(books.dematerialize())
                is Result.error<*> -> handleBookError(books.dematerializeError())
            }

        }
    }

    private fun handleBookAdded(book: Book) {
        delegate?.addBookPresenter(this, book)
    }

    private fun handleBookError(error: Exception) {
        view?.displayBookError("Error", error.localizedMessage)
    }

    private fun updateNavigationState(isEnabled: Boolean) {
        view?.updateAddButtonState(isEnabled)
        view?.updateCancelButtonState(isEnabled)
    }
}