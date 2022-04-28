package com.example.shonenarena.presentation.common.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shonenarena.common.EMPTY_STRING
import com.example.shonenarena.common.LOG_TAG
import com.example.shonenarena.infrastructure.navigation.NavigationCommand

abstract class BaseFragment : Fragment(), BaseView {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setCurrentLocation(findNavController().currentDestination?.id ?: -1)
        observeNavigationEvents()
    }

    private fun observeNavigationEvents() {
        viewModel.navLiveData.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let {  navigationCommand ->
                when(navigationCommand) {
                    is NavigationCommand.To -> navigateTo(navigationCommand.currentDestinationId) { navController ->
                        navController.navigate(navigationCommand.directions)
                    }
                    is NavigationCommand.Back -> findNavController(this).navigateUp()
                }
            }
        })
    }

    private fun navigateTo(currentDestinationId: Int, callback: (navController: NavController) -> Unit) {
        try {
            if(findNavController(this).currentDestination?.id == currentDestinationId) {
                callback(findNavController(this))
            }
        } catch (exception: Exception) {
            Log.d(LOG_TAG, exception.message ?: EMPTY_STRING)
        }
    }
}