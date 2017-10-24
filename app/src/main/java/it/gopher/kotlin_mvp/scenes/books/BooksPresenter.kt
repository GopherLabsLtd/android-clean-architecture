package it.gopher.kotlin_mvp.scenes.books

import it.gopher.kotlin_mvp.core.entities.Book
import it.gopher.kotlin_mvp.core.usecase.DeleteBookUseCase
import it.gopher.kotlin_mvp.core.usecase.DisplayBookUseCase

interface BooksView {
    fun refreshBooksView()
    fun displayBooksRetrievalError(title: String, message: String)
    fun displayBookDeleteError(title: String, message: String)
    fun deleteAnimated(row: Int)
    fun endEditing()
}

interface BookCellView {
    fun displayTitle(title: String)
    fun displayAuthor(author: String)
    fun displayReleaseDate(releaseDate: String)
}

interface BooksPresenter {
    val numberOfBooks: Int
    val router: BooksViewRouter
    fun onViewCreated()
    fun configure(cell: BookCellView, row: Int)
    fun didSelect(row: Int)
    fun canEdit(row: Int): Boolean
    fun titleForDeleteButton(row: Int): String
    fun deleteButtonPressed(row: Int)
    fun addButtonPressed()
}


public class BooksPresenterImplementation(
        val view: BooksView? = null,
        val displayBookUseCase: DisplayBookUseCase,
        val deleteBookUseCase: DeleteBookUseCase,
        override val router: BooksViewRouter
) : BooksPresenter {

    val books = ArrayList<Book>()

    override val numberOfBooks: Int
        get() = books.count()

    override fun onViewCreated() {
        displayBookUseCase.displayBooks { result ->
            // TODO
        }
    }

    override fun configure(cell: BookCellView, row: Int) {
        val book = books[row]

        cell.displayTitle(book.title)
        cell.displayAuthor(book.author)
        cell.displayReleaseDate(book.releaseDate.toString())
    }

    override fun didSelect(row: Int) {
        val book = books[row]
        // TODO :
    }

    override fun canEdit(row: Int): Boolean {
        return true
    }

    override fun titleForDeleteButton(row: Int): String {
        return "Delete book"
    }

    override fun deleteButtonPressed(row: Int) {
        view?.endEditing()
        val book = books[row]
        deleteBookUseCase.delete(book) { result ->
            // TODO
        }

    }

    override fun addButtonPressed() {

    }

}