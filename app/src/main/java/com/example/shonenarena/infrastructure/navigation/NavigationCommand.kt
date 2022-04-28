package com.example.shonenarena.infrastructure.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val currentDestinationId: Int, val directions: NavDirections) : NavigationCommand()
    object Back: NavigationCommand()
}