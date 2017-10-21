package it.gopher.kotlin_mvp.core.entities

import java.util.*

/**
 * Basic Data Class for a Book Entity.
 */
data class Book(val id: String,
                val isbn: String,
                val title: String,
                val author: String,
                val releaseDate: Date?,
                val pages: Int) {

    /**
     * Calculates the time it takes to read in hours.
     */
    public fun durationToReadInHours(): Double {
        return pages.toDouble() / 30.0
    }
}