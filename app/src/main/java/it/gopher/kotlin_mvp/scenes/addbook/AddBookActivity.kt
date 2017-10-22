package it.gopher.kotlin_mvp.scenes.addbook

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import it.gopher.kotlin_mvp.R

import kotlinx.android.synthetic.main.activity_add_book.*

public class AddBookActivity : AppCompatActivity() {
    public var presenter: AddBookPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
