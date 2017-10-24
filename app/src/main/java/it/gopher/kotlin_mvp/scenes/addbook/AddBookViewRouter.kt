package it.gopher.kotlin_mvp.scenes.addbook

import it.gopher.kotlin_mvp.other.ViewRouter

/**
 * Created by alexnguyen on 2017-10-23.
 */
public interface AddBookViewRouter : ViewRouter {
    fun dimiss()
}

public class AddBookViewRouterImplementation(
        val addBookActivity: AddBookActivity) : AddBookViewRouter {

    override fun dimiss() {
        addBookActivity.onBackPressed()
    }
}