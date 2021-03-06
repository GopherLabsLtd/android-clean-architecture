package it.gopher.kotlin_mvp

import android.content.res.Resources

public sealed class Result<out T : Any?, out E : Exception?> {
    class success<T>(val obj: T) : Result<T, Exception>()
    class error<T>(val error: Exception) : Result<T, Exception>()

    @Throws(Exception::class)
    public fun dematerialize(): T {
        when (this) {
            is success<*> -> return obj as T
            is error<*> -> throw error
        }
    }

    @Throws(Resources.NotFoundException::class)
    public fun dematerializeError(): Exception {
        when (this) {
            is success<*> -> throw Resources.NotFoundException()
            is error<*> -> return error
        }
    }
}