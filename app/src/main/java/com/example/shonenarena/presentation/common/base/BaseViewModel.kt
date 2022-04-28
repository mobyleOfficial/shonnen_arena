package com.example.shonenarena.presentation.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.shonenarena.infrastructure.navigation.NavigationCommand
import com.example.shonenarena.infrastructure.navigation.SingleNavigationEvent

open class BaseViewModel : ViewModel() {
    private val _navLiveData: MutableLiveData<SingleNavigationEvent<NavigationCommand>> = MutableLiveData()
    val navLiveData: LiveData<SingleNavigationEvent<NavigationCommand>> = _navLiveData
    private var currentDestinationId: Int = -1

    fun setCurrentLocation(currentDestinationId: Int) {
        this.currentDestinationId = currentDestinationId
    }

    protected fun navigate(directions: NavDirections) {
        _navLiveData.postValue(SingleNavigationEvent(NavigationCommand.To(currentDestinationId , directions)))
    }

    protected fun navigateBack() {
        _navLiveData.postValue(SingleNavigationEvent(NavigationCommand.Back))
    }
}