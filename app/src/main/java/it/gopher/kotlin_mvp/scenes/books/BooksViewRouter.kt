package it.gopher.kotlin_mvp.scenes.books

import it.gopher.kotlin_mvp.core.entities.Book
import it.gopher.kotlin_mvp.other.ViewRouter
import it.gopher.kotlin_mvp.scenes.addbook.AddBookPresenterDelegate

interface BooksViewRouter : ViewRouter {
    fun presentDetailViewFor(book: Book)
    fun presentAddBook(addBookPresenterDelegate: AddBookPresenterDelegate)
}

public class BooksViewRouterImplementation(
        val booksActivity: BooksActivity,
        var addBookPresenterDelegate: AddBookPresenterDelegate? = null,
        var book: Book? = null
) : BooksViewRouter {

    override fun presentAddBook(addBookPresenterDelegate: AddBookPresenterDelegate) {
        this.addBookPresenterDelegate = addBookPresenterDelegate
        // TODO
    }

    override fun presentDetailViewFor(book: Book) {
        // TODO
    }
}