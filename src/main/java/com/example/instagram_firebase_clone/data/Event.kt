package com.example.instagram_firebase_clone.data

open class Event<out T>(private val content: T) {

    // only this class can be updated
    var hasBeenHandled = false
        private set

    fun getContentOrNull(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}