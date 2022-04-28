package com.example.shonenarena.presentation.common.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.shonenarena.common.EMPTY_STRING
import com.example.shonenarena.common.LOG_TAG
import com.example.shonenarena.infrastructure.navigation.NavigationCommand
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog : BottomSheetDialogFragment() , BaseView  {
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
                    is NavigationCommand.Back -> NavHostFragment.findNavController(this).navigateUp()
                }
            }
        })
    }

    private fun navigateTo(currentDestinationId: Int, callback: (navController: NavController) -> Unit) {
        try {
            if(NavHostFragment.findNavController(this).currentDestination?.id == currentDestinationId) {
                callback(NavHostFragment.findNavController(this))
            }
        } catch (exception: Exception) {
            Log.d(LOG_TAG, exception.message ?: EMPTY_STRING)
        }
    }
}