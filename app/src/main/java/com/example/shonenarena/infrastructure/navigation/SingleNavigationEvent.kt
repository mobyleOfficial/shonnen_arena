package com.example.shonenarena.infrastructure.navigation

open class SingleNavigationEvent <T>(private val content: T) {
    private var hasBeenHandled = false

    fun getContentIfNotHandled() : T? = if(hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }
}