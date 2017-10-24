package it.gopher.kotlin_mvp.scenes.books

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import it.gopher.kotlin_mvp.R

import kotlinx.android.synthetic.main.activity_main.*

class BooksActivity : AppCompatActivity(), BooksView {
    override fun refreshBooksView() {

    }

    override fun displayBooksRetrievalError(title: String, message: String) {

    }

    override fun displayBookDeleteError(title: String, message: String) {

    }

    override fun deleteAnimated(row: Int) {

    }

    override fun endEditing() {

    }

    var presenter: BooksPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
